package modelo.excepciones;

public class SaldoInsuficienteException extends SistemaBancarioException {
    private final double saldoActual;
    private final double montoSolicitado;

    public SaldoInsuficienteException(double saldoActual, double montoSolicitado) {
        super("SALDO_INSUFICIENTE", "Saldo insuficiente. Actual: " + saldoActual + ", solicitado: " + montoSolicitado);
        this.saldoActual = saldoActual;
        this.montoSolicitado = montoSolicitado;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }
}
