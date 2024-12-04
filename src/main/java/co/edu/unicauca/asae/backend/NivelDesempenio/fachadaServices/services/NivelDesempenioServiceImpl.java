package co.edu.unicauca.asae.backend.NivelDesempenio.fachadaServices.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;
import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.repositories.NivelDesempenioRepository;
import co.edu.unicauca.asae.backend.NivelDesempenio.fachadaServices.DTO.NivelDesempenioDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NivelDesempenioServiceImpl implements INivelDesempenioService {
    private NivelDesempenioRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public NivelDesempenioServiceImpl(NivelDesempenioRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NivelDesempenioDTO> findAll() {
        List<NivelDesempenioEntity> listaNivel = this.servicioAccesoBaseDatos.findAll();
        return listaNivel.stream()
                .map(entity -> this.modelMapper.map(entity, NivelDesempenioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public NivelDesempenioDTO findById(Integer idNivelD) {
        NivelDesempenioEntity nivelDesempenio = this.servicioAccesoBaseDatos.findById(idNivelD).orElse(null);
        if(nivelDesempenio == null){
            throw new EntidadNoExisteException("Error, el nivel de desempeño con id " + idNivelD + " no existe");
        }
        return this.modelMapper.map(nivelDesempenio, NivelDesempenioDTO.class);
    }

    @Override
    public NivelDesempenioDTO save(NivelDesempenioDTO nivelD){
        NivelDesempenioEntity nivelDesempenio = this.modelMapper.map(nivelD, NivelDesempenioEntity.class);
        NivelDesempenioEntity nivelDesempenioGuardado = this.servicioAccesoBaseDatos.save(nivelDesempenio);
        return this.modelMapper.map(nivelDesempenioGuardado, NivelDesempenioDTO.class);
    }

    @Override
    public NivelDesempenioDTO update(Integer idNivelD, NivelDesempenioDTO nivelD){
        NivelDesempenioEntity existindNivel = this.servicioAccesoBaseDatos.findById(idNivelD).orElse(null);
        if(existindNivel == null){
            throw new EntidadNoExisteException("Error, el nivel de desempeño con id " + idNivelD + " no existe");
        }
        NivelDesempenioEntity nivelDesempenio = this.modelMapper.map(nivelD, NivelDesempenioEntity.class);
        nivelDesempenio.setId(idNivelD);
        NivelDesempenioEntity nivelDesempenioActualizado = this.servicioAccesoBaseDatos.save(nivelDesempenio);
        return this.modelMapper.map(nivelDesempenioActualizado, NivelDesempenioDTO.class);
    }

    @Override
    public boolean delete(Integer idNivelD){

        if(!this.servicioAccesoBaseDatos.existsById(idNivelD)){
            throw new EntidadNoExisteException("Error, el nivel de desempeño con id " + idNivelD + " no existe");
        }
        this.servicioAccesoBaseDatos.deleteById(idNivelD);
        return true;
    }
}
