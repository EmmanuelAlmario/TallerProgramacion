package modelo.abstractas;

import modelo.banco.Transaccion;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SistemaBancarioException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;

import java.time.LocalDateTime;

public abstract class Cuenta implements Consultable, Transaccionable, Auditable {

    private String numeroCuenta;
    private double saldo;
    private boolean bloqueada;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;

    private Transaccion[] historial;
    private int contadorTransacciones;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.bloqueada = false;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "sistema";

        this.historial = new Transaccion[20];
        this.contadorTransacciones = 0;
    }

    public abstract double calcularInteres();

    public abstract double getLimiteRetiro();

    public abstract String getTipoCuenta();

    @Override
    public abstract void depositar(double monto) throws CuentaBloqueadaException;

    @Override
    public abstract void retirar(double monto) throws SistemaBancarioException;

    @Override
    public abstract double calcularComision(double monto);

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    public void verificarBloqueada() throws CuentaBloqueadaException {
        if (bloqueada) {
            throw new CuentaBloqueadaException();
        }
    }

    public void agregarAlHistorial(Transaccion t) throws CapacidadExcedidaException {
        if (contadorTransacciones >= historial.length) {
            throw new CapacidadExcedidaException(historial.length);
        }
        historial[contadorTransacciones++] = t;
    }

    public Transaccion[] getHistorial() {
        Transaccion[] copia = new Transaccion[contadorTransacciones];
        System.arraycopy(historial, 0, copia, 0, contadorTransacciones);
        return copia;
    }

    @Override
    public String obtenerResumen() {
        return "Cuenta " + getTipoCuenta() + " [" + numeroCuenta + "] saldo=" + saldo;
    }

    @Override
    public boolean estaActivo() {
        return !bloqueada;
    }

    @Override
    public String obtenerTipo() {
        return getTipoCuenta();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new DatoInvalidoException("numeroCuenta", numeroCuenta);
        }
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getUltimaModificacion() {
        return ultimaModificacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return ultimaModificacion;
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return usuarioModificacion;
    }

    @Override
    public void registrarModificacion(String usuario) {
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
    }
}
