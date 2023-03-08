package figo.extenal.jobs.official.services;

import figo.extenal.jobs.official.constants.SkyScannerConstants;
import figo.extenal.jobs.official.services.dto.SkyScannerJob;
import figo.external.jobs.core.models.Job;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JobServiceAbstract<T extends JobConverter> {
    protected List<Job> convert(List<T> jobList, Predicate<T> filter, Predicate<Job> filterJ) {
        if (jobList == null || jobList.isEmpty()) return Arrays.asList();

        List<Job> jobs = jobList.stream()
                .filter(filter)
                .map(T::convert)
                .filter(filterJ)
                .collect(Collectors.toList());

        Collections.sort(jobs, (p, q) -> q.getPublishDate().compareTo(p.getPublishDate()));

        return jobs;
    }

    protected List<Job> convert(List<T> jobList, Predicate<T> filter) {
        return convert(jobList, filter, p -> true);
    }
}
