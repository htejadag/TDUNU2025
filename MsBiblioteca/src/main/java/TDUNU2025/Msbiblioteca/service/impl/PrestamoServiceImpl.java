package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import TDUNU2025.Msbiblioteca.repository.PrestamoRepository;
import TDUNU2025.Msbiblioteca.service.PrestamoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Optional<Prestamo> obtenerPrestamoPorId(Integer id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPrestamo(Integer id) {
        prestamoRepository.deleteById(id);
    }
}