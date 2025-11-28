package tdunu.MsCaja.service;

import tdunu.MsCaja.model.entity.Cajero;
import tdunu.MsCaja.model.request.CajeroRequest;
import tdunu.MsCaja.model.response.CajeroResponse;

import java.util.List;

public interface CajeroService {
    CajeroResponse createCajero(CajeroRequest cajeroRequest);
    CajeroResponse updateCajero(int id, CajeroRequest cajeroRequest);
    void deleteCajero(int id);
    CajeroResponse getCajeroById(int id);
    List<CajeroResponse> getAllCajeros();
}