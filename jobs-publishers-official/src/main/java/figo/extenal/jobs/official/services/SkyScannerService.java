package figo.extenal.jobs.official.services;

import figo.extenal.jobs.official.services.dto.SkyScannerJobList;
import org.springframework.core.Ordered;

public interface SkyScannerService extends Ordered {
    SkyScannerJobList getJobs();
}
