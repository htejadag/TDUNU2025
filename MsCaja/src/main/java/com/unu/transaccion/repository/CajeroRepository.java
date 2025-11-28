package com.unu.transaccion.repository;
import com.unu.transaccion.model.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajeroRepository extends JpaRepository<Cajero, Integer> {}
