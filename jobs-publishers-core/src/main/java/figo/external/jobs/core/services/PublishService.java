package figo.external.jobs.core.services;

import figo.external.jobs.core.models.Job;

import java.util.List;

public interface PublishService {
    String getPublisher();
    List<Job> getJobs();
}
