package co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.repositories.ResultadosAprendizajeRepository;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;

@Service
public class ResultadosAprendizajeServicesImpl implements IResultadosAprendizajeServices {
    
    private ResultadosAprendizajeRepository servicioAccesoBaseDatos;

    private ModelMapper modelMapper;

    public ResultadosAprendizajeServicesImpl(ResultadosAprendizajeRepository servicioAccesoBaseDatos, ModelMapper modelMapper){
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }
@Override
public List<ResultadosAprendizajeDTO> findAll() {
    List<ResultadosAprendizajeEntity> listaRa = this.servicioAccesoBaseDatos.findAll();
    List<ResultadosAprendizajeDTO> raDTOs = listaRa.stream()
        .map(entity -> this.modelMapper.map(entity, ResultadosAprendizajeDTO.class))
        .collect(Collectors.toList());
    return raDTOs;
}

    @Override
    public ResultadosAprendizajeDTO findById(Integer idRa){
        ResultadosAprendizajeEntity objRa = this.servicioAccesoBaseDatos.findById(idRa);
        ResultadosAprendizajeDTO objRaDTOs = this.modelMapper.map(objRa, ResultadosAprendizajeDTO.class);
        return objRaDTOs;
    }

    @Override
    public ResultadosAprendizajeDTO save(ResultadosAprendizajeDTO ra){
        ResultadosAprendizajeEntity objRa = this.modelMapper.map(ra, ResultadosAprendizajeEntity.class);
        ResultadosAprendizajeEntity entidadGuardada = this.servicioAccesoBaseDatos.save(objRa);

        return this.modelMapper.map(entidadGuardada, ResultadosAprendizajeDTO.class);
    }

    @Override
    public ResultadosAprendizajeDTO update(Integer idRa, ResultadosAprendizajeDTO ra){
        
        ResultadosAprendizajeEntity raAux = new ResultadosAprendizajeEntity();
        raAux.setId(ra.getId());
        raAux.setDescripcion(ra.getDescripcion());

        ResultadosAprendizajeEntity objRaAct = this.servicioAccesoBaseDatos.update(idRa, raAux);
        ResultadosAprendizajeDTO raDTO = this.modelMapper.map(objRaAct, ResultadosAprendizajeDTO.class);

        return raDTO;
    }

    @Override
    public boolean delete(Integer idRa){
        return this.servicioAccesoBaseDatos.deleteById(idRa);
    }
}
