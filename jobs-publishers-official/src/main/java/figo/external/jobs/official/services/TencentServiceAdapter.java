package figo.external.jobs.official.services;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.dto.TencentJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TencentServiceAdapter extends JobServiceAbstract<TencentJob> implements PublishService {
    @Autowired
    private TencentRestService tencentRestService;

    @Override
    public String getPublisher() {
        return CompanyNameConstants.Tencent;
    }

    @Override
    public List<Job> getJobs() {
        return convert(tencentRestService.getJobs(), p -> true);
    }
}
