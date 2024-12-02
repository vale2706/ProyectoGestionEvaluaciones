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
        List<RubricaDTO> RubricaDTOs = this.modelMapper.map(listarRubrica,
                new TypeToken<List<RubricaDTO>>() {
                }.getType());
        return RubricaDTOs;
    }

    @Override
    public RubricaDTO findById(Integer idRubrica) {
        RubricaEntity objRubrica = this.servicioAccesoBaseDatos.findById(idRubrica);
        if (objRubrica == null) {
            throw new EntidadNoExisteException("Error, la rubrica con id " + idRubrica + " no existe");
        }
        RubricaDTO rubricaDTO = this.modelMapper.map(objRubrica, RubricaDTO.class);
        return rubricaDTO;
    }

    @Override
    public RubricaDTO save(RubricaDTO rubrica){
        RubricaDTO nivelDesDTO = null;
        
        if(this.servicioAccesoBaseDatos.existeRubrica(rubrica.getId()) == true){
            System.out.println("ID de la rubrica "+rubrica.getId());

            ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                "Exíste una rubrica con ese ID, no se permite crear rubrica");
            throw objExcepcion;
        }else{
            RubricaEntity rubricaEntity = this.modelMapper.map(rubrica, RubricaEntity.class);

            RubricaEntity rubricaEntityGuardada = this.servicioAccesoBaseDatos.save(rubricaEntity);
            nivelDesDTO = this.modelMapper.map(rubricaEntityGuardada, RubricaDTO.class);
        }
        return nivelDesDTO;
    }

    @Override
    public RubricaDTO update(Integer idRubrica, RubricaDTO rubrica){

        RubricaDTO nivelDesDTO = null;

        if(this.servicioAccesoBaseDatos.existeRubrica(idRubrica) == true){

            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idRubrica).getId();
            Integer idNuevo = rubrica.getId();
            if(idAnterior.equals(idNuevo) == false && this.servicioAccesoBaseDatos.existeRubrica(rubrica.getId()) == true){
                ReglaNegocioExcepcion objException = new ReglaNegocioExcepcion(
                    "Existe una rubrica con el id registrado, no se permite actualizar");
                throw objException;
            }else{
                RubricaEntity rubricaAux = new RubricaEntity();
                rubricaAux.setId(rubrica.getId());
                rubricaAux.setNomDescriptivo(rubrica.getNomDescriptivo());
                rubricaAux.setCriterioDes(rubrica.getCriterioDes());

                RubricaEntity objrubricaAct = this.servicioAccesoBaseDatos.update(idRubrica, rubricaAux);
                nivelDesDTO = this.modelMapper.map(objrubricaAct, RubricaDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, la rubrica a actualizar no existe");
            throw objException;
        }
        return nivelDesDTO;
    }

    @Override
    public boolean delete(Integer idRubrica){

        boolean bandera = false;
        if(this.servicioAccesoBaseDatos.existeRubrica(idRubrica) == true){
            bandera = this.servicioAccesoBaseDatos.deleteById(idRubrica);
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, la rubrica a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
