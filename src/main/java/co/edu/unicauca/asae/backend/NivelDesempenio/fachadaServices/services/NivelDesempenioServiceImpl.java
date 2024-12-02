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
        List<NivelDesempenioEntity> listaAsignatura = this.servicioAccesoBaseDatos.findAll();
        List<NivelDesempenioDTO> AsignaturaDTOs = this.modelMapper.map(listaAsignatura,
                new TypeToken<List<NivelDesempenioDTO>>() {
                }.getType());
        return AsignaturaDTOs;
    }

    @Override
    public NivelDesempenioDTO findById(Integer idNivelD) {
        NivelDesempenioEntity objNivelD = this.servicioAccesoBaseDatos.findById(idNivelD);
        if (objNivelD == null) {
            throw new EntidadNoExisteException("Error, el Nivel de desempeño con id " + idNivelD + " no existe");
        }
        NivelDesempenioDTO nivelDesDTO = this.modelMapper.map(objNivelD, NivelDesempenioDTO.class);
        return nivelDesDTO;
    }

    @Override
    public NivelDesempenioDTO save(NivelDesempenioDTO nivelD){
        NivelDesempenioDTO nivelDesDTO = null;
        
        if(this.servicioAccesoBaseDatos.existeNivelDesempenio(nivelD.getId()) == true){
            System.out.println("ID de la nivelD"+nivelD.getId());

            ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                "Exíste una Nivel de desempeño con ese ID, no se permite crear Nivel de desempeño");
            throw objExcepcion;
        }else{
            NivelDesempenioEntity nivelDEntity = this.modelMapper.map(nivelD, NivelDesempenioEntity.class);

            NivelDesempenioEntity nivelDesempenioEntityGuardada = this.servicioAccesoBaseDatos.save(nivelDEntity);
            nivelDesDTO = this.modelMapper.map(nivelDesempenioEntityGuardada, NivelDesempenioDTO.class);
        }
        return nivelDesDTO;
    }

    @Override
    public NivelDesempenioDTO update(Integer idNivelD, NivelDesempenioDTO nivelD){

        NivelDesempenioDTO nivelDesDTO = null;

        if(this.servicioAccesoBaseDatos.existeNivelDesempenio(idNivelD) == true){

            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idNivelD).getId();
            Integer idNuevo = nivelD.getId();
            if(idAnterior.equals(idNuevo) == false && this.servicioAccesoBaseDatos.existeNivelDesempenio(nivelD.getId()) == true){
                ReglaNegocioExcepcion objException = new ReglaNegocioExcepcion(
                    "Existe un Nivel de desempeño con el codigo registrado, no se permite actualizar");
                throw objException;
            }else{
                NivelDesempenioEntity nivelDAux = new NivelDesempenioEntity();
                nivelDAux.setId(nivelD.getId());
                nivelDAux.setNomDescriptivo(nivelD.getNomDescriptivo());

                NivelDesempenioEntity objNivelDAct = this.servicioAccesoBaseDatos.update(idNivelD, nivelDAux);
                nivelDesDTO = this.modelMapper.map(objNivelDAct, NivelDesempenioDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, el Nivel de desempeño a actualizar no existe");
            throw objException;
        }
        return nivelDesDTO;
    }

    @Override
    public boolean delete(Integer idNivelD){

        boolean bandera = false;
        if(this.servicioAccesoBaseDatos.existeNivelDesempenio(idNivelD) == true){
            bandera = this.servicioAccesoBaseDatos.deleteById(idNivelD);
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, el Nivel de desempeño a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
