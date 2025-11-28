package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.mapper.AsistenciaSesionMapper;
import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;
import com.unu.ms.MsConsejo.repository.AsistenciaSesionRepository;
import com.unu.ms.MsConsejo.service.AsistenciaSesionService;

@Slf4j
@Service
public class AsistenciaSesionServiceImp implements AsistenciaSesionService {

        @Autowired
        AsistenciaSesionRepository asistenciaSesionRepository;

        @Autowired
        AsistenciaSesionMapper mapper;

        @Override
        public List<AsistenciaSesionResponse> listar() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'listar'");
        }

        @Override
        public AsistenciaSesionResponse obtenerPorId(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
        }

        @Override
        public AsistenciaSesionResponse crear(AsistenciaSesionRequest asistenciaSesionRequest) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'crear'");
        }

        @Override
        public void eliminar(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
        }

        @Override
        public AsistenciaSesionResponse actualizar(Integer id, AsistenciaSesionRequest asistenciaSesionActualizado) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
        }

        @Override
        public boolean existePorId(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'existePorId'");
        }

}