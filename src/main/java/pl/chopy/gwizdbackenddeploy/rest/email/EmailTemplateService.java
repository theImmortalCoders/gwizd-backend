package pl.chopy.gwizdbackenddeploy.rest.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import pl.chopy.gwizdbackenddeploy.model.entity.Report;

@Service
@AllArgsConstructor
public class EmailTemplateService {
    private final EmailSendingService emailSendingService;

    public void sendDangerEmail(Report report) {
        var message = new Context();
        message.setVariable("report", report);
        emailSendingService.sendEmailWithHtmlTemplate("heilmartinos@gmail.com", "Danger detected", "email-danger", message);
    }
}
