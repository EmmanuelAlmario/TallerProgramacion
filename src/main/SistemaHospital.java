package main;

import modelo.abstractas.Empleado;
import modelo.enums.GrupoSanguineo;
import modelo.enums.Turno;
import modelo.hospital.CitaMedica;
import modelo.hospital.Diagnostico;
import modelo.hospital.Especialidad;
import modelo.hospital.Hospital;
import modelo.personas.Cirujano;
import modelo.personas.Enfermero;
import modelo.personas.Medico;
import modelo.personas.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SistemaHospital {

    public static void main(String[] args) {

        // 1. Crear hospital
        Hospital hospital = new Hospital("Hospital Central", "Calle 123 #45-67");

        // 2. Crear especialidad
        Especialidad cardiologia = new Especialidad("CARD", "Cardiología", "Especialidad del corazón", 150000);

        // 3. Crear empleados
        Medico medico = new Medico(
                "E001", "Carlos", "Ramírez", LocalDate.of(1980, 5, 10),
                "carlos@hospital.com", "LEG001", LocalDate.of(2015, 3, 1),
                3000000, true, 12345, cardiologia, 0
        );

        Cirujano cirujano = new Cirujano(
                "E002", "Ana", "López", LocalDate.of(1978, 8, 20),
                "ana@hospital.com", "LEG002", LocalDate.of(2010, 6, 15),
                4000000, true, 67890, cardiologia, 0, 25, true
        );

        Enfermero enfermero = new Enfermero(
                "E003", "Luis", "Gómez", LocalDate.of(1990, 2, 14),
                "luis@hospital.com", "LEG003", LocalDate.of(2020, 1, 10),
                1500000, true, Turno.MAÑANA, "Urgencias"
        );

        // 4. Contratar empleados
        hospital.contratarEmpleado(medico);
        hospital.contratarEmpleado(cirujano);
        hospital.contratarEmpleado(enfermero);

        // 5. Crear y registrar paciente
        Paciente paciente = new Paciente(
                "P001", "María", "Torres", LocalDate.of(1995, 11, 3),
                "maria@email.com", "HC-001", GrupoSanguineo.O_POSITIVO
        );
        paciente.agregarAlergia("Penicilina");
        paciente.agregarContraindicacion("Ibuprofeno");
        hospital.registrarPaciente(paciente);

        // 6. Crear y agendar cita
        CitaMedica cita = new CitaMedica(
                "CIT001", paciente, medico,
                LocalDateTime.of(2025, 6, 10, 9, 0),
                "Revisión cardiológica"
        );
        hospital.agendarCita(cita);
        paciente.agregarCita(cita);

        // 7. Cambiar estados de la cita
        cita.confirmar();
        System.out.println("Estado: " + cita.getEstado()); // CONFIRMADA

        cita.iniciarAtencion();
        System.out.println("Estado: " + cita.getEstado()); // EN_ATENCION

        // Registrar diagnóstico
        Diagnostico diagnostico = new Diagnostico(
                "D001", "Hipertensión leve", "Enalapril 5mg", medico
        );
        medico.registrarDiagnostico(cita, diagnostico);

        cita.completar();
        System.out.println("Estado: " + cita.getEstado()); // COMPLETADA

        // 8. Polimorfismo: calcularSalario() diferente por tipo
        System.out.println("\n--- Nómina Total ---");
        List<Empleado> empleados = hospital.getEmpleados();
        for (Empleado e : empleados) {
            System.out.println(e.obtenerTipo() + " - " + e.getNombre() +
                    ": $" + e.calcularSalario());
        }
        System.out.println("Total nómina: $" + hospital.calcularNominaTotal());

        // 9. Historial del paciente
        System.out.println("\n--- Historial de " + paciente.getNombre() + " ---");
        System.out.println("Alergias: " + paciente.getAlergias());
        System.out.println("Citas: " + paciente.obtenerHistorial().size());
    }
}