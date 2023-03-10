package figo.external.jobs.official.services.dto;

import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.JobConverter;
import figo.external.jobs.core.models.Job;
import lombok.Data;

import java.util.Date;

@Data
public class ByteDanceJob implements JobConverter {
    private final String url_format = "https://jobs.bytedance.com/referral/pc/position/%s/detail";
    private String id;
    private String title;
    private String description;
    private String requirement;
    private long publish_time;

    private String code;

    public boolean containsKeyword(String keyword) {
        return this.title.toLowerCase().contains(keyword)
                || this.requirement.toLowerCase().contains(keyword);
    }

    @Override
    public Job convert() {
        return Job.builder().title(String.format("%s-%s", this.title, this.code))
                .url(String.format(url_format, this.id))
                .description(this.description)
                .publishDate(new Date(this.publish_time))
                .companyName(CompanyNameConstants.ByteDance)
                .build();
    }
}
