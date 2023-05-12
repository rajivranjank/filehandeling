package com.rajiv.psl.web;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IService {
    WebEntity saveFile(MultipartFile file,RequestData requestData) throws Exception;
    WebEntity GetById(String fileId) throws Exception;
    List<WebEntity> getAll();
}
