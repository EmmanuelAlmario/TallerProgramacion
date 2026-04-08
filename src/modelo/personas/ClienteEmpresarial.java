package modelo.personas;

import modelo.abstractas.Cliente;
import modelo.interfaces.Consultable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteEmpresarial extends Cliente implements Consultable {

    private String nit;
    private String razonSocial;
    private String representanteLegal;

    public ClienteEmpresarial(String id, String nombre, String apellido,
                              LocalDate fechaNacimiento, String email,
                              String nit, String razonSocial, String representanteLegal,
                              LocalDateTime creacion, String usuario) {

        super(id, nombre, apellido, fechaNacimiento, email);
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.representanteLegal = representanteLegal;
        registrarModificacion(usuario);
    }

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
        return 0;
    }

    @Override
    public String obtenerResumen() {
        return razonSocial + " - NIT: " + nit;
    }

    @Override
    public boolean estaActivo() {
        return true;
    }

    @Override
    public void notificar(String mensaje) {
        if (aceptaNotificaciones()) {
            System.out.println("Notificación empresa: " + mensaje);
        }
    }
}
