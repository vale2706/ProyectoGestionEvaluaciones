package co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias;

import co.edu.unicauca.asae.backend.ControladorExcepciones.estructuraExcepciones.CodigoError;

public class ReglaNegocioExcepcion  extends GestionAsignaturaRuntimeException{
    
    private static final String FORMA_EXCEPTION = "%s - Violación a regla de negocio: %s";

    private final String reglaNegocio;

    public ReglaNegocioExcepcion(final String reglaNegocio){
        super(CodigoError.VIOLACION_REGLA_DE_NEGOCIO);
        this.reglaNegocio = reglaNegocio;
    }

    @Override
    public String formatException(){
        return String.format(FORMA_EXCEPTION, codigoError.getCodigo(), reglaNegocio);
    }
}