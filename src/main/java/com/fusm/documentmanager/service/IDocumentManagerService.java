package com.fusm.documentmanager.service;

import com.fusm.documentmanager.entity.DocumentManager;
import com.fusm.documentmanager.model.DocumentRequest;
import com.fusm.documentmanager.model.DocumentResponse;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IDocumentManagerService {
    String saveDocument(DocumentRequest documentRequest);
    DocumentResponse getDocumentByRoute(Integer route);
    void deleteDocumentMassive(List<String> fileName);
}
