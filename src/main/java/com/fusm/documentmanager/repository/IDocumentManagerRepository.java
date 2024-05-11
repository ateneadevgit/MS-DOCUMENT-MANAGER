package com.fusm.documentmanager.repository;

import com.fusm.documentmanager.entity.DocumentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDocumentManagerRepository extends JpaRepository<DocumentManager, Integer> {


}
