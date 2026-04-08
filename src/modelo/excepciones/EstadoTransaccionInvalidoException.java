package modelo.excepciones;

public class EstadoTransaccionInvalidoException extends BancoRuntimeException {
    public EstadoTransaccionInvalidoException(String estadoActual, String estadoNuevo) {
        super("Transición inválida de estado: " + estadoActual + " -> " + estadoNuevo);
    }
}
