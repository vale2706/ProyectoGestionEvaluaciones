package co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones;

import java.util.Locale;
import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.estructuraExcepciones.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.estructuraExcepciones.CodigoError;
import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.estructuraExcepciones.ErrorUtils;
import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.excepcionesPropias.EntidadYaExisteException;
import co.edu.unicauca.asae.backend.Asignatura.capaControladores.controladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

@ControllerAdvice
public class RestApiExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Error> handleGenericException(final HttpServletRequest req, final Exception ex,
                        final Locale locale) {
                final Error error = ErrorUtils.crearError(CodigoError.ERROR_GENERICO.getCodigo(),
                                CodigoError.ERROR_GENERICO.getLlaveMensaje(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        @ExceptionHandler(EntidadYaExisteException.class)
        public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                        final EntidadYaExisteException ex) {
                final Error error = ErrorUtils.crearError(CodigoError.ENTIDAD_YA_EXISTE.getCodigo(),
                                String.format("%s, %s", CodigoError.ENTIDAD_YA_EXISTE.getLlaveMensaje(),
                                                ex.getMessage()),
                                HttpStatus.NOT_ACCEPTABLE.value())
                                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(ReglaNegocioExcepcion.class)
        public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                        final ReglaNegocioExcepcion ex, final Locale locale) {
                final Error error = ErrorUtils
                                .crearError(CodigoError.VIOLACION_REGLA_DE_NEGOCIO.getCodigo(), ex.formatException(),
                                                HttpStatus.BAD_REQUEST.value())
                                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(EntidadNoExisteException.class)
        public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                        final EntidadNoExisteException ex, final Locale locale) {
                final Error error = ErrorUtils
                                .crearError(CodigoError.ENTIDAD_NO_ENCONTRADA.getCodigo(),
                                                String.format("%s, %s",
                                                                CodigoError.ENTIDAD_NO_ENCONTRADA.getLlaveMensaje(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_FOUND.value())
                                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
}

