package co.edu.unicauca.asae.backend.Asignatura.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.Asignatura.fachadaServices.DTO.AsignaturaDTO;
import co.edu.unicauca.asae.backend.Asignatura.fachadaServices.services.IAsignaturaService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class AsignaturaController {
    @Autowired
    private IAsignaturaService asignaturaService;

    //Obtener todas las asignaturas
    @GetMapping("/asignatura")
    public ResponseEntity<List<AsignaturaDTO>> obtenerAsignaturas() {
        List<AsignaturaDTO> lista = asignaturaService.findAll();
        ResponseEntity<List<AsignaturaDTO>> objRespuesta = new ResponseEntity<List<AsignaturaDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar asignatura por id
    @GetMapping("/asignatura/{idAsignatura}")
    public ResponseEntity<AsignaturaDTO> consultarAsignatura(@PathVariable Integer idAsignatura) {
        AsignaturaDTO objAsignatura = asignaturaService.findById(idAsignatura);
        if (objAsignatura == null) {
            throw new EntidadNoExisteException("Asignatura con id " + idAsignatura + " no encontrada");
        }
        return new ResponseEntity<>(objAsignatura, HttpStatus.OK);
    }    

    @PostMapping("/asignatura")
    public ResponseEntity<AsignaturaDTO> CrearAsignatura(@RequestBody AsignaturaDTO asignatura) {
        AsignaturaDTO objAsignatura = asignaturaService.save(asignatura);
        ResponseEntity<AsignaturaDTO> objRespuesta = new ResponseEntity<AsignaturaDTO>(objAsignatura, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/asignatura/{idAsignatura}")
    public ResponseEntity<AsignaturaDTO> actualizarAsignatura(@RequestBody AsignaturaDTO asignatura,
            @PathVariable Integer idAsignatura) {
        AsignaturaDTO objAsignatura = asignaturaService.update(idAsignatura, asignatura);
        ResponseEntity<AsignaturaDTO> objRespuesta = new ResponseEntity<AsignaturaDTO>(objAsignatura, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una asignatura
    @DeleteMapping("/asignatura")
    public ResponseEntity<Boolean> eliminarAsignatura(@RequestParam Integer idAsignatura) {
        boolean isRemoved = asignaturaService.delete(idAsignatura);
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
