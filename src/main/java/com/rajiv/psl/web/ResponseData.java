package com.rajiv.psl.web;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {

//    private String id;
    private String fileName;
    private String fileType;
    private String projectName;
    private String client;
    private String technology;
    private String productionUrl;
    private String hostUrl;

    public ResponseData(String fileName, String downloadUrl, String productionUrl, String hostUrl, String client, String technology, String contentType, long size) {
    }



}
