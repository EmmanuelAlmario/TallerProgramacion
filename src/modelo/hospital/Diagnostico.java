package modelo.hospital;

import modelo.personas.Medico;
import java.time.LocalDate;

public class Diagnostico {
    private String id, descripcion, receta;
    private LocalDate fecha;
    private Medico medico;

    public Diagnostico(String id, String descripcion, String receta, Medico medico) {
        this.id = id;
        this.descripcion = descripcion;
        this.receta = receta;
        this.fecha = LocalDate.now();
        this.medico = medico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}

