package modelo.banco;

import modelo.abstractas.Cliente;
import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.interfaces.Auditable;

import java.time.LocalDateTime;

public class Banco implements Auditable {

    private String nombre;
    private Empleado[] empleados = new Empleado[50];
    private Cliente[] clientes = new Cliente[200];
    private Cuenta[] cuentas = new Cuenta[500];

    private int contEmp, contCli, contCuentas;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = fechaCreacion;
        this.usuarioModificacion = "sistema";
    }

    public void registrarCliente(Cliente c) throws CapacidadExcedidaException {
        if (contCli >= clientes.length) {
            throw new CapacidadExcedidaException(clientes.length);
        }
        clientes[contCli++] = c;
        registrarModificacion("registro-cliente");
    }

    public void registrarEmpleado(Empleado e) throws CapacidadExcedidaException {
        if (contEmp >= empleados.length) {
            throw new CapacidadExcedidaException(empleados.length);
        }
        empleados[contEmp++] = e;
        registrarModificacion("registro-empleado");
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
        registrarModificacion("abrir-cuenta");
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

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return ultimaModificacion;
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return usuarioModificacion;
    }

    @Override
    public void registrarModificacion(String usuario) {
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
    }
}
