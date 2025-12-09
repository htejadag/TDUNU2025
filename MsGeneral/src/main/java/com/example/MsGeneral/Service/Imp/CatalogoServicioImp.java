package com.example.MsGeneral.Service.Imp;


import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.MsGeneral.Model.Entidad.Catalogo;
import com.example.MsGeneral.Model.Request.CatalogoRequest;
import com.example.MsGeneral.Model.Response.CatalogoResponse;
import com.example.MsGeneral.Repository.CatalogoRepository;
import com.example.MsGeneral.Service.CatalogoService;

@Service
public class CatalogoServicioImp implements CatalogoService {

    @Autowired 
    private CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CatalogoResponse> listar() {
        return catalogoRepository.findAll()
                .stream()
                .map(model->modelMapper.map(model, CatalogoResponse.class))
                .toList();
    }

    @Override
    public List<CatalogoResponse> listarPorCategoria(String categoria) {
        return catalogoRepository.findByCategoria(categoria)
                .stream()
                .map(model->modelMapper.map(model,CatalogoResponse.class ))
                .toList();
    }

    @Override
    public CatalogoResponse obtenerPorId(String id) {
        return catalogoRepository.findById(id)
                .map(model->modelMapper.map(model, CatalogoResponse.class))
                .orElse(null);
    }

    @Override
    public CatalogoResponse guardar(CatalogoRequest request) {
        Catalogo model = modelMapper.map(request, Catalogo.class);

        //Codigo y orden automatico
        Integer nuevoCodigo = catalogoRepository.findTopByCategoriaOrderByCodigoDesc(request.getCategoria())
                            .map(c-> c.getCodigo() + 1)
                            .orElse(1);
        
        model.setCodigo(nuevoCodigo);
        model.setOrden(nuevoCodigo);
        
        Catalogo saved = catalogoRepository.save(model);

        CatalogoResponse response = modelMapper.map(saved , CatalogoResponse.class);

        return response;
    }

    @Override
    public CatalogoResponse actualizar(String id, CatalogoRequest request) {
        Catalogo modelExistente = catalogoRepository.findById(id).orElse(null);

        modelExistente.setCategoria(request.getCategoria());
        modelExistente.setDescripcion(request.getDescripcion());
        modelExistente.setAbreviatura(request.getAbreviatura());
        modelExistente.setActivo(request.isActivo());
        
        Catalogo saved = catalogoRepository.save(modelExistente);

        CatalogoResponse response = modelMapper.map(saved , CatalogoResponse.class);

        return response;
    }

    @Override
    public void cambiarEstado(String id, boolean activo) {
        
        Catalogo model = catalogoRepository.findById(id).orElse(null);

        model.setActivo(false);

        catalogoRepository.save(model);

    }

    @Override
    public void eliminar(String id) {
        catalogoRepository.deleteById(id);
    }

        
}
