package co.edu.unicauca.asae.backend.Usuario.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;

import co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.models.CoordinadorEntity;
import co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.repositories.CoordinadorRepository;
import co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO.CoordinadorDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

@Service
public class CoordinadorServicesImpl implements ICoordinadorServices {
    
    private CoordinadorRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public CoordinadorServicesImpl(CoordinadorRepository servicioAccesoBaseDatos, ModelMapper modelMapper){
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CoordinadorDTO> findAll(){
        List<CoordinadorEntity> listaCoordi = this.servicioAccesoBaseDatos.findAll();
        List<CoordinadorDTO> coordiDTOs = this.modelMapper.map(listaCoordi, new TypeToken<List<CoordinadorDTO>>(){
        }.getType());
        return coordiDTOs;
    }

    @Override
    public CoordinadorDTO findById(Integer idCoordi){
        CoordinadorEntity objCoordi = this.servicioAccesoBaseDatos.findById(idCoordi);
        if(objCoordi == null){
            throw new EntidadNoExisteException("Error, la Coordinador con id "+ idCoordi + "no existe");
        }
        CoordinadorDTO objCompDTOs = this.modelMapper.map(objCoordi, CoordinadorDTO.class);
        return objCompDTOs;
    }

    @Override
    public CoordinadorDTO save(CoordinadorDTO coordi){
        CoordinadorDTO CoordinadorDTO = null;
        if(this.servicioAccesoBaseDatos.existeCorreo(coordi.getCorreo()) == true){
            System.out.println("El correo del Coordinador "+ coordi.getCorreo());

            ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                "Exíste un Coordinador con ese Correo, no se permite crear Coordinador");
            throw objExcepcion;
        }else{
            CoordinadorEntity CoordinadorEntity = this.modelMapper.map(coordi, CoordinadorEntity.class);

            CoordinadorEntity coordinadorEntityGuardada = this.servicioAccesoBaseDatos.save(CoordinadorEntity);
            CoordinadorDTO = this.modelMapper.map(coordinadorEntityGuardada, CoordinadorDTO.class);
        }
        return CoordinadorDTO;
    }

    @Override
    public CoordinadorDTO update(Integer idCoordi, CoordinadorDTO coordi){
        
        CoordinadorDTO CoordinadorDTO = null;

        if(this.servicioAccesoBaseDatos.existeCorreo(coordi.getCorreo()) == true){
            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idCoordi).getId();
            Integer idNuevo = coordi.getId();
            if(idAnterior.equals(idNuevo) == false && this.servicioAccesoBaseDatos.existeCorreo(coordi.getCorreo()) == true){
                ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                    "Existe un Coordinador con el correo registrado, no se permite actualizar");
                throw objExcepcion;
            }else{
                CoordinadorEntity coordiAux = new CoordinadorEntity();
                coordiAux.setId(coordi.getId());
                coordiAux.setNombre(coordi.getNombre());
                coordiAux.setApellido(coordi.getApellido());
                coordiAux.setCorreo(coordi.getCorreo());
                coordiAux.setProgramas(coordi.getProgramas());

                CoordinadorEntity objCoordinadorAct = this.servicioAccesoBaseDatos.update(idCoordi, coordiAux);
                CoordinadorDTO = this.modelMapper.map(objCoordinadorAct, CoordinadorDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, el Coordinador a actualizar no existe");
            throw objException;
        }
        return CoordinadorDTO;
    }

    @Override
    public boolean delete(Integer idCoordi){
        boolean bandera = false;
        if (this.servicioAccesoBaseDatos.existeCoordinador(idCoordi) == true) {
            bandera = this.servicioAccesoBaseDatos.deleteById(idCoordi);
        } else {
            EntidadNoExisteException objException = new EntidadNoExisteException(
                    "Error, el Coordinador a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
