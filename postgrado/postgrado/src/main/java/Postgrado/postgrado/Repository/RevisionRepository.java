package Postgrado.postgrado.Repository;

import Postgrado.postgrado.Model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Integer> {
    List<Revision> findByActivoTrue();
}
