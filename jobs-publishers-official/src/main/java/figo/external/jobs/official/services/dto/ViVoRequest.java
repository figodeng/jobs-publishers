package figo.external.jobs.official.services.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ViVoRequest {
    private int pageNum;
    private int pageSize;
    private String publishName;
    private List<String> jobTypeList;
    private List<String> positionIdList;
    private String recruitType;
    private List<String> workCityCodeList;

    public ViVoRequest() {
        this.pageNum = 1;
        this.pageSize = 200;
        this.publishName = "";
        this.jobTypeList = Arrays.asList("SOFTWARE");
        this.positionIdList = Arrays.asList();
        this.recruitType = "SOCIAL-RECRUITMENT";
        this.workCityCodeList = Arrays.asList("440300");
    }
}
