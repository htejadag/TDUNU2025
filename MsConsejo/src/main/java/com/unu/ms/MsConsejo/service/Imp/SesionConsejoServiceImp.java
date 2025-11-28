package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.mapper.SesionConsejoMapper;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.SesionConsejoService;

@Slf4j
@Service
public class SesionConsejoServiceImp implements SesionConsejoService {

        @Autowired
        SesionConsejoRepository sesionConsejoRepository;

        @Autowired
        SesionConsejoMapper mapper;

        @Override
        public List<SesionConsejoResponse> listar() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'listar'");
        }

        @Override
        public SesionConsejoResponse obtenerPorId(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
        }

        @Override
        public SesionConsejoResponse crear(SesionConsejoRequest sesionConsejoRequest) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'crear'");
        }

        @Override
        public void eliminar(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
        }

        @Override
        public SesionConsejoResponse actualizar(Integer id, SesionConsejoRequest sesionConsejoActualizado) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
        }

        @Override
        public boolean existePorId(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'existePorId'");
        }

}