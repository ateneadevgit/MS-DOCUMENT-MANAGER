package com.fusm.documentmanager.service;

import org.springframework.stereotype.Service;

@Service
public interface IDocumentTemplateService {

    String getTemplate(Integer templateId);

}
