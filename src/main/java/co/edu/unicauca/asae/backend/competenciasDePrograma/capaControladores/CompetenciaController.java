package co.edu.unicauca.asae.backend.competenciasDePrograma.capaControladores;

import java.util.List;

import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO.DocenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.services.ICompetenciaServices;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
public class CompetenciaController {
    @Autowired
    private ICompetenciaServices competenciaServices;

    @GetMapping("/competencia")
    @PreAuthorize( "hasRole('COORDINADOR')")
    public ResponseEntity<List<CompetenciaDTO>> obtenerCompetencias() {
        List<CompetenciaDTO> lista = competenciaServices.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/competencia/{idComp}")
    @PreAuthorize( "hasRole('COORDINADOR')")
    public ResponseEntity<CompetenciaDTO> consultarCompetencia(@PathVariable Integer idComp) {
        CompetenciaDTO objCompetencia = competenciaServices.findById(idComp);
        if (objCompetencia == null) {
            throw new EntidadNoExisteException("competencia con id " + idComp + " no encontrada");
        }
        return new ResponseEntity<>(objCompetencia, HttpStatus.OK);
    }

    @PostMapping("/competencia")
    @PreAuthorize( "hasRole('COORDINADOR')")
    public ResponseEntity<CompetenciaDTO> CrearCompetencia(@RequestBody CompetenciaDTO competencia) {
        CompetenciaDTO objCompetencia = competenciaServices.save(competencia);
        ResponseEntity<CompetenciaDTO> objRespuesta = new ResponseEntity<>(objCompetencia, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/competencia/{idComp}")
    @PreAuthorize( "hasRole('COORDINADOR')")
    public ResponseEntity<CompetenciaDTO> actualizarCompetencia(@RequestBody CompetenciaDTO competencia, @PathVariable Integer idComp) {
        CompetenciaDTO objCompetencia = competenciaServices.update(idComp, competencia);
        return new ResponseEntity<>(objCompetencia, HttpStatus.OK);
    }

    @DeleteMapping("/competencia")
    @PreAuthorize( "hasRole('COORDINADOR')")
    public ResponseEntity<Boolean> eliminarCompetencia(@RequestParam Integer idComp) {
        boolean isRemoved = competenciaServices.delete(idComp);
        return new ResponseEntity<>(isRemoved, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/competencia/{competenciaId}/asociar/{raId}")
    @PreAuthorize("hasRole('COORDINADOR')")
    public ResponseEntity<?> asociarRAConCompetencia(@PathVariable Integer competenciaId, @PathVariable Integer raId) {
        try {
            // Llamada al servicio para asociar la competencia con el RA
            competenciaServices.vincularResultadoAprendizajeACompetencia(competenciaId, raId);
            return ResponseEntity.ok("Competencia vinculada a Resultado de Aprendizaje exitosamente.");
        } catch (EntidadNoExisteException ex) {
            // Manejo de la excepción en caso de que la Competencia o el RA no existan
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (ReglaNegocioExcepcion ex) {
            // Manejo de otras reglas de negocio específicas
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            // Manejo de cualquier otra excepción no específica
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al vincular los datos.");
        }
    }
}