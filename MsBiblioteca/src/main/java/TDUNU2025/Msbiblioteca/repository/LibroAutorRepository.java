    package tdunu2025.msbiblioteca.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import tdunu2025.msbiblioteca.model.entity.LibroAutor;
    @Repository
    public interface LibroAutorRepository extends JpaRepository<LibroAutor, Long> {

        boolean existsByLibro_IdLibroAndAutor_IdAutorAndRol(Long idLibro,Long idAutor, String rol );

    }
