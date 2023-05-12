package com.rajiv.psl.web;

import lombok.Data;

@Data
public class Response {
    private String id;
    private String fileName;
    private String fileType;
    private String projectName;
    private String client;
    private String technology;
    private String productionUrl;
    private String hostUrl;
}
