package tdunu.MsCaja.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsCaja.model.entity.Cajero;
import tdunu.MsCaja.model.request.CajeroRequest;
import tdunu.MsCaja.model.response.CajeroResponse;
import tdunu.MsCaja.repository.CajeroRepository;
import tdunu.MsCaja.service.CajeroService;

import java.util.List;
import java.util.Optional;

@Service
public class CajeroServiceImp implements CajeroService {

    @Autowired
    private CajeroRepository cajeroRepository;

    @Override
    public CajeroResponse createCajero(CajeroRequest cajeroRequest) {
        Cajero cajero = new Cajero();
        // Set properties from cajeroRequest to cajero
        // Example: cajero.setNombre(cajeroRequest.getNombre());
        cajero = cajeroRepository.save(cajero);
        return new CajeroResponse(cajero);
    }

    @Override
    public List<CajeroResponse> getAllCajeros() {
        List<Cajero> cajeros = cajeroRepository.findAll();
        // Convert List<Cajero> to List<CajeroResponse>
        return CajeroResponse.fromEntityList(cajeros);
    }

    @Override
    public CajeroResponse getCajeroById(int id) {
        Cajero cajero = cajeroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cajero not found"));
        return new CajeroResponse(cajero);
    }

    @Override
    public CajeroResponse updateCajero(int id, CajeroRequest cajeroRequest) {
        Cajero cajero = cajeroRepository.findById(id).orElseThrow(() -> new RuntimeException("Cajero not found"));
        // Update properties from cajeroRequest to cajero
        // Example: cajero.setNombre(cajeroRequest.getNombre());
        cajero = cajeroRepository.save(cajero);
        return new CajeroResponse(cajero);
    }

    @Override
    public void deleteCajero(int id) {
        cajeroRepository.deleteById(id);
    }
}