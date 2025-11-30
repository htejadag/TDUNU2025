package ReIngreso.Mr_pago.controller;

import org.springframework.web.bind.annotation.RestController;

import ReIngreso.Mr_pago.model.pagoModel;
import ReIngreso.Mr_pago.service.pagoService;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/pago")
public class pagoController {
 
    @Autowired
    pagoService pagoService;


    @GetMapping (value = "Listar")
    public List<pagoModel> get() {
        return pagoService.getListar();
    }

    @PostMapping("/Crear")
public pagoModel registrarPago(@RequestBody pagoModel pago) {
    return pagoService.createPago(pago);
}


@DeleteMapping("/Eliminar/{id}")
public ResponseEntity<String> eliminarPago(@PathVariable Integer id) {
    pagoService.eliminarPago(id);
    return ResponseEntity.ok("Pago eliminado correctamente");
}


@GetMapping("/Buscar/{id}")
public ResponseEntity<?> buscarPago(@PathVariable Integer id) {
    Optional<pagoModel> pago = pagoService.buscarPagoPorId(id);

    if (pago.isPresent()) {
        return ResponseEntity.ok(pago.get());
    } else {
        // Enviamos mensaje personalizado con status 404
        return ResponseEntity.status(404).body("El pago con ID " + id + " no existe");
    }
}

@PutMapping("/Modificar/{id}")
public ResponseEntity<?> modificarPago(@PathVariable Integer id, @RequestBody pagoModel pagoActualizado) {

    pagoModel pagoModificado = pagoService.modificarPago(id, pagoActualizado);

    if (pagoModificado != null) {
        return ResponseEntity.ok(pagoModificado); // Éxito
    } else {
        return ResponseEntity.status(404).body("No existe el pago con ID " + id);
    }
}


}