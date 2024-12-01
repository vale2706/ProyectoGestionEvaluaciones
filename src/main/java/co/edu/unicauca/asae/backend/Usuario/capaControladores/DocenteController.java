package co.edu.unicauca.asae.backend.Usuario.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO.DocenteDTO;
import co.edu.unicauca.asae.backend.Usuario.fachadaServices.services.IDocenteServices;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class DocenteController {
    @Autowired
    private IDocenteServices docenteServices;

    //Obtener todas las asignaturas
    @GetMapping("/docente")
    public ResponseEntity<List<DocenteDTO>> obtenerDocente() {
        List<DocenteDTO> lista = docenteServices.findAll();
        ResponseEntity<List<DocenteDTO>> objRespuesta = new ResponseEntity<List<DocenteDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar docente por id
    @GetMapping("/docente/{idDocente}")
    public ResponseEntity<DocenteDTO> consultarDocente(@PathVariable Integer idDocente) {
        DocenteDTO objDocente = docenteServices.findById(idDocente);
        if (objDocente == null) {
            throw new EntidadNoExisteException("docente con id " + idDocente + " no encontrado");
        }
        return new ResponseEntity<>(objDocente, HttpStatus.OK);
    }    

    @PostMapping("/docente")
    public ResponseEntity<DocenteDTO> CrearDocente(@RequestBody DocenteDTO docente) {
        DocenteDTO objDocente = docenteServices.save(docente);
        ResponseEntity<DocenteDTO> objRespuesta = new ResponseEntity<DocenteDTO>(objDocente, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/docente/{idDocente}")
    public ResponseEntity<DocenteDTO> actualizarDocente(@RequestBody DocenteDTO docente,
            @PathVariable Integer idDocente) {
        DocenteDTO objDocente = docenteServices.update(idDocente, docente);
        ResponseEntity<DocenteDTO> objRespuesta = new ResponseEntity<DocenteDTO>(objDocente, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una docente
    @DeleteMapping("/docente")
    public ResponseEntity<Boolean> eliminarDocente(@RequestParam Integer idDocente) {
        boolean isRemoved = docenteServices.delete(idDocente);
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
