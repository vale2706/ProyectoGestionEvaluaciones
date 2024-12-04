package co.edu.unicauca.asae.backend.NivelDesempenio.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import co.edu.unicauca.asae.backend.NivelDesempenio.fachadaServices.DTO.NivelDesempenioDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.NivelDesempenio.fachadaServices.services.INivelDesempenioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class NivelDesempenioController {
    @Autowired
    private INivelDesempenioService nivelDesempenioService;

    //Obtener todas las nivelDesempenio
    @GetMapping("/nivelDesempenio")
    public ResponseEntity<List<NivelDesempenioDTO>> obtenerNivelDesempenio() {
        List<NivelDesempenioDTO> lista = nivelDesempenioService.findAll();
        ResponseEntity<List<NivelDesempenioDTO>> objRespuesta = new ResponseEntity<List<NivelDesempenioDTO>>(lista,
                HttpStatus.OK);
        return objRespuesta;
    }

    // Consultar nivelDesempenio por id
    @GetMapping("/nivelDesempenio/{idNivelD}")
    public ResponseEntity<NivelDesempenioDTO> consultarNivelDesempenio(@PathVariable Integer idNivelD) {
        NivelDesempenioDTO objNivelD = nivelDesempenioService.findById(idNivelD);
        if (objNivelD == null) {
            throw new EntidadNoExisteException("Nivel de desempeño con id " + idNivelD + " no encontrada");
        }
        return new ResponseEntity<>(objNivelD, HttpStatus.OK);
    }    

    @PostMapping("/nivelDesempenio")
    public ResponseEntity<NivelDesempenioDTO> CrearNivelDesempenio(@RequestBody NivelDesempenioDTO nivelDesempenio) {
        NivelDesempenioDTO objNivelD = nivelDesempenioService.save(nivelDesempenio);
        ResponseEntity<NivelDesempenioDTO> objRespuesta = new ResponseEntity<NivelDesempenioDTO>(objNivelD, HttpStatus.CREATED);
        return objRespuesta;
    }

    @PutMapping("/nivelDesempenio/{idNivelD}")
    public ResponseEntity<NivelDesempenioDTO> actualizarNivelDesempenio(@RequestBody NivelDesempenioDTO nivelDesempenio,
            @PathVariable Integer idNivelD) {
        NivelDesempenioDTO objNivelD = nivelDesempenioService.update(idNivelD, nivelDesempenio);
        ResponseEntity<NivelDesempenioDTO> objRespuesta = new ResponseEntity<NivelDesempenioDTO>(objNivelD, HttpStatus.OK);
        return objRespuesta;
    }

    // Eliminar una nivelDesempenio
    @DeleteMapping("/nivelDesempenio/{idNivelD}")
    public ResponseEntity<Boolean> eliminarNivelDesempenio(@PathVariable Integer idNivelD) {
        boolean isRemoved = nivelDesempenioService.delete(idNivelD);
        ResponseEntity<Boolean> objRespuesta = new ResponseEntity<Boolean>(isRemoved, HttpStatus.NO_CONTENT);
        return objRespuesta;
    }
}
