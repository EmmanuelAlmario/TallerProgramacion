package modelo.banco;
import java.time.LocalDateTime;
import modelo.enums.EstadoTransaccion;
import modelo.abstractas.Cuenta;
import modelo.excepciones.EstadoTransaccionInvalidoException;

public class Transaccion {

    private String id;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double monto;
    private EstadoTransaccion estado;
    private LocalDateTime fecha;
    private String descripcion;

    public Transaccion(String id, Cuenta origen, Cuenta destino, double monto, String descripcion) {
        this.id = id;
        this.cuentaOrigen = origen;
        this.cuentaDestino = destino;
        this.monto = monto;
        this.descripcion = descripcion;
        this.estado = EstadoTransaccion.PENDIENTE;
        this.fecha = LocalDateTime.now();
    }

    public void cambiarEstado(EstadoTransaccion nuevo) {

        String actual = this.estado.name();
        String nuevoStr = nuevo.name();

        switch (this.estado) {

            case PENDIENTE:
                if (nuevo != EstadoTransaccion.PROCESANDO &&
                        nuevo != EstadoTransaccion.RECHAZADA) {
                    throw new EstadoTransaccionInvalidoException(actual, nuevoStr);
                }
                break;

            case PROCESANDO:
                if (nuevo != EstadoTransaccion.COMPLETADA &&
                        nuevo != EstadoTransaccion.RECHAZADA) {
                    throw new EstadoTransaccionInvalidoException(actual, nuevoStr);
                }
                break;

            case COMPLETADA:
                if (nuevo != EstadoTransaccion.REVERTIDA) {
                    throw new EstadoTransaccionInvalidoException(actual, nuevoStr);
                }
                break;

            case RECHAZADA:
            case REVERTIDA:
                throw new EstadoTransaccionInvalidoException(actual, nuevoStr);
        }

        this.estado = nuevo;
    }

    public String generarComprobante() {
        return "=== COMPROBANTE ===\n" +
                "ID: " + id + "\n" +
                "Fecha: " + fecha + "\n" +
                "Monto: " + monto + "\n" +
                "Estado: " + estado + "\n" +
                "Descripción: " + descripcion + "\n";
    }

    // Getters

    public String getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }
}