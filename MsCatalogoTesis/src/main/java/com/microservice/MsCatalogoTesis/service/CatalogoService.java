package com.microservice.MsCatalogoTesis.service;

import com.microservice.MsCatalogoTesis.model.Catalogo;
import com.microservice.MsCatalogoTesis.repository.CatalogoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogoService {

    private final CatalogoRepository catalogoRepository;

    @Cacheable(value = "catalogo", key = "'all'")
    public List<Catalogo> obtenerTodos() {
        log.debug("Consultando todos los elementos del catálogo desde MongoDB");
        return catalogoRepository.findAllByOrderByGrupoAscOrdenAsc();
    }

    @Cacheable(value = "catalogoPorGrupo", key = "#grupo")
    public List<Catalogo> obtenerPorGrupo(String grupo) {
        log.debug("Consultando elementos del grupo '{}' desde MongoDB", grupo);
        return catalogoRepository.findByGrupoOrderByOrdenAsc(grupo);
    }

    @Cacheable(value = "catalogoActivosPorGrupo", key = "#grupo")
    public List<Catalogo> obtenerActivosPorGrupo(String grupo) {
        log.debug("Consultando elementos activos del grupo '{}' desde MongoDB", grupo);
        return catalogoRepository.findByGrupoAndActivoTrueOrderByOrdenAsc(grupo);
    }

    @Cacheable(value = "catalogoElemento", key = "#grupo + ':' + #codigo")
    public Optional<Catalogo> obtenerPorGrupoYCodigo(String grupo, String codigo) {
        log.debug("Consultando elemento grupo='{}', codigo='{}' desde MongoDB", grupo, codigo);
        return catalogoRepository.findByGrupoAndCodigo(grupo, codigo);
    }

    @CacheEvict(value = { "catalogo", "catalogoPorGrupo", "catalogoActivosPorGrupo",
            "catalogoElemento" }, allEntries = true)
    public Catalogo guardar(Catalogo catalogo) {
        log.debug("Guardando elemento del catálogo e invalidando caché");
        return catalogoRepository.save(catalogo);
    }

    @CacheEvict(value = { "catalogo", "catalogoPorGrupo", "catalogoActivosPorGrupo",
            "catalogoElemento" }, allEntries = true)
    public void eliminar(String id) {
        log.debug("Eliminando elemento del catálogo e invalidando caché");
        catalogoRepository.deleteById(id);
    }

    public boolean existe(String grupo, String codigo) {
        return catalogoRepository.existsByGrupoAndCodigo(grupo, codigo);
    }
}
