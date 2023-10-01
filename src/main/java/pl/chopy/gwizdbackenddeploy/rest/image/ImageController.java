package pl.chopy.gwizdbackenddeploy.rest.image;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
@Tag(name = "Image handling")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Upload image to server")
    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file, @RequestParam(required = false, defaultValue = "false") boolean crop) throws IOException {
        return ResponseEntity.ok(imageService.uploadImage(file, crop));
    }

    @GetMapping("/{fileName}")
    @Operation(summary = "Download image from server")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
