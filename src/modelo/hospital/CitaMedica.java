package modelo.hospital;

import modelo.enums.EstadoCita;
import modelo.personas.Medico;
import modelo.personas.Paciente;

import java.time.LocalDateTime;

public class CitaMedica {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private String motivo;
    private EstadoCita estado;
    private double costo;
    private Diagnostico diagnostico;

    public CitaMedica(String id, Paciente paciente, Medico medico, LocalDateTime fechaHora, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = EstadoCita.EN_ESPERA;
        this.diagnostico = diagnostico;
        this.costo = calcularCosto();
    }

    public double calcularCosto() {
        return medico.getEspecialidad().getCostoConsulta();
    }

    public void confirmar() {
        estado = EstadoCita.CONFIRMADA;
    }

    public void iniciarAtencion() {
        estado = EstadoCita.EN_ATENCION;
    }

    public void completar() {
        estado = EstadoCita.COMPLETADA;
    }

    public void cancelar() {
        estado = EstadoCita.CANCELADA;
    }

    public String getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public double getCosto() {
        return costo;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }



    public void setDiagnostico(Diagnostico d) {
        this.diagnostico = d;
    }
}
