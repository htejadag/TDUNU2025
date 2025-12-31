package com.unu.ms.MsConsejo.repository;

import org.springframework.stereotype.Repository;

import com.unu.ms.MsConsejo.model.entity.Auditoria;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
    

}
