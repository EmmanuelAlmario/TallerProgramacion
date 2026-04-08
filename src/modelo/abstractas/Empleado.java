package modelo.abstractas;

import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public abstract class Empleado extends Persona implements Auditable {
    private String legajo;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private boolean activo;

    private final LocalDateTime creacion;
    private LocalDateTime modificacion;
    private String usuario;

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
        validarSalarioBase(salarioBase);
        this.activo = activo;
        this.creacion = LocalDateTime.now();
        this.modificacion = this.creacion;
        this.usuario = "sistema";
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
        validarSalarioBase(salarioBase);
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    private void validarSalarioBase(double salarioBase) {
        if (salarioBase <= 0) {
            throw new DatoInvalidoException("salarioBase", salarioBase);
        }
        this.salarioBase = salarioBase;
    }

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return creacion;
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return modificacion;
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return usuario;
    }

    @Override
    public void registrarModificacion(String usuario) {
        this.usuario = usuario;
        this.modificacion = LocalDateTime.now();
    }
}
