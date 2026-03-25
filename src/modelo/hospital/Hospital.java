package modelo.hospital;

import modelo.abstractas.Empleado;
import modelo.personas.Paciente;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String nombre;
    private String direccion;
    private List<Empleado> empleados;
    private List<Paciente> pacientes;
    private List<CitaMedica> citas;

    public Hospital(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.empleados = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public void registrarPaciente(Paciente e) {
        pacientes.add(e);
    }

    public void contratarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public void agendarCita(CitaMedica c) {
        citas.add(c);
    }

    public double calcularNominaTotal() {
        double nominaTotal = 0;

        for (Empleado e: empleados) {
            nominaTotal += e.calcularSalario();
        }

        return nominaTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }

    public List<Paciente> getPacientes() {
        return new ArrayList<>(pacientes);
    }

    public List<CitaMedica> getCitas() {
        return new ArrayList<>(citas);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
