package modelo.excepciones;

import java.time.LocalDateTime;

public class SistemaBancarioException extends Exception {
    private final String codigoError;
    private final LocalDateTime timestamp;

    public SistemaBancarioException(String codigoError, String mensaje) {
        super(mensaje);
        this.codigoError = codigoError;
        this.timestamp = LocalDateTime.now();
    }

    public String getCodigoError() {
        return codigoError;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "SistemaBancarioException{" +
                "codigoError='" + codigoError + '\'' +
                ", timestamp=" + timestamp +
                ", mensaje='" + getMessage() + '\'' +
                '}';
    }
}
