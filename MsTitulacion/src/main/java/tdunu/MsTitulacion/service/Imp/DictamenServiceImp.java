package tdunu.MsTitulacion.service.Imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdunu.MsTitulacion.repository.DictamenRepository;

@Service
@Slf4j
public class DictamenServiceImp {
    
    @Autowired
    private DictamenRepository dictamenRepository;

    @Autowired
    private ModelMapper modelMapper;

    

}
