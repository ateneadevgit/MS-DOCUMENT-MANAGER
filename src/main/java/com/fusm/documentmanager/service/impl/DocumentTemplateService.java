package com.fusm.documentmanager.service.impl;

import com.fusm.documentmanager.entity.DocumentTemplate;
import com.fusm.documentmanager.repository.IDocumentTemplateRepository;
import com.fusm.documentmanager.service.IDocumentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentTemplateService implements IDocumentTemplateService {

    @Autowired
    private IDocumentTemplateRepository documentTemplateRepository;


    @Override
    public String getTemplate(Integer templateId) {
        Optional<DocumentTemplate> templateOptional = documentTemplateRepository.findById(templateId);
        String response = "";
        if (templateOptional.isPresent()) response = templateOptional.get().getTemplateBody();
        return response;
    }

}
