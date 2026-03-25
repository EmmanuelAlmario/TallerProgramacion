package modelo.personas;

import modelo.abstractas.Empleado;
import modelo.enums.Turno;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Enfermero extends Empleado {
    private Turno turno;
    private String areaAsignada;
    private List<Paciente> pacientesACargo;

    public Enfermero(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String legajo, LocalDate fechaContratacion, double salarioBase, boolean activo, Turno turno, String areaAsignada) {
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase, activo);
        this.turno = turno;
        this.areaAsignada = areaAsignada;
        this.pacientesACargo = new ArrayList<>();
    }

    public void asistirCirugia() {
        System.out.println("Asistiendo a cirugia.");
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + (antiguedad() * 1500);
    }

    @Override
    public int calcularEdad() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }

    @Override
    public String obtenerTipo() {
        return "Enfermero";
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public List<Paciente> getPacientesACargo() {
        return new ArrayList<>(pacientesACargo);
    }

    public String getAreaAsignada() {
        return areaAsignada;
    }

    public void setAreaAsignada(String areaAsignada) {
        this.areaAsignada = areaAsignada;
    }
}
