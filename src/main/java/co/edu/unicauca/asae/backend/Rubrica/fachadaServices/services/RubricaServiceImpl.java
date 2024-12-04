package co.edu.unicauca.asae.backend.Rubrica.fachadaServices.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;
import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.repositories.RubricaRepository;
import co.edu.unicauca.asae.backend.Rubrica.fachadaServices.DTO.RubricaDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RubricaServiceImpl implements IRubricaService {
    private RubricaRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public RubricaServiceImpl(RubricaRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RubricaDTO> findAll() {
        List<RubricaEntity> listarRubrica = this.servicioAccesoBaseDatos.findAll();
        return listarRubrica.stream()
                .map(entity -> this.modelMapper.map(entity, RubricaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RubricaDTO findById(Integer idRubrica) {
        RubricaEntity rubricaEntity = this.servicioAccesoBaseDatos.findById(idRubrica).orElse(null);
        if(rubricaEntity == null){
            throw new EntidadNoExisteException("Error, la rubrica con id " + idRubrica + " no existe");
        }
        return this.modelMapper.map(rubricaEntity, RubricaDTO.class);
    }

    @Override
    public RubricaDTO save(RubricaDTO rubrica){

        RubricaEntity rubricaEntity = this.modelMapper.map(rubrica, RubricaEntity.class);
        RubricaEntity rubricaEntityGuardada = this.servicioAccesoBaseDatos.save(rubricaEntity);
        return this.modelMapper.map(rubricaEntityGuardada, RubricaDTO.class);
    }

    @Override
    public RubricaDTO update(Integer idRubrica, RubricaDTO rubrica){

        RubricaEntity rubricaExistente = this.servicioAccesoBaseDatos.findById(idRubrica).orElse(null);
        if(rubricaExistente == null){
            throw new EntidadNoExisteException("Error, la rubrica a actualizar no existe");
        }

        RubricaEntity rubricaActualizada = this.modelMapper.map(rubrica, RubricaEntity.class);
        rubricaActualizada.setId(idRubrica);
        RubricaEntity rubricaEntityGuardada = this.servicioAccesoBaseDatos.save(rubricaActualizada);
        return this.modelMapper.map(rubricaEntityGuardada, RubricaDTO.class);
    }

    @Override
    public boolean delete(Integer idRubrica){
        if(!this.servicioAccesoBaseDatos.existsById(idRubrica)){
            throw new EntidadNoExisteException("Error, la rubrica a eliminar no existe");
        }

        this.servicioAccesoBaseDatos.deleteById(idRubrica);
        return true;
    }
}
