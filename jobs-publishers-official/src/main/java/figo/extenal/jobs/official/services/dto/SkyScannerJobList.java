package figo.extenal.jobs.official.services.dto;

import figo.extenal.jobs.official.constants.SkyScannerConstants;
import figo.external.jobs.core.models.Job;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class SkyScannerJobList {
    private List<SkyScannerJob> jobs;

    public List<Job> convert(Predicate<SkyScannerJob> filter) {
        if (this.jobs == null || this.jobs.isEmpty()) return Arrays.asList();
        return this.jobs.stream()
                .filter(filter)
                .map(SkyScannerJob::convert)
                .collect(Collectors.toList());
    }
}
