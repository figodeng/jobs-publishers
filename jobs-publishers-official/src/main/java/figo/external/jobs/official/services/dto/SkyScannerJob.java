package figo.external.jobs.official.services.dto;

import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.JobConverter;
import figo.external.jobs.core.models.Job;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class SkyScannerJob implements JobConverter {
    private String absolute_url;

    private Map<String, String> location;

    private String title;

    private String content;

    private Date updated_at;

    public Job convert() {
        return Job.builder().title(this.title)
                .url(this.absolute_url)
                .description(this.content)
                .publishDate(this.updated_at)
                .companyName(CompanyNameConstants.SkyScanner)
                .build();

    }

    public boolean locate(String locationName) {
        return this.location.containsValue(locationName);
    }
}
