package co.edu.unicauca.asae.backend.Docente.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.reflect.TypeToken;

import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models.DocenteEntity;
import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.repositories.DocenteRepository;
import co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO.DocenteDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Repositorios.UserRepository;

@Service
public class DocenteServicesImpl implements IDocenteServices {
    

    private DocenteRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;
   
    @Autowired
    private UserRepository userRepository;

    public DocenteServicesImpl(DocenteRepository servicioAccesoBaseDatos, ModelMapper modelMapper){
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DocenteDTO> findAll(){
        List<DocenteEntity> listaDocente = this.servicioAccesoBaseDatos.findAll();
        List<DocenteDTO> docenteDTOs = this.modelMapper.map(listaDocente, new TypeToken<List<DocenteDTO>>(){
        }.getType());
        return docenteDTOs;
    }

    @Override
    public DocenteDTO findById(Integer idDocente){
        DocenteEntity objCoordi = this.servicioAccesoBaseDatos.findById(idDocente);
        if(objCoordi == null){
            throw new EntidadNoExisteException("Error, la Docente con id "+ idDocente + "no existe");
        }
        DocenteDTO objCompDTOs = this.modelMapper.map(objCoordi, DocenteDTO.class);
        return objCompDTOs;
    }

    @Override
    public DocenteDTO save(DocenteDTO docente) {
        DocenteDTO docenteDTO = null;

        // Verificar si el correo ya existe
        if (userRepository.existsByEmail(docente.getEmail())) {
            System.out.println("El correo del Docente " + docente.getEmail());
            throw new ReglaNegocioExcepcion("Exíste un Docente con ese Correo, no se permite crear Docente");
        }

        // Convertir el DTO a entidad
        DocenteEntity docenteEntity = this.modelMapper.map(docente, DocenteEntity.class);
        // Guardar en la base de datos
        DocenteEntity docenteEntityGuardada = this.servicioAccesoBaseDatos.save(docenteEntity);
        // Convertir la entidad guardada en DTO
        docenteDTO = this.modelMapper.map(docenteEntityGuardada, DocenteDTO.class);

        return docenteDTO;
    }

    @Override
    public DocenteDTO update(Integer idDocente, DocenteDTO docente){
        
        DocenteDTO DocenteDTO = null;

        if(userRepository.existsByEmail(docente.getEmail())){
            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idDocente).getId();
            Integer idNuevo = docente.getId();
            if(idAnterior.equals(idNuevo) == false && userRepository.existsByEmail(docente.getEmail())){
                ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                    "Existe un Docente con el correo registrado, no se permite actualizar");
                throw objExcepcion;
            }else{
                DocenteEntity docenteAux = new DocenteEntity();
                docenteAux.setId(docente.getId());
                docenteAux.setNombreCompleto(docente.getNombreCompleto());
                docenteAux.setTipoId(DocenteEntity.TipoId.valueOf(docente.getTipoId().name()));
                docenteAux.setTipoDocente(DocenteEntity.TipoDocente.valueOf(docente.getTipoDocente().name()));
                

                DocenteEntity objDocenteAct = this.servicioAccesoBaseDatos.update(idDocente, docenteAux);
                DocenteDTO = this.modelMapper.map(objDocenteAct, DocenteDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, el Docente a actualizar no existe");
            throw objException;
        }
        return DocenteDTO;
    }

    @Override
    public boolean delete(Integer idDocente){
        boolean bandera = false;
        if (this.servicioAccesoBaseDatos.existeDocente(idDocente) == true) {
            bandera = this.servicioAccesoBaseDatos.deleteById(idDocente);
        } else {
            EntidadNoExisteException objException = new EntidadNoExisteException(
                    "Error, el Docente a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
