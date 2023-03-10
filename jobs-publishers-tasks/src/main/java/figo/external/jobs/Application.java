package figo.external.jobs;

import figo.external.jobs.core.models.Recruitment;
import figo.external.jobs.core.services.PublishService;
import figo.external.jobs.core.services.RecruitmentDispatchService;
import figo.external.jobs.core.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication(scanBasePackages = {
        "figo.external.jobs"
})
@EnableScheduling
public class Application implements CommandLineRunner {
    @Autowired
    private List<PublishService> publishServices;
    @Autowired
    private RecruitmentDispatchService dispatchService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (PublishService publishService : publishServices) {
            dispatchService.update(new Recruitment(publishService.getPublisher(), publishService.getJobs()));
        }
    }
}
