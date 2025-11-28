package tdunu.MsCaja.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsCaja.model.entity.TipoDocumento;
import tdunu.MsCaja.model.request.TipoDocumentoRequest;
import tdunu.MsCaja.model.response.TipoDocumentoResponse;
import tdunu.MsCaja.repository.TipoDocumentoRepository;
import tdunu.MsCaja.service.TipoDocumentoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoServiceImp implements TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<TipoDocumentoResponse> getAllTipoDocumentos() {
        List<TipoDocumento> list = tipoDocumentoRepository.findAll();
        return list.stream().map(td -> new TipoDocumentoResponse(td.getIdTipoDocumento(), td.getNombreDocumento())).collect(Collectors.toList());
    }

    @Override
    public TipoDocumentoResponse getTipoDocumentoById(Integer id) {
        TipoDocumento td = tipoDocumentoRepository.findById(id).orElseThrow(() -> new RuntimeException("TipoDocumento not found"));
        return new TipoDocumentoResponse(td.getIdTipoDocumento(), td.getNombreDocumento());
    }

    @Override
    public TipoDocumentoResponse createTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest) {
        TipoDocumento td = new TipoDocumento();
        td.setNombreDocumento(tipoDocumentoRequest.getNombreDocumento());
        td = tipoDocumentoRepository.save(td);
        return new TipoDocumentoResponse(td.getIdTipoDocumento(), td.getNombreDocumento());
    }

    @Override
    public TipoDocumentoResponse updateTipoDocumento(Integer id, TipoDocumentoRequest tipoDocumentoRequest) {
        TipoDocumento td = tipoDocumentoRepository.findById(id).orElseThrow(() -> new RuntimeException("TipoDocumento not found"));
        td.setNombreDocumento(tipoDocumentoRequest.getNombreDocumento());
        td = tipoDocumentoRepository.save(td);
        return new TipoDocumentoResponse(td.getIdTipoDocumento(), td.getNombreDocumento());
    }

    @Override
    public void deleteTipoDocumento(Integer id) {
        tipoDocumentoRepository.deleteById(id);
    }
}