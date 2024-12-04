package co.edu.unicauca.asae.backend.CriterioDesempenio.fachadaServices.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;
import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.repositories.CriteriosDesempenioRepository;
import co.edu.unicauca.asae.backend.CriterioDesempenio.fachadaServices.DTO.CriteriosDesempenioDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CriteriosDesempenioServiceImpl implements ICriteriosDesempenioService{
    private CriteriosDesempenioRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public CriteriosDesempenioServiceImpl(CriteriosDesempenioRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CriteriosDesempenioDTO> findAll() {
        List<CriteriosDesempenioEntity> listaDesempenio = this.servicioAccesoBaseDatos.findAll();
        return listaDesempenio.stream()
                .map(entity -> this.modelMapper.map(entity, CriteriosDesempenioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CriteriosDesempenioDTO findById(Integer idDesempenio) {
        CriteriosDesempenioEntity desempenioEntity = this.servicioAccesoBaseDatos.findById(idDesempenio).orElse(null);
        if(desempenioEntity == null){
            throw new EntidadNoExisteException("Error, el desempeño con id " + idDesempenio + " no existe");
        }
        return this.modelMapper.map(desempenioEntity, CriteriosDesempenioDTO.class);
    }

    @Override
    public CriteriosDesempenioDTO save(CriteriosDesempenioDTO desempenio){
        CriteriosDesempenioEntity desempenioEntity = this.modelMapper.map(desempenio, CriteriosDesempenioEntity.class);
        CriteriosDesempenioEntity desempenioEntityGuardada = this.servicioAccesoBaseDatos.save(desempenioEntity);
        return this.modelMapper.map(desempenioEntityGuardada, CriteriosDesempenioDTO.class);
    }

    @Override
    public CriteriosDesempenioDTO update(Integer idDesempenio, CriteriosDesempenioDTO desempenio){

        CriteriosDesempenioEntity existingDesempenio = this.servicioAccesoBaseDatos.findById(idDesempenio).orElse(null);
        if(existingDesempenio == null){
            throw new EntidadNoExisteException("Error, el desempeño con id " + idDesempenio + " no existe");
        }

        CriteriosDesempenioEntity desempenioEntity = this.modelMapper.map(desempenio, CriteriosDesempenioEntity.class);
        desempenioEntity.setId(idDesempenio);
        CriteriosDesempenioEntity desempenioEntityActualizada = this.servicioAccesoBaseDatos.save(desempenioEntity);
        return this.modelMapper.map(desempenioEntityActualizada, CriteriosDesempenioDTO.class);
    }

    @Override
    public boolean delete(Integer idDesempenio){

        if(!this.servicioAccesoBaseDatos.existsById(idDesempenio)){
            throw new EntidadNoExisteException("Error, el desempeño con id " + idDesempenio + " no existe");
        }

        this.servicioAccesoBaseDatos.deleteById(idDesempenio);
        return true;
    }
}
