package com.fusm.documentmanager.service.impl;

import com.fusm.documentmanager.entity.DocumentManager;
import com.fusm.documentmanager.external.IExternalService;
import com.fusm.documentmanager.model.DocumentRequest;
import com.fusm.documentmanager.model.DocumentResponse;
import com.fusm.documentmanager.model.SettingRequest;
import com.fusm.documentmanager.repository.IDocumentManagerRepository;
import com.fusm.documentmanager.service.IDocumentManagerService;
import com.fusm.documentmanager.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class DocumentManagerService implements IDocumentManagerService {

    @Autowired
    private IDocumentManagerRepository documentManagerRepository;

    @Autowired
    private IExternalService externalService;

    @Override
    public String saveDocument(DocumentRequest documentRequest) {
        String response = null;
        try {
            String documentUuid = UUID.randomUUID().toString();
            String documentName = defineDocumentName(documentUuid, documentRequest.getDocumentVersion(), documentRequest.getDocumentExtension());
            String initUrl = getOriginPath(Constant.FILE_ROUTE);
            String documentUrl = createFile(documentName, documentRequest.getDocumentBytes(), initUrl);
            if (!documentUrl.isEmpty()) {
                String newUrl = getOriginPath(Constant.FINAL_FILE_ROUTE);
                String finalUrl = documentUrl.replace(initUrl, newUrl);
                documentManagerRepository.save(
                        DocumentManager.builder()
                                .userId(documentRequest.getIdUser())
                                .documentName(documentName)
                                .url(finalUrl)
                                .extension(documentRequest.getDocumentExtension())
                                .version(documentRequest.getDocumentVersion())
                                .documentUuid(documentUuid)
                                .createdAt(new Date())
                                .createdBy(documentRequest.getIdUser())
                                .enabled(true)
                                .build()
                );
                response = finalUrl;
            }
        } catch (Exception ex) {}
        return response;
    }

    @Override
    public DocumentResponse getDocumentByRoute(Integer documentId) {
        DocumentResponse documentResponse = null;
        Optional<DocumentManager> documentOptional = documentManagerRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            DocumentManager document = documentOptional.get();
            documentResponse = DocumentResponse.builder()
                    .route(document.getUrl())
                    .build();
        }
        return documentResponse;
    }

    @Override
    public void deleteDocumentMassive(List<String> fileName) {
        for (String name : fileName) {
            name = name.replace(getOriginPath(Constant.FINAL_FILE_ROUTE), getOriginPath(Constant.FILE_ROUTE));
            File file = new File(name);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private String defineDocumentName(String documentUuid, String documentVersion, String extension) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day + "_" + documentUuid + "_" + documentVersion + "." + extension;
    }

    private String definePath() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return year + "_" + month;
    }

    private String getOriginPath(String originType) {
        return externalService.getSetting(new SettingRequest(originType));
    }

    private String createFile(String documentName, String documentBytes, String basePath) {
        Path finalPath;
        try {
            basePath += "/" + definePath();
            finalPath = Paths.get(basePath);
            if (!Files.exists(finalPath)) Files.createDirectories(finalPath);
            Path filePath = Paths.get(basePath + "/" + documentName);
            byte[] byteDecoded = Base64.getDecoder().decode(documentBytes);
            Files.createFile(filePath);
            Files.write(filePath, byteDecoded, StandardOpenOption.WRITE);
            return basePath + "/" + documentName;
        } catch (Exception e) {
            return "";
        }

    }

}
