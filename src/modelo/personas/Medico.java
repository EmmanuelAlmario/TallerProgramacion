package modelo.personas;
import modelo.abstractas.Empleado;
import modelo.hospital.CitaMedica;
import modelo.hospital.Diagnostico;
import modelo.hospital.Especialidad;
import modelo.personas.Paciente;
import modelo.abstractas.Empleado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.time.Period;

public class Medico extends Empleado {
    private int numeroLicencia;
    private Especialidad especialidad;
    private List<Paciente> pacientesAsignados;
    private int citasAtendidas;

    public Medico(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String legajo, LocalDate fechaContratacion, double salarioBase, boolean activo, int numeroLicencia, Especialidad especialidad, int citasAtendidas) {
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase, activo);
        this.numeroLicencia = numeroLicencia;
        this.especialidad = especialidad;
        this.citasAtendidas = citasAtendidas;
        this.pacientesAsignados = new ArrayList<>();
    }

    public void atenderPaciente(Paciente p) {
        pacientesAsignados.add(p);
        citasAtendidas++;
    }

    public void registrarDiagnostico(CitaMedica cita, Diagnostico d) {
        cita.setDiagnostico(d);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + (antiguedad() * 5000);
    }

    @Override
    public int calcularEdad() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }

    @Override
    public String obtenerTipo() {
        return "Medico";
    }

    public int getNumeroLicencia() {
        return numeroLicencia;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public List<Paciente> getPacientesAsignados() {
        return new ArrayList<>(pacientesAsignados);
    }

    public int getCitasAtendidas() {
        return citasAtendidas;
    }
}
