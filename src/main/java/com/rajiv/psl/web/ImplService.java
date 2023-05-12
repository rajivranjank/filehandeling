package com.rajiv.psl.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@Slf4j
public class ImplService implements IService {


    private Repo repository;

    public ImplService(Repo repository) {

        this.repository = repository;
    }

    @Override
    public WebEntity saveFile(MultipartFile file,RequestData requestData) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + fileName);
            }


            RequestData requestData1=new RequestData();
            WebEntity entity = new WebEntity(
                    fileName,
                    file.getContentType(),
                    file.getBytes(),
                    requestData.getClient(),
                    requestData.getHostUrl(),
                    requestData.getProductionUrl());



            return repository.save(entity);
        }

        catch (Exception e) {
            throw new Exception(e.getCause());

        }

    }
    @Override
    public WebEntity GetById(String fileId) throws Exception {
        return repository.findById(fileId).orElseThrow(()->new Exception("file not found = "+fileId));
    }

    @Override
    public List<WebEntity> getAll() {
        return repository.findAll();
    }
}