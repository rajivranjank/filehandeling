package com.rajiv.psl.app;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/app/")
public class AppController {
    @Autowired
    private AppServiceImpl appService;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private  AppRepository repository;


    private Logger logger= LoggerFactory.getLogger(AppController.class);

    @PostMapping("save")
    public ResponseEntity<?>saveApplication(
            @RequestParam("app")MultipartFile file,
            @RequestParam ("information") String information
            ) throws IOException {

        ApplicationEntity application=null;
        String downloadUrl="";
        

        RequestData requestData=null;
        requestData=mapper.readValue(information,RequestData.class);

        application = appService.saveApp(file,requestData);
        downloadUrl=ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/app/download/").
                path(application.getAppId()).toUriString();
        logger.info(downloadUrl);
        requestData.setDownload(downloadUrl);
        application.setProdUrl(requestData.getProdUrl());
        application.setHostUrl(requestData.getHostUrl());
        return ResponseEntity.ok("SUCCESSFULLY ADDED ----");
    }

    @GetMapping("all")
    public ResponseEntity<?>getall(){

        String Url="http://localhost:9192/api/app/download/";

        List<ResponceData> all = appService.getAll().stream().map(a->{
            ResponceData responceData=new ResponceData();
            responceData.setId(a.getAppId());
            responceData.setAppName(a.getName());
            responceData.setFileType(a.getFileType());
            responceData.setProdUrl(a.getProdUrl());
            responceData.setHostUrl(a.getHostUrl());
            responceData.setDownloadUrl(Url+a.getAppId());
            logger.info("id is = "+a.getAppId());
            logger.info(responceData.getDownloadUrl());
            return responceData;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(all);
    }
    @GetMapping("/download/{appId}")
    public ResponseEntity<Resource>getById(@PathVariable String appId) throws Exception {

        ApplicationEntity application =null;

        application= appService.getById(appId);
        logger.warn("somthing went wrong in downloadurl= "+application);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(application.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "application; name=\""+application.getName()+"\"")
                .body(new ByteArrayResource(application.getApp()));
    }
    public ResponseEntity<?>getByName(String appName){
        ApplicationEntity byName = appService.getByName(appName);
        return  ResponseEntity.ok("successfully downloaded");
    }

}
