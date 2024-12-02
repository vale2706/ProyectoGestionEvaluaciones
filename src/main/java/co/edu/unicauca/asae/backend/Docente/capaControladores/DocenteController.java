package co.edu.unicauca.asae.backend.Docente.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO.DocenteDTO;
import co.edu.unicauca.asae.backend.Docente.fachadaServices.services.IDocenteService;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class DocenteController {
    @Autowired
    private IDocenteService docenteservice;

    //Obtener todas las docentes
    @GetMapping("/docente")
    public ResponseEntity<List<DocenteDTO>> obtenerDocentes() {
        List<DocenteDTO> lista = docenteservice.findAll();
        ResponseEntity<List<DocenteDTO>> objRespuesta = new ResponseEntity<List<DocenteDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar docente por id
    @GetMapping("/docente/{idDocentes}")
    public ResponseEntity<DocenteDTO> consultarDocentes(@PathVariable Integer idDocentes) {
        DocenteDTO objDocentes = docenteservice.findById(idDocentes);
        if (objDocentes == null) {
            throw new EntidadNoExisteException("Docente con id " + idDocentes + " no encontrada");
        }
        return new ResponseEntity<>(objDocentes, HttpStatus.OK);
    }    

    @PostMapping("/docente")
    public ResponseEntity<DocenteDTO> CrearDocentes(@RequestBody DocenteDTO docente) {
        DocenteDTO objDocentes = docenteservice.save(docente);
        ResponseEntity<DocenteDTO> objRespuesta = new ResponseEntity<DocenteDTO>(objDocentes, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/docente/{idDocentes}")
    public ResponseEntity<DocenteDTO> actualizarDocentes(@RequestBody DocenteDTO docente,
            @PathVariable Integer idDocentes) {
        DocenteDTO objDocentes = docenteservice.update(idDocentes, docente);
        ResponseEntity<DocenteDTO> objRespuesta = new ResponseEntity<DocenteDTO>(objDocentes, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una docente
    @DeleteMapping("/docente")
    public ResponseEntity<Boolean> eliminarDocentes(@RequestParam Integer idDocentes) {
        boolean isRemoved = docenteservice.delete(idDocentes);
        ResponseEntity<Boolean> objRespuesta = new ResponseEntity<Boolean>(isRemoved, HttpStatus.NO_CONTENT);
        return objRespuesta;
    }
}
