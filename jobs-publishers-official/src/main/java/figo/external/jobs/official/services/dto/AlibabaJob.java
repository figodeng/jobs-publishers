package figo.external.jobs.official.services.dto;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.JobConverter;
import lombok.Data;

import java.util.Date;

@Data
public class AlibabaJob implements JobConverter {
    private final String url_format = "https://talent.alibaba.com%s";
    private String name;
    private String department;
    private String requirement;
    private String description;
    private long publishTime;
    private String positionUrl;

    @Override
    public Job convert() {
        return Job.builder().title(String.format("%s-%s", this.name, this.department))
                .url(String.format(url_format, this.positionUrl))
                .description(this.requirement)
                .publishDate(new Date(this.publishTime))
                .companyName(CompanyNameConstants.Alibaba)
                .build();
    }
}
