package modelo.personas;

import modelo.abstractas.Persona;
import modelo.enums.GrupoSanguineo;
import modelo.hospital.CitaMedica;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {

    private String historiaClinicaId;
    private GrupoSanguineo grupoSanguineo;
    private List<String> alergias;
    private List<String> contraindicaciones;
    private List<CitaMedica> citas;

    // Constructor
    public Paciente(String id, String nombre, String apellido,
                    LocalDate fechaNacimiento, String email,
                    String historiaClinicaId, GrupoSanguineo grupoSanguineo) {
        super(id, nombre, apellido, fechaNacimiento, email);
        this.historiaClinicaId = historiaClinicaId;
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = new ArrayList<>();
        this.contraindicaciones = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    @Override
    public int calcularEdad() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }

    @Override
    public String obtenerTipo() {
        return "Paciente";
    }

    public void agregarAlergia(String alergia) {
        if (alergia != null && !alergia.isBlank())
            this.alergias.add(alergia);
    }

    public void agregarContraindicacion(String contraindicacion) {
        if (contraindicacion != null && !contraindicacion.isBlank())
            this.contraindicaciones.add(contraindicacion);
    }

    public void agregarCita(CitaMedica cita) {
        if (cita != null)
            this.citas.add(cita);
    }

    public List<CitaMedica> obtenerHistorial() {
        return new ArrayList<>(citas); // copia defensiva obligatoria
    }

    // Getters y Setters
    public String getHistoriaClinicaId() {
        return historiaClinicaId;
    }

    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }
    public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public List<String> getAlergias() {
        return new ArrayList<>(alergias);
    }
    public List<String> getContraindicaciones() {
        return new ArrayList<>(contraindicaciones);
    }
}