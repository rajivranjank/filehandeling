package com.rajiv.psl.app;

import com.rajiv.psl.web.WebEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AppService {
 ApplicationEntity saveApp(MultipartFile file,RequestData requestData) throws IOException;
 List<ApplicationEntity>getAll();

 ApplicationEntity getById(String appId) throws Exception;

 ApplicationEntity getByName(String appName);



}
