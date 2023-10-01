package pl.chopy.gwizdbackenddeploy.rest.proceed;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chopy.gwizdbackenddeploy.model.ReportType;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;
import pl.chopy.gwizdbackenddeploy.model.entity.Report;
import pl.chopy.gwizdbackenddeploy.model.repository.ReportRepository;
import pl.chopy.gwizdbackenddeploy.rest.achievement.AchievementService;
import pl.chopy.gwizdbackenddeploy.rest.email.EmailTemplateService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportProceedService {
    private final EmailTemplateService emailTemplateService;
    private final AchievementService achievementService;
    private final ReportRepository reportRepository;

    public void processReport(Report report) {
        if (report.getReportType().equals(ReportType.DANGER)) {
            report.setSleepDate(null);
            emailTemplateService.sendDangerEmail(report);
        }
        checkActivity();
    }

    public List<Achievement> checkAchievements(Report report) {
        var user = report.getAuthor();
        var newAchs = new ArrayList<Achievement>();
        int reportNumber = reportRepository.countAllByAuthor(user);
        if (reportNumber == 5) {
            newAchs.add(achievementService.addAchievement(1L, user.getId()));
        }
        if (report.getAnimal().getId().equals(2L)) {
            newAchs.add(achievementService.addAchievement(2L, user.getId()));
        }
        if (report.getAnimal().getId().equals(3L)) {
            newAchs.add(achievementService.addAchievement(3L, user.getId()));
        }
        if (report.getAnimal().getId().equals(4L)) {
            newAchs.add(achievementService.addAchievement(4L, user.getId()));
        }
        return newAchs;
    }

    public void checkActivity() {
        var actives = reportRepository.findAllByActive(true);
        actives.forEach(
                rep -> {
                    if (rep.getSleepDate().isBefore(LocalDateTime.now()) &&
                            !rep.getReportType().equals(ReportType.DANGER)) {
                        rep.setActive(false);
                    }
                }
        );
        reportRepository.saveAll(actives);
    }
}
