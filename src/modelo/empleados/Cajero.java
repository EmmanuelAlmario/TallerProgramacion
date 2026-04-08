package modelo.empleados;

import modelo.abstractas.Empleado;
import modelo.enums.Turno;

import java.time.LocalDate;
import java.time.Period;

public class Cajero extends Empleado {
    private Turno turno;
    private String  sucursalAsignada;
    private int transaccionesDia;

    public Cajero(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String legajo, LocalDate fechaContratacion, double salarioBase, boolean activo, Turno turno, String sucursalAsignada) {
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase, activo);
        this.turno = turno;
        this.sucursalAsignada = sucursalAsignada;
        this.transaccionesDia = 0;
    }

    @Override
    public double calcularBono() {
        return transaccionesDia * 500;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    @Override
    public int calcularEdad() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }

    @Override
    public String obtenerTipo() {
        return "Cajero";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return "Legajo: " + getLegajo();
    }

    public void registrarTransaccion() {
        this.transaccionesDia++;
    }

    public Turno getTurno() {
        return turno;
    }

    public String getSucursalAsignada() {
        return sucursalAsignada;
    }

    public int getTransaccionesDia() {
        return transaccionesDia;
    }
}

