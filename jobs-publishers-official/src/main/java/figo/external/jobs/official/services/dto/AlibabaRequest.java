package figo.external.jobs.official.services.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlibabaRequest {
    private String channel;
    private String language;
    private String batchId;
    private String categories;
    private List<String> deptCodes;
    private String key;
    private int pageIndex;
    private int pageSize;
    private String regions;
    private String subCategories;

    public AlibabaRequest() {
        this.channel = "group_official_site";
        this.language = "zh";
        this.batchId = "";
        this.categories = "130";
        this.deptCodes = new ArrayList<>();
        this.key = "";
        this.pageIndex = 1;
        this.pageSize = 100;
        this.regions = "440300";
        this.subCategories = "133,135,136,137,407,408,409,410,411,511,702,703,704,747,764,769,798,811";
    }
}
