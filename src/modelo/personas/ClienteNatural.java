package modelo.personas;

import modelo.abstractas.Cliente;
import modelo.enums.TipoDocumento;
import modelo.interfaces.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ClienteNatural extends Cliente implements Consultable, Notificable, Auditable {

    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

    // Auditable
    private LocalDateTime creacion;
    private LocalDateTime modificacion;
    private String usuario;

    public ClienteNatural(String id, String nombre, String apellido,
                          LocalDate fechaNacimiento, String email,
                          TipoDocumento tipoDocumento, String numeroDocumento,
                          LocalDateTime creacion, String usuario) {

        super(id, nombre, apellido, fechaNacimiento, email);
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.creacion = creacion;
        this.usuario = usuario;
    }

    // =========================
    // MÉTODOS ABSTRACTOS (Persona)
    // =========================

    @Override
    public String obtenerTipo() {
        return "Cliente Natural";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return tipoDocumento + " - " + numeroDocumento;
    }

    @Override
    public int calcularEdad() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }

    // =========================
    // INTERFAZ Consultable
    // =========================

    @Override
    public String obtenerResumen() {
        return getNombreCompleto() + " - " + numeroDocumento;
    }

    @Override
    public boolean estaActivo() {
        return true;
    }

    // =========================
    // INTERFAZ Notificable
    // =========================

    @Override
    public void notificar(String mensaje) {
        if (aceptaNotificaciones()) {
            System.out.println("Notificación: " + mensaje);
        }
    }

    @Override
    public String obtenerContacto() {
        return getEmail();
    }

    @Override
    public boolean aceptaNotificaciones() {
        return true;
    }

    // =========================
    // INTERFAZ Auditable
    // =========================


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