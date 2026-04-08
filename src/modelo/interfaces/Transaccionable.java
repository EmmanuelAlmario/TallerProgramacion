package modelo.interfaces;

import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.SistemaBancarioException;

public interface Transaccionable {
    void depositar(double monto) throws CuentaBloqueadaException;
    void retirar(double monto) throws SistemaBancarioException;
    double calcularComision(double monto);
    double consultarSaldo();
}
