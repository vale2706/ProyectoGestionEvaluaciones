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
        List<CriteriosDesempenioDTO> desempenioDTOs = this.modelMapper.map(listaDesempenio,
                new TypeToken<List<CriteriosDesempenioDTO>>() {
                }.getType());
        return desempenioDTOs;
    }

    @Override
    public CriteriosDesempenioDTO findById(Integer idDesempenio) {
        CriteriosDesempenioEntity objDesempenio = this.servicioAccesoBaseDatos.findById(idDesempenio);
        if (objDesempenio == null) {
            throw new EntidadNoExisteException("Error, el criterio de desempeño con id " + idDesempenio + " no existe");
        }
        CriteriosDesempenioDTO desempenioDTO = this.modelMapper.map(objDesempenio, CriteriosDesempenioDTO.class);
        return desempenioDTO;
    }

    @Override
    public CriteriosDesempenioDTO save(CriteriosDesempenioDTO desempenio){
        CriteriosDesempenioDTO desempenioDTO = null;
        
        if(this.servicioAccesoBaseDatos.existeCriteriosDesempenio(desempenio.getId()) == true){
            System.out.println("ID de la desempenio"+desempenio.getId());

            ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                "Exíste un criterio de desempeño con ese ID, no se permite crear criterio de desempeño");
            throw objExcepcion;
        }else{
            CriteriosDesempenioEntity asignaturaEntity = this.modelMapper.map(desempenio, CriteriosDesempenioEntity.class);

            CriteriosDesempenioEntity asignaturaEntityGuardada = this.servicioAccesoBaseDatos.save(asignaturaEntity);
            desempenioDTO = this.modelMapper.map(asignaturaEntityGuardada, CriteriosDesempenioDTO.class);
        }
        return desempenioDTO;
    }

    @Override
    public CriteriosDesempenioDTO update(Integer idDesempenio, CriteriosDesempenioDTO desempenio){

        CriteriosDesempenioDTO desempenioDTO = null;

        if(this.servicioAccesoBaseDatos.existeCriteriosDesempenio(idDesempenio) == true){

            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idDesempenio).getId();
            Integer idNuevo = desempenio.getId();
            if(idAnterior.equals(idNuevo) == false && this.servicioAccesoBaseDatos.existeCriteriosDesempenio(desempenio.getId()) == true){
                ReglaNegocioExcepcion objException = new ReglaNegocioExcepcion(
                    "Existe un criterio de desempeño con el codigo registrado, no se permite actualizar");
                throw objException;
            }else{
                CriteriosDesempenioEntity desempenioAux = new CriteriosDesempenioEntity();
                desempenioAux.setId(desempenio.getId());
                desempenioAux.setDescripcion(desempenio.getDescripcion());
                desempenioAux.setPonderacionDesemp(desempenio.getPonderacionDesemp());
                desempenioAux.setNivelDesemp(desempenio.getNivelDesemp());

                CriteriosDesempenioEntity objDesempenioAct = this.servicioAccesoBaseDatos.update(idDesempenio, desempenioAux);
                desempenioDTO = this.modelMapper.map(objDesempenioAct, CriteriosDesempenioDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, el criterio de desempeño a actualizar no existe");
            throw objException;
        }
        return desempenioDTO;
    }

    @Override
    public boolean delete(Integer idDesempenio){

        boolean bandera = false;
        if(this.servicioAccesoBaseDatos.existeCriteriosDesempenio(idDesempenio) == true){
            bandera = this.servicioAccesoBaseDatos.deleteById(idDesempenio);
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, el criterio de desempeño a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
