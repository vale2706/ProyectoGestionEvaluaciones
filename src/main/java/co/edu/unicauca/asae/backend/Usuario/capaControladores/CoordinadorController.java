package co.edu.unicauca.asae.backend.Usuario.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO.CoordinadorDTO;
import co.edu.unicauca.asae.backend.Usuario.fachadaServices.services.ICoordinadorServices;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class CoordinadorController {
    @Autowired
    private ICoordinadorServices coordinadorServices;

    //Obtener todas las asignaturas
    @GetMapping("/coordinador")
    public ResponseEntity<List<CoordinadorDTO>> obtenerCoordinadores() {
        List<CoordinadorDTO> lista = coordinadorServices.findAll();
        ResponseEntity<List<CoordinadorDTO>> objRespuesta = new ResponseEntity<List<CoordinadorDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar coordinador por id
    @GetMapping("/coordinador/{idCoordi}")
    public ResponseEntity<CoordinadorDTO> consultarCoordinador(@PathVariable Integer idCoordi) {
        CoordinadorDTO objCoordi = coordinadorServices.findById(idCoordi);
        if (objCoordi == null) {
            throw new EntidadNoExisteException("coordinador con id " + idCoordi + " no encontrado");
        }
        return new ResponseEntity<>(objCoordi, HttpStatus.OK);
    }    

    @PostMapping("/coordinador")
    public ResponseEntity<CoordinadorDTO> CrearCoordinador(@RequestBody CoordinadorDTO coordinador) {
        CoordinadorDTO objCoordi = coordinadorServices.save(coordinador);
        ResponseEntity<CoordinadorDTO> objRespuesta = new ResponseEntity<CoordinadorDTO>(objCoordi, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/coordinador/{idCoordi}")
    public ResponseEntity<CoordinadorDTO> actualizarCoordinador(@RequestBody CoordinadorDTO coordinador,
            @PathVariable Integer idCoordi) {
        CoordinadorDTO objCoordi = coordinadorServices.update(idCoordi, coordinador);
        ResponseEntity<CoordinadorDTO> objRespuesta = new ResponseEntity<CoordinadorDTO>(objCoordi, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una coordinador
    @DeleteMapping("/coordinador")
    public ResponseEntity<Boolean> eliminarCoordinador(@RequestParam Integer idCoordi) {
        boolean isRemoved = coordinadorServices.delete(idCoordi);
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
