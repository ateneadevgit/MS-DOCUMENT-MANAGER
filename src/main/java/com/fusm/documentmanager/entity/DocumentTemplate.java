package com.fusm.documentmanager.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "Document_template")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTemplate {

    @Id
    @Column(name =  "id_document_template", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documenttemplateId;

    @NonNull
    @Column(name = "template_name", length = 300, nullable = false)
    private String templateName;

    @Column(name = "description", length = 500, nullable = true)
    private String description;

    @NonNull
    @Column(name = "template_body", length = 10000, nullable = false)
    private String templateBody;

}
