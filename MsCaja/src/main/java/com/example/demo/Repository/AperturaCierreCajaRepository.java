package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.AperturaCierreCaja;

@Repository
public interface AperturaCierreCajaRepository extends JpaRepository<AperturaCierreCaja, Integer> {

}
