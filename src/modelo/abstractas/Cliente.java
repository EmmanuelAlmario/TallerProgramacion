package modelo.abstractas;

import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Notificable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Cliente extends Persona implements Notificable, Auditable {
    private Cuenta[] cuentas = new Cuenta[5];
    private int cantidadCuentas = 0;

    private final LocalDateTime creacion;
    private LocalDateTime modificacion;
    private String usuario;

    public Cliente(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email) {
        super(id, nombre, apellido, fechaNacimiento, email);
        this.creacion = LocalDateTime.now();
        this.modificacion = this.creacion;
        this.usuario = "sistema";
    }

    public void agregarCuenta(Cuenta c) throws CapacidadExcedidaException {
        if (c == null) {
            throw new DatoInvalidoException("cuenta", null);
        }

        if (cantidadCuentas >= cuentas.length) {
            throw new CapacidadExcedidaException(cuentas.length);
        }

        cuentas[cantidadCuentas++] = c;
    }

    public Cuenta[] getCuentas() {
        Cuenta[] copia = new Cuenta[cantidadCuentas];
        System.arraycopy(cuentas, 0, copia, 0, cantidadCuentas);
        return copia;
    }

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
