package modelo.banco;

import modelo.abstractas.*;
import modelo.excepciones.*;

public class Banco {

    private String nombre;
    private Empleado[] empleados = new Empleado[50];
    private Cliente[] clientes = new Cliente[200];
    private Cuenta[] cuentas = new Cuenta[500];

    private int contEmp, contCli, contCuentas;

    public void registrarCliente(Cliente c) throws CapacidadExcedidaException {
        if (contCli >= clientes.length) {
            throw new CapacidadExcedidaException(clientes.length);
        }
        clientes[contCli++] = c;
    }

    public void registrarEmpleado(Empleado e) throws CapacidadExcedidaException {
        if (contEmp >= empleados.length) {
            throw new CapacidadExcedidaException(empleados.length);
        }
        empleados[contEmp++] = e;
    }

    public Cliente buscarCliente(String id) throws ClienteNoEncontradoException {
        for (int i = 0; i < contCli; i++) {
            if (clientes[i].obtenerDocumentoIdentidad().equals(id)) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException(id);
    }

    public void abrirCuenta(String idCliente, Cuenta c)
            throws ClienteNoEncontradoException, CapacidadExcedidaException {

        Cliente cliente = buscarCliente(idCliente);

        if (contCuentas >= cuentas.length) {
            throw new CapacidadExcedidaException(cuentas.length);
        }

        cliente.agregarCuenta(c);

        cuentas[contCuentas++] = c;
    }

    public double calcularNominaTotal() {
        double total = 0;
        for (int i = 0; i < contEmp; i++) {
            total += empleados[i].calcularSalario();
        }
        return total;
    }

    public void calcularInteresesMensuales() {
        for (int i = 0; i < contCuentas; i++) {
            cuentas[i].calcularInteres();
        }
    }
}