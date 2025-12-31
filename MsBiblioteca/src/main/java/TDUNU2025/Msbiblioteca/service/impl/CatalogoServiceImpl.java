package TDUNU2025.MsCatalogo.service.impl;

import TDUNU2025.MsCatalogo.model.entity.Catalogo;
import TDUNU2025.MsCatalogo.repository.CatalogoRepository;
import TDUNU2025.MsCatalogo.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    /**
     * @Cacheable: La primera vez va a la base de datos (Postgres).
     * Las siguientes veces, trae el resultado directamente desde Redis (RAM).
     * 'cacheNames' es el identificador de este grupo de datos en Redis.
     */
    @Override
    @Cacheable(cacheNames = "catalogos")
    public List<Catalogo> listarCatalogos() {
        // Este mensaje solo saldrá en consola cuando NO esté en caché.
        System.out.println("Consultando catálogos desde Postgres...");
        return catalogoRepository.findAll();
    }

    /**
     * Usamos 'key' para que Redis guarde cada catálogo por su ID individual.
     */
    @Override
    @Cacheable(cacheNames = "catalogos", key = "#id")
    public Optional<Catalogo> obtenerCatalogoPorId(Integer id) {
        System.out.println("Consultando catálogo ID: " + id + " desde Postgres...");
        return catalogoRepository.findById(id);
    }

    /**
     * @CacheEvict: Cuando guardas o actualizas, los datos en RAM quedan obsoletos.
     * 'allEntries = true' borra toda la lista "catalogos" de la RAM para que 
     * la próxima consulta traiga la información fresca de la BD.
     */
    @Override
    @CacheEvict(cacheNames = "catalogos", allEntries = true)
    public Catalogo guardarCatalogo(Catalogo catalogo) {
        return catalogoRepository.save(catalogo);
    }

    /**
     * También limpiamos el caché al eliminar un registro.
     */
    @Override
    @CacheEvict(cacheNames = "catalogos", allEntries = true)
    public void eliminarCatalogo(Integer id) {
        catalogoRepository.deleteById(id);
    }
}