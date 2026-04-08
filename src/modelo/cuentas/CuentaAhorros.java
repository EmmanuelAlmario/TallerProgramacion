package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.excepciones.*;
import modelo.interfaces.*;

import java.time.LocalDateTime;

public class CuentaAhorros extends Cuenta implements Consultable, Transaccionable, Auditable {

    private double tasaInteres;
    private int retirosMesActual;
    private int maxRetirosMes;

    public CuentaAhorros(String numeroCuenta, double saldo,
                         double tasaInteres, int maxRetirosMes) {
        super(numeroCuenta, saldo);
        this.tasaInteres = tasaInteres;
        this.maxRetirosMes = maxRetirosMes;
        this.retirosMesActual = 0;
    }

    @Override
    public double calcularInteres() {
        return getSaldo() * tasaInteres / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return 1000000;
    }

    @Override
    public String getTipoCuenta() {
        return "AHORROS";
    }

    // ========================
    // Transaccionable
    // ========================
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

        if (getSaldo() < monto) {
            throw new SaldoInsuficienteException(getSaldo(), monto);
        }

        if (retirosMesActual >= maxRetirosMes) {
            throw new BancoRuntimeException("Límite de retiros alcanzado");
        }

        setSaldo(getSaldo() - monto);
        retirosMesActual++;
    }

    @Override
    public double calcularComision(double monto) {
        return 0;
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
    }

    // ========================
    // Consultable
    // ========================
    @Override
    public String obtenerResumen() {
        return "Cuenta Ahorros - Saldo: " + getSaldo();
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return getTipoCuenta();
    }

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return getFechaCreacion();
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return getUltimaModificacion();
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return getUsuarioModificacion();
    }
}