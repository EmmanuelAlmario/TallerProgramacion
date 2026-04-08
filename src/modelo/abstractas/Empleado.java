package modelo.abstractas;

import modelo.excepciones.DatoInvalidoException;

import java.time.LocalDate;
import java.time.Period;

public abstract class Empleado extends Persona {
    private String legajo;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private boolean activo;

    public Empleado(String id, String nombre, String apellido, LocalDate fechaNacimiento,
                    String email, String legajo, LocalDate fechaContratacion,
                    double salarioBase, boolean activo) {
        super(id, nombre, apellido, fechaNacimiento, email);
        this.legajo = legajo;
        if (fechaContratacion == null || fechaContratacion.isAfter(LocalDate.now())) {
            throw new DatoInvalidoException("fechaDeNacimiento", fechaContratacion);
        } else {
            this.fechaContratacion = fechaContratacion;
        }
        if (salarioBase <= 0) {
            throw new DatoInvalidoException("salarioBase", salarioBase);
        } else {
            this.salarioBase = salarioBase;
        }
        this.activo = activo;
    }

    public abstract double calcularSalario();
    public abstract double calcularBono();

    public int calcularAntiguedad() {
        return Period.between(fechaContratacion, LocalDate.now()).getYears();
    }

    public String getLegajo() {
        return legajo;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        if (fechaContratacion.isAfter(LocalDate.now())) {
            throw new DatoInvalidoException("fechaContratacion", fechaContratacion);
        }
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase <= 0) {
            throw new DatoInvalidoException("salarioBase", salarioBase);
        } else {
            this.salarioBase = salarioBase;
        }
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
