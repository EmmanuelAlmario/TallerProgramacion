package modelo.excepciones;

public class DatoInvalidoException extends BancoRuntimeException {
    private final String campo;
    private final Object valorRecibido;

    public DatoInvalidoException(String campo, Object valorRecibido) {
        super("Dato inválido para '" + campo + "': " + valorRecibido);
        this.campo = campo;
        this.valorRecibido = valorRecibido;
    }

    public String getCampo() {
        return campo;
    }

    public Object getValorRecibido() {
        return valorRecibido;
    }
}
