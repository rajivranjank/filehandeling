package com.rajiv.psl.web;

import lombok.Data;

@Data
public class RequestData {
    private String projectName;
    private String client;
    private String technology;
    private String productionUrl;
    private String hostUrl;
}
