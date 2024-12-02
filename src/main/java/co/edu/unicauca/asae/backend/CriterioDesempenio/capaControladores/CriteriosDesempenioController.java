package co.edu.unicauca.asae.backend.CriterioDesempenio.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unicauca.asae.backend.CriterioDesempenio.fachadaServices.DTO.CriteriosDesempenioDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.CriterioDesempenio.fachadaServices.services.ICriteriosDesempenioService;

import java.util.*;;

public class CriteriosDesempenioController {
    @Autowired
    private ICriteriosDesempenioService desempenioService;

    //Obtener todas las asignaturas
    @GetMapping("/desempenio")
    public ResponseEntity<List<CriteriosDesempenioDTO>> obtenerDesempenio() {
        List<CriteriosDesempenioDTO> lista = desempenioService.findAll();
        ResponseEntity<List<CriteriosDesempenioDTO>> objRespuesta = new ResponseEntity<List<CriteriosDesempenioDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar desempenio por id
    @GetMapping("/desempenio/{idDesempenio}")
    public ResponseEntity<CriteriosDesempenioDTO> consultarDesempenio(@PathVariable Integer idDesempenio) {
        CriteriosDesempenioDTO objDesempenio = desempenioService.findById(idDesempenio);
        if (objDesempenio == null) {
            throw new EntidadNoExisteException("Criterio de desempeño con id " + idDesempenio + " no encontrada");
        }
        return new ResponseEntity<>(objDesempenio, HttpStatus.OK);
    }    

    @PostMapping("/desempenio")
    public ResponseEntity<CriteriosDesempenioDTO> CrearDesempenio(@RequestBody CriteriosDesempenioDTO desempenio) {
        CriteriosDesempenioDTO objDesempenio = desempenioService.save(desempenio);
        ResponseEntity<CriteriosDesempenioDTO> objRespuesta = new ResponseEntity<CriteriosDesempenioDTO>(objDesempenio, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/desempenio/{idDesempenio}")
    public ResponseEntity<CriteriosDesempenioDTO> actualizarDEsempenio(@RequestBody CriteriosDesempenioDTO desempenio,
            @PathVariable Integer idDesempenio) {
        CriteriosDesempenioDTO objDesempenio = desempenioService.update(idDesempenio, desempenio);
        ResponseEntity<CriteriosDesempenioDTO> objRespuesta = new ResponseEntity<CriteriosDesempenioDTO>(objDesempenio, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una desempenio
    @DeleteMapping("/desempenio")
    public ResponseEntity<Boolean> eliminarDesempenio(@RequestParam Integer idDesempenio) {
        boolean isRemoved = desempenioService.delete(idDesempenio);
        ResponseEntity<Boolean> objRespuesta = new ResponseEntity<Boolean>(isRemoved, HttpStatus.NO_CONTENT);
        return objRespuesta;
    }
}
