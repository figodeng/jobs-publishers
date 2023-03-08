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
}
