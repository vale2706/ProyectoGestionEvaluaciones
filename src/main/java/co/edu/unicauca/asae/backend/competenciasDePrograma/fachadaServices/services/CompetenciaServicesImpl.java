package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.services;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Repositorios.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories.CompetenciaRepository;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;

@Service
public class CompetenciaServicesImpl implements ICompetenciaServices {

    private CompetenciaRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public CompetenciaServicesImpl(CompetenciaRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CompetenciaDTO> findAll() {
        List<CompetenciaEntity> listaComp = this.servicioAccesoBaseDatos.findAll();
        return listaComp.stream()
                .map(entity -> this.modelMapper.map(entity, CompetenciaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompetenciaDTO findById(Integer idComp) {
        CompetenciaEntity objCompetencia = this.servicioAccesoBaseDatos.findById(idComp).orElse(null);
        if(objCompetencia == null){
            throw new EntidadNoExisteException("Error, la competencia con id " + idComp + " no existe");
        }
        return this.modelMapper.map(objCompetencia, CompetenciaDTO.class);
    }

    @Override
    public CompetenciaDTO save(CompetenciaDTO comp) {
        if(comp.getIdcomp() != null && servicioAccesoBaseDatos.existsById(comp.getIdcomp())){
            throw new ReglaNegocioExcepcion("Ya existe una competencia con ese codigo, no se permite crear");
        }
        CompetenciaEntity competenciaEntity = modelMapper.map(comp, CompetenciaEntity.class);
        CompetenciaEntity competenciaEntityGuardada = this.servicioAccesoBaseDatos.save(competenciaEntity);
        return this.modelMapper.map(competenciaEntityGuardada, CompetenciaDTO.class);
    }

    @Override
    public CompetenciaDTO update(Integer idComp, CompetenciaDTO comp) {
        CompetenciaEntity existingCompetencia = this.servicioAccesoBaseDatos.findById(idComp).orElse(null);
        if(existingCompetencia == null){
            throw new EntidadNoExisteException("Error, la competencia con id " + idComp + " no existe");
        }

        CompetenciaEntity competenciaEntity = modelMapper.map(comp, CompetenciaEntity.class);
        competenciaEntity.setIdComp(idComp);
        CompetenciaEntity competenciaEntityActualizada = this.servicioAccesoBaseDatos.save(competenciaEntity);

        return this.modelMapper.map(competenciaEntityActualizada, CompetenciaDTO.class);
    }

    @Override
    public boolean delete(Integer idComp) {
        if(!this.servicioAccesoBaseDatos.existsById(idComp)){
            throw new EntidadNoExisteException("Error, la competencia con id " + idComp + " no existe");
        }
        this.servicioAccesoBaseDatos.deleteById(idComp);
        return true;
    }

    @Override
    public List<CompetenciaDTO> findAllWithResultados() {
        // Usar el nuevo método con @Query que hace uso de LEFT JOIN FETCH
        List<CompetenciaEntity> listaComp = this.servicioAccesoBaseDatos.findAllWithResultados();
        return listaComp.stream()
                .map(entity -> this.modelMapper.map(entity, CompetenciaDTO.class))
                .collect(Collectors.toList());
    }
}
