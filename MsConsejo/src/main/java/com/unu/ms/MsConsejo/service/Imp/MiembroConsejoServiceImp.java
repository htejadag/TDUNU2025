package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.mapper.MiembroConsejoMapper;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRespository;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;

@Slf4j
@Service
public class MiembroConsejoServiceImp implements MiembroConsejoService {

        @Autowired
        MiembroConsejoRespository miembroConsejoRespository;

        @Autowired
        MiembroConsejoMapper mapper;

        @Override
        public List<MiembroConsejoResponse> listar() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'listar'");
        }

        @Override
        public MiembroConsejoResponse obtenerPorId(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
        }

        @Override
        public MiembroConsejoResponse crear(MiembroConsejoRequest miembroConsejoRequest) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'crear'");
        }

        @Override
        public void eliminar(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
        }

        @Override
        public MiembroConsejoResponse actualizar(Integer id, MiembroConsejoRequest miembroConsejoActualizado) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
        }

        @Override
        public boolean existePorId(Integer id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'existePorId'");
        }

}