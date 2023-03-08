package figo.extenal.jobs.official.services;

import figo.extenal.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.services.PublishService;

import java.util.List;

public class ByteDanceService implements PublishService {
    @Override
    public String getPublisher() {
        return CompanyNameConstants.ByteDance;
    }

    @Override
    public List<Job> getJobs() {
        return null;
    }
}
