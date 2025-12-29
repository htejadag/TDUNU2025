package com.unu.TDUNU2025.Msbiblioteca.service;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import java.util.List;
import java.util.Optional;

public interface DetalleUsuarioService {
    List<DetalleUsuario> listarTodo();
    Optional<DetalleUsuario> obtenerPorId(Integer idUsuario);
    DetalleUsuario guardar(DetalleUsuario detalle);
    void eliminar(Integer idUsuario);
}