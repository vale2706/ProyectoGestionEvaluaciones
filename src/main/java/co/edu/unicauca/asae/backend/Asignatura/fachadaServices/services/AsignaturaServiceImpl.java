package co.edu.unicauca.asae.backend.Asignatura.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
import co.edu.unicauca.asae.backend.Asignatura.fachadaServices.DTO.AsignaturaDTO;
import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.repositories.AsignaturaRepository;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService {

    private final AsignaturaRepository servicioAccesoBaseDatos;
    private final ModelMapper modelMapper;

    public AsignaturaServiceImpl(AsignaturaRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AsignaturaDTO> findAll() {
        List<AsignaturaEntity> listaAsignatura = servicioAccesoBaseDatos.findAll();
        return modelMapper.map(listaAsignatura, new TypeToken<List<AsignaturaDTO>>() {}.getType());
    }

    @Override
    public AsignaturaDTO findById(Integer idAsignatura) {
        AsignaturaEntity objAsignatura = servicioAccesoBaseDatos.findById(idAsignatura)
                .orElseThrow(() -> new EntidadNoExisteException("Error, la Asignatura con id " + idAsignatura + " no existe"));
        return modelMapper.map(objAsignatura, AsignaturaDTO.class);
    }

    @Override
    
    public AsignaturaDTO save(AsignaturaDTO asignatura) {
        if (servicioAccesoBaseDatos.existsById(asignatura.getId())) {
            throw new ReglaNegocioExcepcion("Existe una Asignatura con ese ID, no se permite crear la Asignatura");
        }

        AsignaturaEntity asignaturaEntity = modelMapper.map(asignatura, AsignaturaEntity.class);
        AsignaturaEntity asignaturaEntityGuardada = servicioAccesoBaseDatos.save(asignaturaEntity);
        return modelMapper.map(asignaturaEntityGuardada, AsignaturaDTO.class);
    }

    @Override
    public AsignaturaDTO update(Integer idAsignatura, AsignaturaDTO asignatura) {
        AsignaturaEntity asignaturaExistente = servicioAccesoBaseDatos.findById(idAsignatura)
                .orElseThrow(() -> new EntidadNoExisteException("Error, la asignatura a actualizar no existe"));

        if (!idAsignatura.equals(asignatura.getId()) && servicioAccesoBaseDatos.existsById(asignatura.getId())) {
            throw new ReglaNegocioExcepcion("Existe una asignatura con el código registrado, no se permite actualizar");
        }

        asignaturaExistente.setNombre(asignatura.getNombre());
        asignaturaExistente.setDescripcion(asignatura.getDescripcion());
        asignaturaExistente.setCreditos(asignatura.getCreditos());
        asignaturaExistente.setSemestre(asignatura.getSemestre());
        asignaturaExistente.setCompA(asignatura.getCompA());

        AsignaturaEntity asignaturaActualizada = servicioAccesoBaseDatos.save(asignaturaExistente);
        return modelMapper.map(asignaturaActualizada, AsignaturaDTO.class);
    }

    @Override
    public boolean delete(Integer idAsignatura) {
        if (!servicioAccesoBaseDatos.existsById(idAsignatura)) {
            throw new EntidadNoExisteException("Error, la asignatura a eliminar no existe");
        }

        servicioAccesoBaseDatos.deleteById(idAsignatura);
        return true;
    }
}
