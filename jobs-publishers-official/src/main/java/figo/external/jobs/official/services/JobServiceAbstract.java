package figo.external.jobs.official.services;

import figo.external.jobs.core.models.Job;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JobServiceAbstract<T extends JobConverter> {
    @Value("${jobs.publishers.start-date}")
    private String startDate;
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @SneakyThrows
    protected List<Job> convert(List<T> jobList, Predicate<T> filter, Predicate<Job> filterJ) {
        if (jobList == null || jobList.isEmpty()) return Arrays.asList();

        Date date = format.parse(startDate);
        filterJ = filterJ.and(q -> q.getPublishDate().compareTo(date) > 0);

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

    protected List<Job> convert(List<T> jobList) {
        return convert(jobList, p -> true);
    }
}
