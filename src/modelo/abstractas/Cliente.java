package modelo.abstractas;

import modelo.abstractas.Cuenta;
import modelo.abstractas.Persona;
import modelo.excepciones.*;

import java.time.LocalDate;

public abstract class Cliente extends Persona {
    private Cuenta[] cuentas = new Cuenta[5];
    private int cantidadCuentas = 0;

    public Cliente(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email) {
        super(id, nombre, apellido, fechaNacimiento, email);
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
}