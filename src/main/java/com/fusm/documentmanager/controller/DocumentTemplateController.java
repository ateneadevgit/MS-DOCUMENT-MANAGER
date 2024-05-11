package com.fusm.documentmanager.controller;

import com.fusm.documentmanager.service.IDocumentTemplateService;
import com.fusm.documentmanager.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que expone los servicios necesarios para los PDF de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.DOCUMENT_MANAGER_ROUTE + "/pdf")
public class DocumentTemplateController {

    @Autowired
    private IDocumentTemplateService documentTemplateService;


    /**
     * Obtiene la plantilla de un PDF
     * @param templateId ID de la plantilla
     * @return plantilla
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getDocumentTemplate(
            @PathVariable("id") Integer templateId
    ) {
        return ResponseEntity.ok(documentTemplateService.getTemplate(templateId));
    }

}
