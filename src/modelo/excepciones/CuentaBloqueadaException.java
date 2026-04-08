package modelo.excepciones;

public class CuentaBloqueadaException extends SistemaBancarioException {
    public CuentaBloqueadaException() {
        super("CUENTA_BLOQUEADA", "La cuenta está bloqueada");
    }
}
