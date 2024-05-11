package com.fusm.documentmanager.controller;

import com.fusm.documentmanager.model.DocumentRequest;
import com.fusm.documentmanager.model.DocumentResponse;
import com.fusm.documentmanager.service.IDocumentManagerService;
import com.fusm.documentmanager.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone todos los servicios relacionados con los archivos dentro de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.DOCUMENT_MANAGER_ROUTE)
public class DocumentManagerController {

    @Autowired
    private IDocumentManagerService documentManagerService;


    /**
     * Guarda un archivo en el servidor
     * @param documentRequest Modelo que contiene la información del archivo a almacenar en el servidor
     * @return OK
     */
    @PostMapping
    public ResponseEntity<String> createFile(
            @RequestBody DocumentRequest documentRequest
        ) {
        return ResponseEntity.ok(documentManagerService.saveDocument(documentRequest));
    }

    /**
     * Exporta un archivo
     * @param documentId ID del documento
     * @return URL del archivo en el servidor
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> exportFile(
            @PathVariable("id") Integer documentId
            ) {
        return ResponseEntity.ok(documentManagerService.getDocumentByRoute(documentId));
    }

    /**
     * Eliminar archivos
     * @param files Lista de URL de los archivos que se desean eliminar de forma física y lógica dentro del servidor
     * @return OK
     */
    @PostMapping("/delete")
    public ResponseEntity<String> deleteFiles(
            @RequestBody List<String> files
            ) {
        documentManagerService.deleteDocumentMassive(files);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
