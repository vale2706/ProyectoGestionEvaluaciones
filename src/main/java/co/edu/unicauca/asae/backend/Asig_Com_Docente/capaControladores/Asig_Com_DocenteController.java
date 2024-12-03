package co.edu.unicauca.asae.backend.Asig_Com_Docente.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocentePK;
import co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.DTO.Asig_Com_DocenteDTO;
import co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.services.IAsig_Com_DocenteService;

import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class Asig_Com_DocenteController {

    @Autowired
    private IAsig_Com_DocenteService relacionService;

    // Obtener todas las relaciones(acceso permitido a todos los roles autenticados)
    @PreAuthorize("hasRole('COORDINADOR')")
    @GetMapping("/asig_com_docente")
    public ResponseEntity<List<Asig_Com_DocenteDTO>> obtenerRelacion() {
        List<Asig_Com_DocenteDTO> lista = relacionService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Consultar relacion por id (acceso permitido a todos los roles autenticados)
    @PreAuthorize("hasRole('COORDINADOR')")
    @GetMapping("/asig_com_docente/{idCompuesto}")
    public ResponseEntity<Asig_Com_DocenteDTO> consultarRelacion(@PathVariable Asig_Com_DocentePK id) {
        Asig_Com_DocenteDTO objRelacion = relacionService.findById(id);
        if (objRelacion == null) {
            throw new EntidadNoExisteException("relacion con id " + id + " no encontrada");
        }
        return new ResponseEntity<>(objRelacion, HttpStatus.OK);
    }

    // Crear relacion (acceso restringido solo a coordinadores)
    @PreAuthorize("hasRole('PROFESOR')")
    @PostMapping("/asig_com_docente")
    public ResponseEntity<Asig_Com_DocenteDTO> crearRelacion(@RequestBody Asig_Com_DocenteDTO relacion) {
        Asig_Com_DocenteDTO objRelacion = relacionService.save(relacion);
        return new ResponseEntity<>(objRelacion, HttpStatus.CREATED);
    }

    // Actualizar relacion (acceso restringido solo a coordinadores)
    @PreAuthorize("hasAnyRole('COORDINADOR','PROFESOR')")
    @PutMapping("/asig_com_docente/{idrelacion}")
    public ResponseEntity<Asig_Com_DocenteDTO> actualizarRelacion(@RequestBody Asig_Com_DocenteDTO relacion,
            @PathVariable Asig_Com_DocentePK id) {
        Asig_Com_DocenteDTO objRelacion = relacionService.update(id,relacion);
        return new ResponseEntity<>(objRelacion, HttpStatus.OK);
    }

    // Eliminar relacion (acceso restringido solo a coordinadores)
    @PreAuthorize("hasRole('COORDINADOR')")
    @DeleteMapping("/asig_com_docente")
    public ResponseEntity<Boolean> eliminarRelacion(@RequestParam Asig_Com_DocentePK id) {
        boolean isRemoved = relacionService.delete(id);
        return new ResponseEntity<>(isRemoved, HttpStatus.NO_CONTENT);
    }
}
