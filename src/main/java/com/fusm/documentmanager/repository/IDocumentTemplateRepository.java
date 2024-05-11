package com.fusm.documentmanager.repository;

import com.fusm.documentmanager.entity.DocumentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentTemplateRepository extends JpaRepository<DocumentTemplate, Integer> {
}
