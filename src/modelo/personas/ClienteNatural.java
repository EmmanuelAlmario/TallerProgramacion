package modelo.personas;

import modelo.abstractas.Cliente;
import modelo.enums.TipoDocumento;
import modelo.interfaces.Consultable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ClienteNatural extends Cliente implements Consultable {

    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

    public ClienteNatural(String id, String nombre, String apellido,
                          LocalDate fechaNacimiento, String email,
                          TipoDocumento tipoDocumento, String numeroDocumento,
                          LocalDateTime creacion, String usuario) {

        super(id, nombre, apellido, fechaNacimiento, email);
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        registrarModificacion(usuario);
    }

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

    @Override
    public String obtenerResumen() {
        return getNombreCompleto() + " - " + numeroDocumento;
    }

    @Override
    public boolean estaActivo() {
        return true;
    }
}
