package modelo.excepciones;

public class PermisoInsuficienteException extends BancoRuntimeException {
    public PermisoInsuficienteException(String accionDenegada) {
        super("Permiso insuficiente: " + accionDenegada);
    }
}
