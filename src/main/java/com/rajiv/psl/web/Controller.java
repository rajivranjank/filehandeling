package com.rajiv.psl.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/web/")
public class Controller {

    @Autowired
    private ObjectMapper mapper;

    private ImplService service;

    public Controller(ImplService service) {
        this.service = service;
    }

   private Logger logger= LoggerFactory.getLogger(Controller.class);

    @PostMapping("save")
    public ResponseEntity<?> uploadfile(
            @RequestParam("file")MultipartFile file,
            @RequestParam ("data") String userData
    ) throws Exception {
        WebEntity webApp = null;
        String downloadUrl = "";
        RequestData requestData = null;


        requestData = mapper.readValue(userData, RequestData.class);
        webApp = service.saveFile(file, requestData);

        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/web/download/")
                .path(webApp.getId())
                .toUriString();
        logger.info("url is = " + downloadUrl);

        webApp.setClient(requestData.getClient());
        webApp.setHostUrl(requestData.getHostUrl());
        String  finalDownloadUrl = downloadUrl;
        webApp.setProductionUrl(downloadUrl);
        logger.info("production url is = "+webApp.getProductionUrl());

        logger.info("web", userData);
        logger.info("hostUrl is = " + webApp.getHostUrl(),
                webApp.getClient(),
                webApp.getId());
        ResponseData responseData = new ResponseData();
        responseData.setFileType(webApp.getFileType());
        responseData.setFileName(webApp.getFileName());
        responseData.setHostUrl(webApp.getHostUrl());

        return ResponseEntity.ok(responseData);
    }
    @GetMapping("download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        WebEntity webEntity=null;
        webEntity=service.GetById(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(webEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + webEntity.getFileName()
                                + "\"")
                .body(new ByteArrayResource(webEntity.getData()));
    }

    @GetMapping("all")
    public List<Response>getAll(){
//        String  downloadUrl="";
//        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/api/web/download/")
////                .path(webApp.getId())
//                .path(String.valueOf(service.getAll().stream().map(a->{
//                    return a.getId();
//                }).collect(Collectors.toList())))
//                .toUriString();
//        String  finalDownloadUrl = downloadUrl;
        String downloadUrl="http://localhost:9192/api/web/download/";
        return service.getAll().stream().map(d->{

            Response responseData=new Response();

            responseData.setId(d.getId());
            responseData.setFileName(d.getFileName());
            responseData.setFileType(d.getFileType());
            responseData.setClient(d.getClient());
//            responseData.setHostUrl(d.getHostUrl());
            responseData.setHostUrl(downloadUrl+d.getId());
            responseData.setTechnology(d.getTechnology());
            responseData.setProductionUrl(d.getProductionUrl());
            responseData.setProjectName(d.getProjectName());

            return responseData;
        }).collect(Collectors.toList());

    }
}
