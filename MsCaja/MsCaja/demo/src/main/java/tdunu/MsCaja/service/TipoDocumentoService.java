package tdunu.MsCaja.service;

import tdunu.MsCaja.model.request.TipoDocumentoRequest;
import tdunu.MsCaja.model.response.TipoDocumentoResponse;
import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoResponse> getAllTipoDocumentos();
    TipoDocumentoResponse getTipoDocumentoById(Integer id);
    TipoDocumentoResponse createTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest);
    TipoDocumentoResponse updateTipoDocumento(Integer id, TipoDocumentoRequest tipoDocumentoRequest);
    void deleteTipoDocumento(Integer id);
}