package figo.external.jobs.official.services;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.dto.ViVoJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViVoServiceAdapter extends JobServiceAbstract<ViVoJob> implements PublishService {
    @Autowired
    private ViVoRestService restService;
    @Override
    public String getPublisher() {
        return CompanyNameConstants.ViVo;
    }

    @Override
    public List<Job> getJobs() {
        return convert(restService.getJobs());
    }
}
