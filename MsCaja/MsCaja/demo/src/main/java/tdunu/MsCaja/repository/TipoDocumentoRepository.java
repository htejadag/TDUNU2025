package tdunu.MsCaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsCaja.model.entity.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {
}