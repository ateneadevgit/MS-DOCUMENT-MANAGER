package com.fusm.documentmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "Document_Manager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentManager {

    @Id
    @Column(name =  "id_document_manager", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentManagerId;

    @NonNull
    @Column(name = "id_user", nullable = false)
    private String userId;

    @NonNull
    @Column(name = "document_name", nullable = false)
    private String documentName;

    @NonNull
    @Column(name = "url", nullable = false)
    private String url;

    @NonNull
    @Column(name = "extension", nullable = false)
    private String extension;

    @NonNull
    @Column(name = "version", nullable = false)
    private String version;

    @NonNull
    @Column(name = "uuid", nullable = false)
    private String documentUuid;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @NonNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

}
