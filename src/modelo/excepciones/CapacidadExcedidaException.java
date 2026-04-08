package modelo.excepciones;

public class CapacidadExcedidaException extends SistemaBancarioException {
    private final int capacidadMaxima;

    public CapacidadExcedidaException(int capacidadMaxima) {
        super("CAPACIDAD_EXCEDIDA", "Se alcanzó la capacidad máxima: " + capacidadMaxima);
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}
