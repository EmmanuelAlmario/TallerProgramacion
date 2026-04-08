package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SaldoInsuficienteException;
import modelo.excepciones.SistemaBancarioException;

public class CuentaCorriente extends Cuenta {

    private double montoSobregiro;
    private double comisionMantenimiento;

    public CuentaCorriente(String numeroCuenta, double saldo,
                           double montoSobregiro, double comisionMantenimiento) {
        super(numeroCuenta, saldo);
        this.montoSobregiro = montoSobregiro;
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public double calcularInteres() {
        return 0;
    }

    @Override
    public double getLimiteRetiro() {
        return getSaldo() + montoSobregiro;
    }

    @Override
    public String getTipoCuenta() {
        return "CORRIENTE";
    }

    @Override
    public void depositar(double monto) throws CuentaBloqueadaException {
        verificarBloqueada();

        if (monto <= 0) {
            throw new DatoInvalidoException("monto", monto);
        }

        setSaldo(getSaldo() + monto);
    }

    @Override
    public void retirar(double monto) throws SistemaBancarioException {
        verificarBloqueada();

        if (monto <= 0) {
            throw new DatoInvalidoException("monto", monto);
        }

        if (monto > getSaldo() + montoSobregiro) {
            throw new SaldoInsuficienteException(getSaldo(), monto);
        }

        setSaldo(getSaldo() - monto);
    }

    @Override
    public double calcularComision(double monto) {
        return Math.max(comisionMantenimiento, monto * 0.01);
    }
}
