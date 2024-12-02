package co.edu.unicauca.asae.backend.Docente.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models.DocenteEntity;
import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.repositories.DocenteRepository;
import co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO.DocenteDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

public class DocenteServiceImpl implements IDocenteService {
    private DocenteRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public DocenteServiceImpl(DocenteRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DocenteDTO> findAll() {
        List<DocenteEntity> listaAsignatura = this.servicioAccesoBaseDatos.findAll();
        List<DocenteDTO> AsignaturaDTOs = this.modelMapper.map(listaAsignatura,
                new TypeToken<List<DocenteDTO>>() {
                }.getType());
        return AsignaturaDTOs;
    }

    @Override
    public DocenteDTO findById(Integer idDocente) {
        DocenteEntity objDocente = this.servicioAccesoBaseDatos.findById(idDocente);
        if (objDocente == null) {
            throw new EntidadNoExisteException("Error, la Docente con id " + idDocente + " no existe");
        }
        DocenteDTO boletaCineDTO = this.modelMapper.map(objDocente, DocenteDTO.class);
        return boletaCineDTO;
    }

    @Override
    public DocenteDTO save(DocenteDTO docente){
        DocenteDTO asignaturaDTO = null;
        
        if(this.servicioAccesoBaseDatos.existeDocente(docente.getId()) == true){
            System.out.println("ID de la docente"+docente.getId());

            ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion(
                "Exíste una Docente con ese ID, no se permite crear Docente");
            throw objExcepcion;
        }else{
            DocenteEntity asignaturaEntity = this.modelMapper.map(docente, DocenteEntity.class);

            DocenteEntity asignaturaEntityGuardada = this.servicioAccesoBaseDatos.save(asignaturaEntity);
            asignaturaDTO = this.modelMapper.map(asignaturaEntityGuardada, DocenteDTO.class);
        }
        return asignaturaDTO;
    }

    @Override
    public DocenteDTO update(Integer idDocente, DocenteDTO docente){

        DocenteDTO asignaturaDTO = null;

        if(this.servicioAccesoBaseDatos.existeDocente(idDocente) == true){

            Integer idAnterior = this.servicioAccesoBaseDatos.findById(idDocente).getId();
            Integer idNuevo = docente.getId();
            if(idAnterior.equals(idNuevo) == false && this.servicioAccesoBaseDatos.existeDocente(docente.getId()) == true){
                ReglaNegocioExcepcion objException = new ReglaNegocioExcepcion(
                    "Existe una docente con el id registrado, no se permite actualizar");
                throw objException;
            }else{
                DocenteEntity docenteAux = new DocenteEntity();
                docenteAux.setId(docente.getId());
                docenteAux.setNombreCompleto(docente.getNombreCompleto());
                docenteAux.setTituloDocente(docente.getTituloDocente());
                docenteAux.setEmail(docente.getEmail());
                docenteAux.setTipoId(docente.getTipoId());
                docenteAux.setTipoDocente(DocenteEntity.TipoDocente.valueOf(docente.getTipoDocente().name()));

                DocenteEntity objAsignaturaAct = this.servicioAccesoBaseDatos.update(idDocente, docenteAux);
                asignaturaDTO = this.modelMapper.map(objAsignaturaAct, DocenteDTO.class);
            }
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, la docente a actualizar no existe");
            throw objException;
        }
        return asignaturaDTO;
    }

    @Override
    public boolean delete(Integer idDocente){

        boolean bandera = false;
        if(this.servicioAccesoBaseDatos.existeDocente(idDocente) == true){
            bandera = this.servicioAccesoBaseDatos.deleteById(idDocente);
        }else{
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Error, la docente a eliminar no existe");
            throw objException;
        }
        return bandera;
    }
}
