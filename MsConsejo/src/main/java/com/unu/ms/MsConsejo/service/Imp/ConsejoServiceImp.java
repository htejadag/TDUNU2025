package com.unu.ms.MsConsejo.service.Imp;


import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.service.ConsejoService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsejoServiceImp implements ConsejoService {
    
    @Autowired
    private ConsejoRepository consejoRepository;
    
    @Override
    public List<ConsejoModel> listar() {
        return (List<ConsejoModel>) consejoRepository.findAll();
    }

       

}
