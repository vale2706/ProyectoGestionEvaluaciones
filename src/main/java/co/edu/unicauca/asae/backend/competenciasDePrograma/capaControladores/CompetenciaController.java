package co.edu.unicauca.asae.backend.competenciasDePrograma.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.services.ICompetenciaServices;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class CompetenciaController {
    @Autowired
    private ICompetenciaServices competenciaServices;

    @GetMapping("/resultados")
    public List<CompetenciaDTO> listarCompetenciasConResultados() {
        return competenciaServices.findAllWithResultados();
    }

    //Obtener todas las asignaturas
    @GetMapping("/competencia")
    public ResponseEntity<List<CompetenciaDTO>> obtenerCompetencias() {
        List<CompetenciaDTO> lista = competenciaServices.findAll();
        ResponseEntity<List<CompetenciaDTO>> objRespuesta = new ResponseEntity<List<CompetenciaDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar competencia por id
    @GetMapping("/competencia/{idComp}")
    public ResponseEntity<CompetenciaDTO> consultarAsignatura(@PathVariable Integer idComp) {
        CompetenciaDTO objCompetencia = competenciaServices.findById(idComp);
        if (objCompetencia == null) {
            throw new EntidadNoExisteException("competencia con id " + idComp + " no encontrada");
        }
        return new ResponseEntity<>(objCompetencia, HttpStatus.OK);
    }    

    @PostMapping("/competencia")
    public ResponseEntity<CompetenciaDTO> CrearCompetencia(@RequestBody CompetenciaDTO competencia) {
        CompetenciaDTO objCompetencia = competenciaServices.save(competencia);
        ResponseEntity<CompetenciaDTO> objRespuesta = new ResponseEntity<CompetenciaDTO>(objCompetencia, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/competencia/{idComp}")
    public ResponseEntity<CompetenciaDTO> actualizarCompetencia(@RequestBody CompetenciaDTO competencia,
            @PathVariable Integer idComp) {
        CompetenciaDTO objCompetencia = competenciaServices.update(idComp, competencia);
        ResponseEntity<CompetenciaDTO> objRespuesta = new ResponseEntity<CompetenciaDTO>(objCompetencia, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una competencia
    @DeleteMapping("/competencia")
    public ResponseEntity<Boolean> eliminarCompetencia(@RequestParam Integer idComp) {
        boolean isRemoved = competenciaServices.delete(idComp);
        ResponseEntity<Boolean> objRespuesta = new ResponseEntity<Boolean>(isRemoved, HttpStatus.NO_CONTENT);
        return objRespuesta;
    }

    @RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("¡El servidor funciona correctamente!");
    }
}
}
