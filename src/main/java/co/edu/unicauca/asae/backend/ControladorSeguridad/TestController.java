package co.edu.unicauca.asae.backend.ControladorSeguridad;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Contenido publico";
  }

  //PreAuthorize verifica si un rol tiene autorizacion antes de acceder a un metodo 
 
  @GetMapping("/coor")
  @PreAuthorize("hasRole('COORDINADOR')")
  public String coordinadorAccess() {
    return "Contenido privado. Datos retornados para el api decoordinador.";
  }

  @GetMapping("/prf")
  @PreAuthorize("hasRole('PROFESOR')")
  public String profesorAccess() {
    return "Contenido privado. Datos retornados para el api de profesor.";
  }
}
