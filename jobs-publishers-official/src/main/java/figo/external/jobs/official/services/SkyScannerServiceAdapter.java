package figo.external.jobs.official.services;

import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.constants.SkyScannerConstants;
import figo.external.jobs.official.services.dto.SkyScannerJob;
import figo.external.jobs.official.services.dto.SkyScannerJobList;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SkyScannerServiceAdapter extends JobServiceAbstract<SkyScannerJob> implements PublishService {
    private SkyScannerService skyScannerService;

    public SkyScannerServiceAdapter(@Autowired List<SkyScannerService> skyScannerServices) {
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
     *
     * @return
     */
    public List<Job> getJobs() {
        SkyScannerJobList jobList = skyScannerService.getJobs();

        return jobList == null ? Arrays.asList()
                : convert(jobList.getJobs(), p -> p.locate(SkyScannerConstants.Location_Shenzhen));
    }
}
