package ReIngreso.Mr_pago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ReIngreso.Mr_pago.model.pagoModel;
import ReIngreso.Mr_pago.repository.pagoRepository;


    @Service
public class pagoService {
 
    @Autowired
    pagoRepository pagoRepository;
   
    public List<pagoModel>  getAll() {
       
        return (List<pagoModel>) pagoRepository.findAll();
    }

    
}
