package figo.external.jobs.core.services;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.models.Recruitment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class MarkdownService implements SubscribeService {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    @Override
    public void subscribe(Recruitment recruitment) {
        System.out.println("# jobs-publishers");
        System.out.println(String.format("## ------------- %s -------------", recruitment.getPublisher()));
        int i = 0;
        for (Job job : recruitment.getJobs()) {
            System.out.println(String.format(">%s. %s,%s,%s \\", ++i, job.getTitle(), format.format(job.getPublishDate()), job.getUrl()));
        }
    }
}
