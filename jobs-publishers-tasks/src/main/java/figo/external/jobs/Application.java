package figo.external.jobs;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.List;

@SpringBootApplication(scanBasePackages = {
        "figo.extenal.jobs"
})
@EnableScheduling
public class Application implements CommandLineRunner {
    @Autowired
    private List<PublishService> publishServices;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    @Override
    public void run(String... args) throws Exception {
        for (PublishService publishService : publishServices) {
            System.out.println(String.format("------------- %s -------------", publishService.getPublisher()));
            List<Job> jobs = publishService.getJobs();
            for (Job job : jobs) {
                System.out.println(String.format("%s,%s,%s", job.getTitle(), format.format(job.getPublishDate()), job.getUrl()));
            }
        }
    }
}
