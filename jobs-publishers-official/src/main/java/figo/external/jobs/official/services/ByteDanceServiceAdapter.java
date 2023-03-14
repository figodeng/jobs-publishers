package figo.external.jobs.official.services;

import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.dto.ByteDanceJob;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ByteDanceServiceAdapter extends JobServiceAbstract<ByteDanceJob> implements PublishService {
    @Value("${jobs.publishers.bytedance.keyword}")
    private String keyword;
    @Value("${jobs.publishers.bytedance.limit}")
    private int limit;
    @Autowired
    private ByteDanceRestService byteDanceService;

    @Override
    public String getPublisher() {
        return CompanyNameConstants.ByteDance;
    }

    @SneakyThrows
    @Override
    public List<Job> getJobs() {
        return convert(byteDanceService.getJobs(keyword, 0, limit),
                p -> p.containsKeyword(keyword));
    }
}
