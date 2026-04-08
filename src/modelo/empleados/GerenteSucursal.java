package modelo.empleados;

import modelo.abstractas.Empleado;
import modelo.excepciones.PermisoInsuficienteException;
import modelo.interfaces.Consultable;

import java.time.LocalDate;
import java.time.Period;

public class GerenteSucursal extends Empleado implements Consultable {

    private String sucursal;
    private double presupuestoAnual;
    private Empleado[] empleadosACargo = new Empleado[30];
    private int cantidadEmpleados = 0;

    public GerenteSucursal(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String legajo, LocalDate fechaContratacion, double salarioBase, boolean activo, String sucursal, double presupuestoAnual) {
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase, activo);
        this.sucursal = sucursal;
        this.presupuestoAnual = presupuestoAnual;
    }

    @Override
    public int calcularEdad() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }

    @Override
    public String obtenerTipo() {
        return "Gerente de Sucursal";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return "Legajo: " + getLegajo();
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono() + (calcularAntiguedad() * 50000);
    }

    @Override
    public double calcularBono() {
        return 200000;
    }

    public void aprobarCredito(Empleado solicitante) {
        if (!(solicitante instanceof GerenteSucursal)) {
            throw new PermisoInsuficienteException("Solo el gerente puede aprobar créditos");
        }
        registrarModificacion(getLegajo());
        System.out.println("Crédito aprobado");
    }

    @Override
    public String obtenerResumen() {
        return getNombreCompleto() + " - " + sucursal + " - Empleados a cargo: " + cantidadEmpleados;
    }

    @Override
    public boolean estaActivo() {
        return isActivo();
    }
}
