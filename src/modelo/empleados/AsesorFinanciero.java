package modelo.empleados;

import modelo.abstractas.Cliente;
import modelo.abstractas.Empleado;
import modelo.interfaces.Consultable;

import java.time.LocalDate;
import java.time.Period;

public class AsesorFinanciero extends Empleado implements Consultable {

    private double comisionBase;
    private double metasMensuales;
    private Cliente[] clientesAsignados = new Cliente[20];
    private int cantidadClientes = 0;

    public AsesorFinanciero(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String legajo, LocalDate fechaContratacion, double salarioBase, boolean activo, double metasMensuales, double comisionBase) {
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase, activo);
        this.metasMensuales = metasMensuales;
        this.comisionBase = comisionBase;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    @Override
    public double calcularBono() {
        if (cantidadClientes >= metasMensuales) {
            return comisionBase;
        }
        return 0;
    }

    public void agregarCliente(Cliente c) {
        if (cantidadClientes < clientesAsignados.length) {
            clientesAsignados[cantidadClientes++] = c;
            registrarModificacion(getLegajo());
        }
    }

    @Override
    public int calcularEdad() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }

    @Override
    public String obtenerTipo() {
        return "Asesor Financiero";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return "Legajo: " + getLegajo();
    }

    @Override
    public String obtenerResumen() {
        return getNombreCompleto() + " - " + obtenerDocumentoIdentidad() + " - Clientes: " + cantidadClientes;
    }

    @Override
    public boolean estaActivo() {
        return isActivo();
    }
}
