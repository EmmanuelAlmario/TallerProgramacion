package modelo.personas;

import modelo.abstractas.Cliente;
import modelo.interfaces.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteEmpresarial extends Cliente implements Consultable, Notificable, Auditable {

    private String nit;
    private String razonSocial;
    private String representanteLegal;

    // Auditable
    private LocalDateTime creacion;
    private LocalDateTime modificacion;
    private String usuario;

    public ClienteEmpresarial(String id, String nombre, String apellido,
                              LocalDate fechaNacimiento, String email,
                              String nit, String razonSocial, String representanteLegal,
                              LocalDateTime creacion, String usuario) {

        super(id, nombre, apellido, fechaNacimiento, email);
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.representanteLegal = representanteLegal;
        this.creacion = creacion;
        this.usuario = usuario;
    }

    // =========================
    // MÉTODOS ABSTRACTOS (Persona)
    // =========================

    @Override
    public String obtenerTipo() {
        return "Cliente Empresarial";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return nit;
    }

    @Override
    public int calcularEdad() {
        return 0; // No aplica realmente para empresa
    }

    // =========================
    // INTERFAZ Consultable
    // =========================

    @Override
    public String obtenerResumen() {
        return razonSocial + " - NIT: " + nit;
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
            System.out.println("Notificación empresa: " + mensaje);
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