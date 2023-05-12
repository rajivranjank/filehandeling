package com.rajiv.psl.web;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Web")
public class WebEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileType;
    private String projectName;
    private String client;
    private String technology;
    private String productionUrl;
    private String hostUrl;
    @Lob
    private byte[] icon;


    @Lob
    private byte[] data;


//    for specify column constructor

    public WebEntity(String fileName, String fileType, byte[] data,String client,String hostUrl,String productionUrl) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.client=client;
        this.hostUrl=hostUrl;
        this.productionUrl=productionUrl;

    }

//    public WebEntity(String fileName, String contentType, byte[] bytes, String client, String hostUrl, String productionUrl) {
//    }

//    public WebEntity(String fileName, String contentType, byte[] bytes, String client, String hostUrl) {
//    }
}
