package co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.services;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
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
    return listaRa.stream()
            .map(entity -> this.modelMapper.map(entity, ResultadosAprendizajeDTO.class))
            .collect(Collectors.toList());
}

    @Override
    public ResultadosAprendizajeDTO findById(Integer idRa){
        ResultadosAprendizajeEntity objRa = this.servicioAccesoBaseDatos.findById(idRa).orElse(null);
        if(objRa == null){
            throw new NullPointerException("Error, el ResultadosAprendizaje con id " + idRa + " no existe");
        }
        return this.modelMapper.map(objRa, ResultadosAprendizajeDTO.class);
    }

    @Override
    public ResultadosAprendizajeDTO save(ResultadosAprendizajeDTO ra) {
        /*
        System.out.println("se para "+ ra.getId());
        if (ra.getId() != null && servicioAccesoBaseDatos.existsById(ra.getId())) {
            throw new ReglaNegocioExcepcion("Ya existe un ResultadosAprendizaje con ese id, no se permite actualizar");
        }*/
        System.out.println(ra.getDescripcion());
        ResultadosAprendizajeEntity resultadosAprendizajeEntity = modelMapper.map(ra, ResultadosAprendizajeEntity.class);
        ResultadosAprendizajeEntity resultadosAprendizajeEntityGuardada = servicioAccesoBaseDatos.save(resultadosAprendizajeEntity);
        return modelMapper.map(resultadosAprendizajeEntityGuardada, ResultadosAprendizajeDTO.class);
    }
    @Override
    public ResultadosAprendizajeDTO update(Integer idRa, ResultadosAprendizajeDTO ra){

        ResultadosAprendizajeEntity existingRa = this.servicioAccesoBaseDatos.findById(idRa).orElse(null);
        if(existingRa == null){
            throw new NullPointerException("Error, el ResultadosAprendizaje con id " + idRa + " no existe");
        }
        ResultadosAprendizajeEntity raEntity = this.modelMapper.map(ra, ResultadosAprendizajeEntity.class);
        raEntity.setId(idRa);
        ResultadosAprendizajeEntity raEntityActualizada = this.servicioAccesoBaseDatos.save(raEntity);
        ResultadosAprendizajeDTO raDTO = this.modelMapper.map(raEntityActualizada, ResultadosAprendizajeDTO.class);
        return raDTO;
    }

    @Override
    public boolean delete(Integer idRa){
        if(!this.servicioAccesoBaseDatos.existsById(idRa)){
            throw new NullPointerException("Error, el ResultadosAprendizaje con id " + idRa + " no existe");
        }
        this.servicioAccesoBaseDatos.deleteById(idRa);
        return true;
    }
}
