package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;

import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories.CompetenciaRepository;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

@Service
public class CompetenciaServicesImpl implements ICompetenciaServices {
    
    private CompetenciaRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public CompetenciaServicesImpl(CompetenciaRepository servicioAccesoBaseDatos, ModelMapper modelMapper){
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CompetenciaDTO> findAll(){
        List<CompetenciaEntity> listaComp = this.servicioAccesoBaseDatos.findAll();
        List<CompetenciaDTO> compDTOs = this.modelMapper.map(listaComp, new TypeToken<List<CompetenciaDTO>>(){
        }.getType());
        return compDTOs;
    }

    @Override
    public CompetenciaDTO findById(Integer idComp){
        CompetenciaEntity objCompetencia = this.servicioAccesoBaseDatos.findById(idComp);
        if(objCompetencia == null){
            throw new EntidadNoExisteException("Error, la competencia con id "+ idComp + "no existe");
        }
        CompetenciaDTO objCompDTOs = this.modelMapper.map(objCompetencia, CompetenciaDTO.class);
        return objCompDTOs;
    }

    @Override
    public CompetenciaDTO save(CompetenciaDTO comp){
        CompetenciaDTO competenciaDTO = null;
        if(this.servicioAccesoBaseDatos.existeCompetencia(comp.getIdcomp()) == true){
            System.out.println("ID de la Competencia "+ comp.getIdcomp());

            ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                "Exíste una Competencia con ese ID, no se permite crear Competencia");
            throw objExcepcion;
        }else{
            CompetenciaEntity competenciaEntity = this.modelMapper.map(comp, CompetenciaEntity.class);

            CompetenciaEntity competenciaEntityGuardada = this.servicioAccesoBaseDatos.save(competenciaEntity);
            competenciaDTO = this.modelMapper.map(competenciaEntityGuardada, CompetenciaDTO.class);
        }
        return competenciaDTO;
    }

    @Override
    public CompetenciaDTO update(Integer idComp, CompetenciaDTO comp){
        
        CompetenciaDTO competenciaDTO = null;

        if(this.servicioAccesoBaseDatos.existeCompetencia(idComp) == true){
            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idComp).getIdComp();
            Integer idNuevo = comp.getIdcomp();
            if(idAnterior.equals(idNuevo) == false && this.servicioAccesoBaseDatos.existeCompetencia(comp.getIdcomp()) == true){
                ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                    "Existe una competencia con el codigo registrado, no se permite actualizar");
                throw objExcepcion;
            }else{
                CompetenciaEntity compAux = new CompetenciaEntity();
                compAux.setIdComp(comp.getIdcomp());
                compAux.setDescripcion(comp.getDescripcion());
                compAux.setNivel(CompetenciaEntity.Nivel.valueOf(comp.getNivel().name()));

                CompetenciaEntity objCompetenciaAct = this.servicioAccesoBaseDatos.update(idComp, compAux);
                competenciaDTO = this.modelMapper.map(objCompetenciaAct, CompetenciaDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, la competencia a actualizar no existe");
            throw objException;
        }
        return competenciaDTO;
    }

    @Override
    public boolean delete(Integer idComp){
        boolean bandera = false;
        if (this.servicioAccesoBaseDatos.existeCompetencia(idComp) == true) {
            bandera = this.servicioAccesoBaseDatos.deleteById(idComp);
        } else {
            EntidadNoExisteException objException = new EntidadNoExisteException(
                    "Error, la competencia a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
