package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.entity.Catalogo;
import TDUNU2025.Msbiblioteca.model.request.CatalogoRequest;
import TDUNU2025.Msbiblioteca.model.response.CatalogoResponse;
import TDUNU2025.Msbiblioteca.service.CatalogoService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private ModelMapper modelMapper;

    // 1. LISTAR TODOS
    @GetMapping
    public ResponseEntity<ResponseBase<List<CatalogoResponse>>> listarCatalogos() {
        List<Catalogo> catalogos = catalogoService.listarCatalogos();
        List<CatalogoResponse> responseList = catalogos.stream()
                .map(c -> modelMapper.map(c, CatalogoResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, responseList));
    }

    // 2. OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<CatalogoResponse>> obtenerPorId(@PathVariable Integer id) {
        Optional<Catalogo> catalogoOpt = catalogoService.obtenerCatalogoPorId(id);
        if (catalogoOpt.isPresent()) {
            CatalogoResponse res = modelMapper.map(catalogoOpt.get(), CatalogoResponse.class);
            return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, res));
        }
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_ERROR, Mensaje.MENSAJE_NO_ENCONTRADO, null), HttpStatus.NOT_FOUND);
    }

    // 3. GUARDAR
    @PostMapping
    public ResponseEntity<ResponseBase<CatalogoResponse>> guardar(@RequestBody CatalogoRequest request) {
        Catalogo entity = modelMapper.map(request, Catalogo.class);
        Catalogo guardado = catalogoService.guardarCatalogo(entity);
        CatalogoResponse res = modelMapper.map(guardado, CatalogoResponse.class);
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, res), HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<CatalogoResponse>> actualizar(@PathVariable Integer id, @RequestBody CatalogoRequest request) {
        Optional<Catalogo> existente = catalogoService.obtenerCatalogoPorId(id);
        if (existente.isPresent()) {
            Catalogo entity = modelMapper.map(request, Catalogo.class);
            entity.setIdCatalogo(id);
            Catalogo actualizado = catalogoService.guardarCatalogo(entity);
            CatalogoResponse res = modelMapper.map(actualizado, CatalogoResponse.class);
            return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, res));
        }
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_ERROR, Mensaje.MENSAJE_NO_ENCONTRADO, null), HttpStatus.NOT_FOUND);
    }

    // 5. ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
        if (catalogoService.obtenerCatalogoPorId(id).isPresent()) {
            catalogoService.eliminarCatalogo(id);
            return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
        }
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_ERROR, Mensaje.MENSAJE_NO_ENCONTRADO, null), HttpStatus.NOT_FOUND);
    }
}