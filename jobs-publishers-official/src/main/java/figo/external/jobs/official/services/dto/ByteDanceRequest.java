package figo.external.jobs.official.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ByteDanceRequest {
    @JsonProperty(value = "keyword")
    private String keyword;
    @JsonProperty(value = "limit")
    private int limit;
    @JsonProperty(value = "offset")
    private int offset;
    //6704215862603155720
    @JsonProperty(value = "job_category_id_list")
    private List<String> job_category_id_list;
    //CT_128
    @JsonProperty(value = "location_code_list")
    private List<String> location_code_list;
    @JsonProperty(value = "subject_id_list")
    private List<String> subject_id_list;
    //1
    @JsonProperty(value = "recruitment_id_list")
    private List<String> recruitment_id_list;
    //1
    @JsonProperty(value = "portal_type")
    private int portal_type;
    @JsonProperty(value = "job_function_id_list")
    private List<String> job_function_id_list;
    @JsonProperty(value = "referral_token")
    private String referral_token;// NTsxNjc3NjY3Mzg1MDE3OzY5MDc0MDk3NTcxMTIzNzA3MDI7NzIwNTUyNjE0MzE1MDE2NDI4MQ",
    //1
    @JsonProperty(value = "portal_entrance")
    private int portal_entrance;

    public ByteDanceRequest(String referral_token) {
        this.keyword = "";
        this.job_category_id_list = new ArrayList<>(Arrays.asList("6704215862603155720"));
        this.location_code_list = new ArrayList<>(Arrays.asList("CT_128"));
        this.subject_id_list = new ArrayList<>();
        this.recruitment_id_list = new ArrayList<>(Arrays.asList("1"));
        this.portal_type = 1;
        this.job_function_id_list = new ArrayList<>();
        this.referral_token = referral_token;
        this.portal_entrance = 1;
    }

    public ByteDanceRequest paging(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
        return this;
    }
}
