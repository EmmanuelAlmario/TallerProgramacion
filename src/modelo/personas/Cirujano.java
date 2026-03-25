package modelo.personas;

import modelo.hospital.Especialidad;

import java.time.LocalDate;

public class Cirujano extends Medico {
    private int cirugiasRealizadas;
    private boolean disponibleEmergencias;

    public Cirujano(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String legajo, LocalDate fechaContratacion, double salarioBase, boolean activo, int numeroLicencia, Especialidad especialidad, int citasAtendidas, int cirugiasRealizadas, boolean disponibleEmergencias) {
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase, activo, numeroLicencia, especialidad, citasAtendidas);
        this.cirugiasRealizadas = cirugiasRealizadas;
        this.disponibleEmergencias = disponibleEmergencias;
    }

    public double calcularBono() {
        return cirugiasRealizadas * 20000;
    }
    public double calcularSalario() {
        return super.calcularSalario() + calcularBono();
    }

    public void realizarCirugia() {
        cirugiasRealizadas++;
    }

    @Override
    public String obtenerTipo() {
        return "Cirujano";
    }

    public int getCirugiasRealizadas() {
        return cirugiasRealizadas;
    }

    public boolean isDisponibleEmergencias() {
        return disponibleEmergencias;
    }

    public void setDisponibleEmergencias(boolean disponibleEmergencias) {
        this.disponibleEmergencias = disponibleEmergencias;
    }
}
