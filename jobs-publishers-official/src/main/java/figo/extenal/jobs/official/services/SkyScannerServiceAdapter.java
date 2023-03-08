package figo.extenal.jobs.official.services;

import figo.extenal.jobs.official.constants.CompanyNameConstants;
import figo.extenal.jobs.official.constants.SkyScannerConstants;
import figo.extenal.jobs.official.services.dto.SkyScannerJobList;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SkyScannerServiceDispatcher implements PublishService {
    private SkyScannerService skyScannerService;

    public SkyScannerServiceDispatcher(@Autowired List<SkyScannerService> skyScannerServices) {
        for (SkyScannerService scannerService : skyScannerServices) {
            if (skyScannerService == null
                    || skyScannerService.getOrder() < scannerService.getOrder())
                this.skyScannerService = scannerService;
        }
    }

    @Override
    public String getPublisher() {
        return CompanyNameConstants.SkyScanner;
    }

    /**
     * 对象适配器模式：把SkyScanner的数据结构适配成标准的数据结构
     * @return
     */
    public List<Job> getJobs() {
        SkyScannerJobList jobList = skyScannerService.getJobs();

        List<Job> jobs = jobList == null ? Arrays.asList()
                : jobList.convert(p -> p.locate(SkyScannerConstants.Location_Shenzhen));

        Collections.sort(jobs, (p, q) -> q.getPublishDate().compareTo(p.getPublishDate()));

        return jobs;
    }
}
