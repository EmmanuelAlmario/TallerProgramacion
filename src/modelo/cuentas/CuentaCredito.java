package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SistemaBancarioException;

public class CuentaCredito extends Cuenta {
    private double limiteCredito;
    private double tasaInteres;
    private double deudaActual;

    public CuentaCredito(String numeroCuenta, double deudaInicial,
                         double limiteCredito, double tasaInteres) {
        super(numeroCuenta, -Math.abs(deudaInicial));
        this.deudaActual = Math.abs(deudaInicial);
        this.limiteCredito = limiteCredito;
        this.tasaInteres = tasaInteres;
    }

    @Override
    public double calcularInteres() {
        return deudaActual * tasaInteres / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return Math.max(0, limiteCredito - deudaActual);
    }

    @Override
    public String getTipoCuenta() {
        return "CREDITO";
    }

    @Override
    public void depositar(double monto) throws CuentaBloqueadaException {
        verificarBloqueada();
        if (monto <= 0) {
            throw new DatoInvalidoException("monto", monto);
        }
        deudaActual = Math.max(0, deudaActual - monto);
        setSaldo(-deudaActual);
    }

    @Override
    public void retirar(double monto) throws SistemaBancarioException {
        verificarBloqueada();
        if (monto <= 0) {
            throw new DatoInvalidoException("monto", monto);
        }
        if (deudaActual + monto > limiteCredito) {
            throw new DatoInvalidoException("monto", "Supera límite de crédito disponible");
        }
        deudaActual += monto;
        setSaldo(-deudaActual);
    }

    @Override
    public double calcularComision(double monto) {
        return monto * 0.015;
    }
}
