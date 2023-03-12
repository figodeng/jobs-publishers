package figo.external.jobs.official.services;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.dto.PinganJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinganServiceAdapter extends JobServiceAbstract<PinganJob> implements PublishService {
    @Autowired
    private PinganRestService pinganRestService;

    @Override
    public String getPublisher() {
        return CompanyNameConstants.Pingan;
    }

    @Override
    public List<Job> getJobs() {
        return convert(pinganRestService.getJobs());
    }
}
