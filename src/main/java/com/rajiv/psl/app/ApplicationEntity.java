package com.rajiv.psl.app;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application")
public class ApplicationEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String appId;
    @Column(name = "name")
    private String name;
    private String fileType;
    @Lob
    private byte[] app;
//    private String download;
    private String hostUrl;
    private String prodUrl;


    public ApplicationEntity(String appName, String fileType, byte[] app, String hostUrl,String prodUrl) {
        this.name=appName;
        this.fileType=fileType;
        this.app=app;
//        this.download=download;
        this.hostUrl=hostUrl;
        this.prodUrl=prodUrl;
    }
}
