package TDUNU2025.Msbiblioteca.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // ********** CONFIGURACIÓN EXPLÍCITA PARA DETALLELIBRO **********
        // Esto resuelve el error "matches multiple source property hierarchies" (El conflicto de IDs)
        
        PropertyMap<DetalleLibro, DetalleLibroResponse> detalleLibroMap = new PropertyMap<DetalleLibro, DetalleLibroResponse>() {
            @Override
            protected void configure() {
                // Indicamos explícitamente a ModelMapper que para llenar el campo 'idLibro' 
                // del DTO (destination), debe ir a la entidad origen (source), 
                // obtener el objeto Libro (getLibro()) y de allí tomar el ID (getIdLibro()).
                
                // Mapeo Correcto: source.getLibro().getIdLibro()  ---> destination.setIdLibro()
                map().setIdLibro(source.getLibro().getIdLibro());
            }
        };

        // Registrar el mapeo en la instancia de ModelMapper
        modelMapper.addMappings(detalleLibroMap);
        // ********************************************************************************

        return modelMapper;
    }
}