package com.rajiv.psl.app;

import com.rajiv.psl.app.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Repository
public interface AppRepository extends JpaRepository<ApplicationEntity,String> {

    ApplicationEntity findByName(String appName);


}
