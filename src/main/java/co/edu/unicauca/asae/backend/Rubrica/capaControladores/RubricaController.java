package co.edu.unicauca.asae.backend.Rubrica.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import co.edu.unicauca.asae.backend.Rubrica.fachadaServices.DTO.RubricaDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.Rubrica.fachadaServices.services.IRubricaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class RubricaController {
    @Autowired
    private IRubricaService rubricaService;

    //Obtener todas las rubrica
    @GetMapping("/rubrica")
    public ResponseEntity<List<RubricaDTO>> obtenerRubrica() {
        List<RubricaDTO> lista = rubricaService.findAll();
        ResponseEntity<List<RubricaDTO>> objRespuesta = new ResponseEntity<List<RubricaDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar rubrica por id
    @GetMapping("/rubrica/{idRubrica}")
    public ResponseEntity<RubricaDTO> consultarRubrica(@PathVariable Integer idRubrica) {
        RubricaDTO objRubrica = rubricaService.findById(idRubrica);
        if (objRubrica == null) {
            throw new EntidadNoExisteException("Rubrica con id " + idRubrica + " no encontrada");
        }
        return new ResponseEntity<>(objRubrica, HttpStatus.OK);
    }    

    @PostMapping("/rubrica")
    public ResponseEntity<RubricaDTO> CrearRubrica(@RequestBody RubricaDTO rubrica) {
        RubricaDTO objRubrica = rubricaService.save(rubrica);
        ResponseEntity<RubricaDTO> objRespuesta = new ResponseEntity<RubricaDTO>(objRubrica, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/rubrica/{idRubrica}")
    public ResponseEntity<RubricaDTO> actualizarRubrica(@RequestBody RubricaDTO rubrica,
            @PathVariable Integer idRubrica) {
        RubricaDTO objRubrica = rubricaService.update(idRubrica, rubrica);
        ResponseEntity<RubricaDTO> objRespuesta = new ResponseEntity<RubricaDTO>(objRubrica, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una rubrica
    @DeleteMapping("/rubrica")
    public ResponseEntity<Boolean> eliminarRubrica(@RequestParam Integer idRubrica) {
        boolean isRemoved = rubricaService.delete(idRubrica);
        ResponseEntity<Boolean> objRespuesta = new ResponseEntity<Boolean>(isRemoved, HttpStatus.NO_CONTENT);
        return objRespuesta;
    }

    @GetMapping("/{rubricaId}")
    public ResponseEntity<RubricaDTO> getRubricaWithDetails(@PathVariable Integer rubricaId) {
        RubricaDTO rubrica = rubricaService.findRubricaWithDetails(rubricaId);
        if (rubrica == null) {
            return new ResponseEntity<RubricaDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RubricaDTO>(rubrica, HttpStatus.OK);
    }

    @PostMapping("/rubrica/{idRubrica}/vincular/{idCriterio}/{idNivel}")
    public ResponseEntity<Void> vincularRubricaCriterioNivel(
            @PathVariable Integer idRubrica,
            @PathVariable Integer idCriterio,
            @PathVariable Integer idNivel) {
        rubricaService.vincularRubricaCriterioNivel(idRubrica, idCriterio, idNivel);
        return ResponseEntity.ok().build();
    }

}
