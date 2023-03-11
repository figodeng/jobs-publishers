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
import org.springframework.util.StopWatch;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;

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
        long startTime = System.nanoTime();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        Future[] futures = new Future[publishServices.size()];
        for (int i = 0; i < publishServices.size(); i++) {
            PublishService finalPublishService = publishServices.get(i);
            futures[i] = threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        dispatchService.update(new Recruitment(finalPublishService.getPublisher(), finalPublishService.getJobs()));
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        for (Future future : futures) {
            future.get();
        }
        threadPoolExecutor.shutdown();
        Duration timeTakenToGetJobs = Duration.ofNanos(System.nanoTime() - startTime);
        System.out.println(String.format("get jobs takes %s s", timeTakenToGetJobs.toSeconds()));
    }
}
