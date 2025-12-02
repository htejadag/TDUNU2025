package ReIngreso.Mr_pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ReIngreso.Mr_pago.model.pagoModel;
import ReIngreso.Mr_pago.repository.pagoRepository;


    @Service
public class pagoService {
 
    @Autowired
    pagoRepository pagoRepository;
   
    
      //Listar datos 
    public List<pagoModel>  getListar() {
       
        return (List<pagoModel>) pagoRepository.findAll();
    }

      //crear datos 
    public pagoModel createPago(pagoModel pago) {
       return pagoRepository.save(pago);
}

// Eliminar datos
public void eliminarPago(Integer id) {
    pagoRepository.deleteById(id);
}
 
//Buscar por id_Pago
public Optional<pagoModel> buscarPagoPorId(Integer id) {
    return pagoRepository.findById(id);
}


//Modificar datos
public pagoModel modificarPago(Integer id, pagoModel pagoActualizado) {
    return pagoRepository.findById(id).map(pago -> {
        // Aquí modificas los campos que quieres actualizar
        pago.setPago_Metodo(pagoActualizado.getPago_Metodo());
        pago.setPago_Monto(pagoActualizado.getPago_Monto());
        // Agrega más campos si tu modelo los tiene

        return pagoRepository.save(pago); // Guarda los cambios
    }).orElse(null); // Si no existe el ID
}



}
