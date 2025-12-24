package com.microservice.MsCatalogoTesis.service;

import com.microservice.MsCatalogoTesis.model.Catalogo;
import com.microservice.MsCatalogoTesis.repository.CatalogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones del catálogo
 */
@Service
@RequiredArgsConstructor
public class CatalogoService {

    private final CatalogoRepository catalogoRepository;

    /**
     * Obtener todos los elementos del catálogo
     */
    public List<Catalogo> obtenerTodos() {
        return catalogoRepository.findAllByOrderByGrupoAscOrdenAsc();
    }

    /**
     * Obtener elementos de un grupo específico
     */
    public List<Catalogo> obtenerPorGrupo(String grupo) {
        return catalogoRepository.findByGrupoOrderByOrdenAsc(grupo);
    }

    /**
     * Obtener elementos activos de un grupo
     */
    public List<Catalogo> obtenerActivosPorGrupo(String grupo) {
        return catalogoRepository.findByGrupoAndActivoTrueOrderByOrdenAsc(grupo);
    }

    /**
     * Obtener un elemento específico por grupo y código
     */
    public Optional<Catalogo> obtenerPorGrupoYCodigo(String grupo, String codigo) {
        return catalogoRepository.findByGrupoAndCodigo(grupo, codigo);
    }

    /**
     * Crear o actualizar un elemento del catálogo
     */
    public Catalogo guardar(Catalogo catalogo) {
        return catalogoRepository.save(catalogo);
    }

    /**
     * Eliminar un elemento del catálogo
     */
    public void eliminar(String id) {
        catalogoRepository.deleteById(id);
    }

    /**
     * Verificar si existe un elemento
     */
    public boolean existe(String grupo, String codigo) {
        return catalogoRepository.existsByGrupoAndCodigo(grupo, codigo);
    }
}
