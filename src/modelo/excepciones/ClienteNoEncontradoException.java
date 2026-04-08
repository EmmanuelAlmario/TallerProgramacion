package modelo.excepciones;

public class ClienteNoEncontradoException extends SistemaBancarioException {
    private final String idCliente;

    public ClienteNoEncontradoException(String idCliente) {
        super("CLIENTE_NO_ENCONTRADO", "No existe cliente con id/documento: " + idCliente);
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }
}
