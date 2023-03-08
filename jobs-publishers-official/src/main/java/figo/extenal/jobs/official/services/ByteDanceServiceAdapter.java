package figo.extenal.jobs.official.services;

import figo.extenal.jobs.official.constants.CompanyNameConstants;
import figo.extenal.jobs.official.services.dto.ByteDanceJob;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ByteDanceServiceAdapter extends JobServiceAbstract<ByteDanceJob> implements PublishService {
    @Value("${jobs.publishers.bytedance.keyword}")
    private String keyword;
    @Value("${jobs.publishers.bytedance.limit}")
    private int limit;
    @Value("${jobs.publishers.bytedance.start-date}")
    private String startDate;
    private final String DATE_FORMAT = "yyyy-MM-dd";
    @Autowired
    private ByteDanceRestService byteDanceService;

    @Override
    public String getPublisher() {
        return CompanyNameConstants.ByteDance;
    }

    @SneakyThrows
    @Override
    public List<Job> getJobs() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date date = format.parse(startDate);
        return convert(byteDanceService.getJobs(keyword, 0, limit),
                p -> p.containsKeyword(keyword),
                q -> q.getPublishDate().compareTo(date) > 0);
    }
}
