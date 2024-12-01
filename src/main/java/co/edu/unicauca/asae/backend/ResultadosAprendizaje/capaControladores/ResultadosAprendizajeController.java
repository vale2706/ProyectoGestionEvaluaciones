package co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.services.IResultadosAprendizajeServices;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT})
public class ResultadosAprendizajeController {
    @Autowired
    private IResultadosAprendizajeServices RASercvices;

    //Obtener todas las asignaturas
    @GetMapping("/ra")
    public ResponseEntity<List<ResultadosAprendizajeDTO>> obtenerRa() {
        List<ResultadosAprendizajeDTO> lista = RASercvices.findAll();
        ResponseEntity<List<ResultadosAprendizajeDTO>> objRespuesta = new ResponseEntity<List<ResultadosAprendizajeDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar ra por id
    @GetMapping("/ra/{idRa}")
    public ResponseEntity<ResultadosAprendizajeDTO> consultarRa(@PathVariable Integer idRa) {
        ResultadosAprendizajeDTO objRa = RASercvices.findById(idRa);
        if (objRa == null) {
            throw new EntidadNoExisteException("ra con id " + idRa + " no encontrada");
        }
        return new ResponseEntity<>(objRa, HttpStatus.OK);
    }    

    @PostMapping("/ra")
    public ResponseEntity<ResultadosAprendizajeDTO> CrearRa(@RequestBody ResultadosAprendizajeDTO ra) {
        ResultadosAprendizajeDTO objRa = RASercvices.save(ra);
        ResponseEntity<ResultadosAprendizajeDTO> objRespuesta = new ResponseEntity<ResultadosAprendizajeDTO>(objRa, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/ra/{idRa}")
    public ResponseEntity<ResultadosAprendizajeDTO> actualizarRa(@RequestBody ResultadosAprendizajeDTO ra,
            @PathVariable Integer idRa) {
        ResultadosAprendizajeDTO objRa = RASercvices.update(idRa, ra);
        ResponseEntity<ResultadosAprendizajeDTO> objRespuesta = new ResponseEntity<ResultadosAprendizajeDTO>(objRa, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una ra
    @DeleteMapping("/ra")
    public ResponseEntity<Boolean> eliminarRa(@RequestParam Integer idRa) {
        boolean isRemoved = RASercvices.delete(idRa);
        ResponseEntity<Boolean> objRespuesta = new ResponseEntity<Boolean>(isRemoved, HttpStatus.NO_CONTENT);
        return objRespuesta;
    }

}
