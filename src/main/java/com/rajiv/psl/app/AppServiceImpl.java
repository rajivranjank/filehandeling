package com.rajiv.psl.app;

import com.rajiv.psl.web.WebEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AppServiceImpl implements AppService{
    @Autowired
    private AppRepository repository;


    @Override
    public ApplicationEntity saveApp(MultipartFile file, RequestData rd) throws IOException {
        String appName= StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (appName.contains("..")){
                throw new Exception("file contains invalid path name"+appName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        RequestData requestData=new RequestData();
        ApplicationEntity application=new ApplicationEntity(

                appName,
                file.getContentType(),
                file.getBytes(),
                rd.getHostUrl(),
                rd.getProdUrl()
        );


        return repository.save(application);
    }

    @Override
    public List<ApplicationEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public ApplicationEntity getById(String appId) throws Exception {
        return repository.findById(appId).orElseThrow(()->new Exception("file not found = "+appId));
    }

    @Override
    public ApplicationEntity getByName(String appName) {
        return repository.findByName(appName);
    }



}
