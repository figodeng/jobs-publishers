package figo.external.jobs.official.services;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.dto.AlibabaJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlibabaServiceAdapter extends JobServiceAbstract<AlibabaJob> implements PublishService {
    @Autowired
    private AlibabaRestService restService;

    @Override
    public String getPublisher() {
        return CompanyNameConstants.Alibaba;
    }

    @Override
    public List<Job> getJobs() {
        return convert(restService.getJobs());
    }
}
