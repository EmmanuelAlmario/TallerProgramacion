package principal;

import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.banco.Banco;
import modelo.banco.Transaccion;
import modelo.cuentas.CuentaAhorros;
import modelo.cuentas.CuentaCorriente;
import modelo.cuentas.CuentaCredito;
import modelo.empleados.AsesorFinanciero;
import modelo.empleados.Cajero;
import modelo.empleados.GerenteSucursal;
import modelo.enums.EstadoTransaccion;
import modelo.enums.TipoDocumento;
import modelo.enums.Turno;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.EstadoTransaccionInvalidoException;
import modelo.excepciones.PermisoInsuficienteException;
import modelo.excepciones.SaldoInsuficienteException;
import modelo.excepciones.SistemaBancarioException;
import modelo.personas.ClienteEmpresarial;
import modelo.personas.ClienteNatural;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SistemaBancarioDemo {

    public static void main(String[] args) {
        Banco banco = new Banco("Banco POO");

        ClienteNatural clienteNatural1 = new ClienteNatural(
                "CN-001", "Ana", "Gomez", LocalDate.of(1995, 3, 15), "ana@correo.com",
                TipoDocumento.CEDULA, "123456", LocalDateTime.now(), "cajero-01"
        );
        ClienteNatural clienteNatural2 = new ClienteNatural(
                "CN-002", "Luis", "Perez", LocalDate.of(1992, 8, 21), "luis@correo.com",
                TipoDocumento.CEDULA, "654321", LocalDateTime.now(), "cajero-01"
        );
        ClienteEmpresarial clienteEmpresarial1 = new ClienteEmpresarial(
                "CE-001", "Maria", "Rodriguez", LocalDate.of(1980, 1, 10), "empresa@correo.com",
                "900123456", "Tech SAS", "Maria Rodriguez", LocalDateTime.now(), "cajero-01"
        );

        // Escenario 1: registrar 2 clientes naturales y 1 empresarial
        try {
            banco.registrarCliente(clienteNatural1);
            banco.registrarCliente(clienteNatural2);
            banco.registrarCliente(clienteEmpresarial1);
            System.out.println("Escenario 1 OK: clientes registrados");
        } catch (CapacidadExcedidaException e) {
            System.out.println("Escenario 1 ERROR: " + e);
        }

        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC-001", 1_000_000, 500_000, 15_000);
        CuentaAhorros cuentaAhorros = new CuentaAhorros("CA-001", 500_000, 0.06, 3);
        CuentaCredito cuentaCredito = new CuentaCredito("CR-001", 0, 2_000_000, 0.18);

        // Escenario 2: abrir una cuenta de cada tipo
        try {
            banco.abrirCuenta(clienteNatural1.obtenerDocumentoIdentidad(), cuentaCorriente);
            banco.abrirCuenta(clienteNatural2.obtenerDocumentoIdentidad(), cuentaAhorros);
            banco.abrirCuenta(clienteEmpresarial1.obtenerDocumentoIdentidad(), cuentaCredito);
            System.out.println("Escenario 2 OK: cuentas abiertas");
        } catch (ClienteNoEncontradoException | CapacidadExcedidaException e) {
            System.out.println("Escenario 2 ERROR: " + e);
        }

        // Escenario 3: deposito exitoso y captura de cuenta bloqueada
        try {
            cuentaAhorros.depositar(100_000);
            System.out.println("Escenario 3 OK: deposito exitoso en ahorros");
            cuentaAhorros.setBloqueada(true);
            cuentaAhorros.depositar(10_000);
        } catch (CuentaBloqueadaException e) {
            System.out.println("Escenario 3 EXCEPCION esperada: " + e.getMessage());
        }
        cuentaAhorros.setBloqueada(false);

        // Escenario 4: retiro exitoso y saldo insuficiente
        try {
            cuentaCorriente.retirar(100_000);
            System.out.println("Escenario 4 OK: retiro exitoso");
            cuentaCorriente.retirar(3_000_000);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Escenario 4 EXCEPCION esperada: saldoActual=" + e.getSaldoActual() +
                    ", montoSolicitado=" + e.getMontoSolicitado());
        } catch (SistemaBancarioException e) {
            System.out.println("Escenario 4 ERROR: " + e.getMessage());
        }

        // Escenario 5: transferencia entre cuentas
        try {
            double montoTransferencia = 50_000;
            cuentaCorriente.retirar(montoTransferencia);
            cuentaAhorros.depositar(montoTransferencia);

            Transaccion transferencia = new Transaccion(
                    "TX-001", cuentaCorriente, cuentaAhorros, montoTransferencia, "Transferencia de prueba"
            );
            transferencia.cambiarEstado(EstadoTransaccion.PROCESANDO);
            transferencia.cambiarEstado(EstadoTransaccion.COMPLETADA);

            cuentaCorriente.agregarAlHistorial(transferencia);
            cuentaAhorros.agregarAlHistorial(transferencia);

            System.out.println("Escenario 5 OK: transferencia realizada");
        } catch (SistemaBancarioException e) {
            System.out.println("Escenario 5 ERROR: " + e.getMessage());
        }

        // Escenario 6: polimorfismo en Empleado[]
        Empleado cajero = new Cajero(
                "E-001", "Pedro", "Lopez", LocalDate.of(1998, 7, 11), "pedro@correo.com",
                "L-001", LocalDate.of(2020, 1, 10), 2_000_000, true, Turno.MAÑANA, "Norte"
        );
        Empleado asesor = new AsesorFinanciero(
                "E-002", "Sofia", "Diaz", LocalDate.of(1994, 4, 8), "sofia@correo.com",
                "L-002", LocalDate.of(2019, 2, 15), 2_500_000, true, 10, 300_000
        );
        Empleado gerente = new GerenteSucursal(
                "E-003", "Carlos", "Ruiz", LocalDate.of(1989, 12, 1), "carlos@correo.com",
                "L-003", LocalDate.of(2015, 3, 1), 4_000_000, true, "Centro", 500_000_000
        );

        Empleado[] empleados = {cajero, asesor, gerente};
        for (Empleado empleado : empleados) {
            System.out.println("Escenario 6 -> " + empleado.obtenerTipo() + " salario=" + empleado.calcularSalario());
        }

        // Escenario 7: polimorfismo en Cuenta[]
        Cuenta[] cuentas = {cuentaCorriente, cuentaAhorros, cuentaCredito};
        for (Cuenta cuenta : cuentas) {
            System.out.println("Escenario 7 -> " + cuenta.getTipoCuenta() + " interes=" + cuenta.calcularInteres());
        }

        // Escenario 8: transicion invalida en estado de transaccion
        try {
            Transaccion txInvalida = new Transaccion("TX-002", cuentaAhorros, null, 10_000, "Transicion invalida");
            txInvalida.cambiarEstado(EstadoTransaccion.COMPLETADA);
        } catch (EstadoTransaccionInvalidoException e) {
            System.out.println("Escenario 8 EXCEPCION esperada: " + e.getMessage());
        }

        // Escenario 9: aprobar credito desde cajero
        try {
            GerenteSucursal gerenteSucursal = (GerenteSucursal) gerente;
            gerenteSucursal.aprobarCredito(cajero);
        } catch (PermisoInsuficienteException e) {
            System.out.println("Escenario 9 EXCEPCION esperada: " + e.getMessage());
        }

        // Escenario 10: notificar cliente que acepta y uno que no
        clienteNatural1.notificar("Su deposito fue aplicado.");

        ClienteEmpresarial clienteSinNotificaciones = new ClienteEmpresarial(
                "CE-002", "Laura", "Acosta", LocalDate.of(1985, 5, 9), "no-notify@correo.com",
                "900555333", "NoNotify SAS", "Laura Acosta", LocalDateTime.now(), "cajero-01"
        ) {
            @Override
            public boolean aceptaNotificaciones() {
                return false;
            }
        };
        clienteSinNotificaciones.notificar("Este mensaje no deberia mostrarse.");
        System.out.println("Escenario 10 OK: se probaron clientes con y sin notificacion");

        // Escenario 11: registrar modificacion y consultar auditoria en cuenta
        cuentaCorriente.registrarModificacion("L-001");
        System.out.println("Escenario 11 -> ultimaModificacion=" + cuentaCorriente.obtenerUltimaModificacion() +
                ", usuario=" + cuentaCorriente.obtenerUsuarioModificacion());

        // Escenario 12: calcular nomina total del banco
        try {
            banco.registrarEmpleado(cajero);
            banco.registrarEmpleado(asesor);
            banco.registrarEmpleado(gerente);
        } catch (CapacidadExcedidaException e) {
            System.out.println("Escenario 12 ERROR registrando empleados: " + e.getMessage());
        }
        System.out.println("Escenario 12 -> nomina total=" + banco.calcularNominaTotal());
    }
}
