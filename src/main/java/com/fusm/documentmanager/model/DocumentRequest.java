package com.fusm.documentmanager.model;

import lombok.*;

@Data
public class DocumentRequest {

    private String idUser;
    private String documentExtension;
    private String documentVersion;
    private String documentBytes;

}
