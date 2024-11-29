package co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.excepcionesPropias;

import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.estructuraExcepciones.CodigoError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class GestionAsignaturaRuntimeException extends RuntimeException{

    protected CodigoError codigoError;
    public abstract String formatException();
}
