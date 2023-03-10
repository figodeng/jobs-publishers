package figo.external.jobs.official.services;

import figo.external.jobs.official.services.dto.SkyScannerJobList;
import org.springframework.core.Ordered;

public interface SkyScannerService extends Ordered {
    SkyScannerJobList getJobs();
}
