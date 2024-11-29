package co.edu.unicauca.asae.backend.Asignatura.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.repositories.AsignaturaRepository;
import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.backend.Asignatura.fachadaServices.DTO.AsignaturaDTO;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService {
    
    private AsignaturaRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public AsignaturaServiceImpl(AsignaturaRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AsignaturaDTO> findAll() {
        List<AsignaturaEntity> listaAsignatura = this.servicioAccesoBaseDatos.findAll();
        List<AsignaturaDTO> AsignaturaDTOs = this.modelMapper.map(listaAsignatura,
                new TypeToken<List<AsignaturaDTO>>() {
                }.getType());
        return AsignaturaDTOs;
    }

    @Override
    public AsignaturaDTO findById(Integer idAsignatura) {
        AsignaturaEntity objAsignatura = this.servicioAccesoBaseDatos.findById(idAsignatura);
        if (objAsignatura == null) {
            throw new EntidadNoExisteException("Error, la boleta con id " + idAsignatura + " no existe");
        }
        AsignaturaDTO boletaCineDTO = this.modelMapper.map(objAsignatura, AsignaturaDTO.class);
        return boletaCineDTO;
    }

    @Override
    public AsignaturaDTO save(AsignaturaDTO asignatura){
        AsignaturaDTO asignaturaDTO = null;
        
        if(this.servicioAccesoBaseDatos.existeAsignatura(asignatura.getId()) == true){
            System.out.println("ID de la asignatura"+asignatura.getId());

            ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                "Exíste una Asignatura con ese ID, no se permite crear Asignatura");
            throw objExcepcion;
        }else{
            AsignaturaEntity asignaturaEntity = this.modelMapper.map(asignatura, AsignaturaEntity.class);

            AsignaturaEntity asignaturaEntityGuardada = this.servicioAccesoBaseDatos.save(asignaturaEntity);
            asignaturaDTO = this.modelMapper.map(asignaturaEntityGuardada, AsignaturaDTO.class);
        }
        return asignaturaDTO;
    }

    @Override
    public AsignaturaDTO update(Integer idAsignatura, AsignaturaDTO asignatura){

        AsignaturaDTO asignaturaDTO = null;

        if(this.servicioAccesoBaseDatos.existeAsignatura(idAsignatura) == true){

            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idAsignatura).getId();
            Integer idNuevo = asignatura.getId();
            if(idAnterior.equals(idNuevo) == false && this.servicioAccesoBaseDatos.existeAsignatura(asignatura.getId()) == true){
                ReglaNegocioExcepcion objException = new ReglaNegocioExcepcion(
                    "Existe una asignatura con el codigo registrado, no se permite actualizar");
                throw objException;
            }else{
                AsignaturaEntity asignaturaAux = new AsignaturaEntity();
                asignaturaAux.setId(asignatura.getId());
                asignaturaAux.setNombre(asignatura.getNombre());
                asignaturaAux.setDescripcion(asignatura.getDescripcion());
                asignaturaAux.setCreditos(asignatura.getCreditos());
                asignaturaAux.setSemestre(asignatura.getSemestre());
                asignaturaAux.setCompA(asignatura.getCompA());

                AsignaturaEntity objAsignaturaAct = this.servicioAccesoBaseDatos.update(idAsignatura, asignaturaAux);
                asignaturaDTO = this.modelMapper.map(objAsignaturaAct, AsignaturaDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, la asignatura a actualizar no existe");
            throw objException;
        }
        return asignaturaDTO;
    }

    @Override
    public boolean delete(Integer idAsignatura){

        boolean bandera = false;
        if(this.servicioAccesoBaseDatos.existeAsignatura(idAsignatura) == true){
            bandera = this.servicioAccesoBaseDatos.deleteById(idAsignatura);
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, la asignatura a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
