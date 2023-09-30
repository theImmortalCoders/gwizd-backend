package pl.chopy.gwizdbackenddeploy.rest.image;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.chopy.gwizdbackenddeploy.model.entity.ImageData;
import pl.chopy.gwizdbackenddeploy.model.repository.ImageDataRepository;
import pl.chopy.gwizdbackenddeploy.rest.auth.OidcAuthService;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageDataRepository imageDataRepository;
    private final OidcAuthService authService;
    private final String FOLDER_PATH = "C:\\Users\\marci\\Desktop\\Repozytorium\\Projekty\\Hackyeah\\Gwizd-deploy\\gwizd-backend\\public\\images\\";

    public String uploadImage(MultipartFile file, boolean crop) throws IOException {
        String fileName =  "1_" + file.getOriginalFilename();
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (crop) {
            int size = Math.min(image.getHeight(), image.getWidth());
            int x = (image.getWidth() - size) / 2;
            int y = (image.getHeight() - size) / 2;
            image = image.getSubimage(x, y, size, size);
            fileName = "crop_" + fileName;
        }
        fileName = checkDuplicateName(fileName);
        String filePath = FOLDER_PATH + fileName;
        ImageIO.write(image, "png", new File(filePath));
        var imageData = Option.of(imageDataRepository.save(ImageData.builder()
                .name(fileName)
                .type(file.getContentType())
                .filePath(filePath).build())).getOrElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        return imageData.getName();
    }

    @Transactional
    public byte[] downloadImage(String fileName) throws IOException {
        ImageData imageData = Option.ofOptional(imageDataRepository.findByName(fileName))
                .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File '" + fileName + "' not found."));
        return Files.readAllBytes(new File(imageData.getFilePath()).toPath());
    }

    private String checkDuplicateName(String fileName) {
        while (imageDataRepository.existsByName(fileName)) {
            fileName = "copy_" + fileName;
        }
        return fileName;
    }

}
