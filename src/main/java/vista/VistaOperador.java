package vista;

import controlador.SistemaECU911;
import java.util.Scanner;
import modelo.Operador;

/**
 * Vista encargada de la gestión de operadores del sistema ECU 911.
 */
public class VistaOperador {

    private SistemaECU911 sistema;
    private Scanner scanner;

    /**
     * Constructor de la vista de operadores.
     *
     * @param sistema Controlador principal del sistema ECU 911.
     * @param scanner Objeto Scanner para lectura de datos por consola.
     */
    public VistaOperador(SistemaECU911 sistema, Scanner scanner) {
        this.sistema = sistema;
        this.scanner = scanner;
    }

    /**
     * Muestra el menú de administración de operadores y gestiona sus opciones.
     */
    public void mostrarMenu() {
        String opcion = "";

        while (!opcion.equals("4")) {
            System.out.println("\nSISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Version 1.0)");
            System.out.println("[Menu Principal > 2. Administrar operadores]");
            System.out.println("[1] Lista de operadores");
            System.out.println("[2] Registrar operador");
            System.out.println("[3] Eliminar operador");
            System.out.println("[4] Regresar al Menu Principal");
            System.out.print("> Seleccione una opcion (1-4): ");

            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    listarOperadores();
                    break;
                case "2":
                    registrarOperador();
                    break;
                case "3":
                    eliminarOperador();
                    break;
                case "4":
                    System.out.println("Regresando al Menu Principal...");
                    break;
                default:
                    System.out.println("Error: Opcion invalida. Ingrese un numero del 1 al 4.");
            }
        }
    }

    private void listarOperadores() {
        System.out.println("\n[Lista de operadores registrados]");
        if (sistema.getOperadores().isEmpty()) {
            System.out.println("No hay operadores registrados en el sistema.");
            return;
        }

        System.out.printf("%-10s %-28s %-15s %-28s %-10s%n",
                "CODIGO", "NOMBRE COMPLETO", "TURNO", "EMAIL", "LLAMADAS");

        for (Operador op : sistema.getOperadores()) {
            String nombreCompleto = op.getNombres() + " " + op.getApellidos();
            String emailMostrar = (op.getEmail() == null || op.getEmail().isEmpty()) ? "No registrado" : op.getEmail();
            int cantLlamadas = sistema.contarLlamadasDeOperador(op);

            System.out.printf("%-10s %-28s %-15s %-28s %-10d%n",
                    op.getCodigo(),
                    nombreCompleto,
                    op.getTurno(),
                    emailMostrar,
                    cantLlamadas);
        }
    }

    private void registrarOperador() {
        System.out.println("\n[Registrar nuevo operador]");
        String nombres = ConsolaUtil.validarTexto("Ingrese los nombres del operador:", scanner);
        String apellidos = ConsolaUtil.validarTexto("Ingrese los apellidos del operador:", scanner);

        String turno = "";
        while (turno.isEmpty()) {
            System.out.println("Seleccione el turno:");
            System.out.println("[1] Matutino");
            System.out.println("[2] Vespertino");
            System.out.println("[3] Nocturno");
            System.out.print("> Opcion (1-3): ");
            String opTurno = scanner.nextLine().trim();

            switch (opTurno) {
                case "1":
                    turno = "Matutino";
                    break;
                case "2":
                    turno = "Vespertino";
                    break;
                case "3":
                    turno = "Nocturno";
                    break;
                default:
                    System.out.println("Error: Seleccione una opcion valida (1, 2 o 3).");
            }
        }

        System.out.println("Ingrese el email del operador (opcional, presione Enter para omitir):");
        String email = scanner.nextLine().trim();

        Operador nuevo = sistema.agregarOperador(nombres, apellidos, turno, email);
        System.out.println("Operador registrado exitosamente con codigo: " + nuevo.getCodigo());
    }

    private void eliminarOperador() {
        System.out.println("\n[Eliminar operador]");
        if (sistema.getOperadores().isEmpty()) {
            System.out.println("No hay operadores registrados para eliminar.");
            return;
        }

        listarOperadores();
        String codigo = ConsolaUtil.validarTexto("\nIngrese el codigo del operador a eliminar (ej. OP-1):", scanner);
        Operador encontrado = sistema.buscarOperadorPorCodigo(codigo);

        if (encontrado == null) {
            System.out.println("Error: No se encontro ningun operador con el codigo " + codigo + ".");
            return;
        }

        if (sistema.contarLlamadasDeOperador(encontrado) > 0) {
            System.out.println("No se puede eliminar al operador " + encontrado.getNombres() + " "
                    + encontrado.getApellidos() + " porque tiene llamadas de emergencia asociadas.");
            return;
        }

        System.out.print("¿Esta seguro de eliminar al operador " + encontrado.getNombres() + " "
                + encontrado.getApellidos() + "? (S/N): ");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            if (sistema.eliminarOperador(encontrado)) {
                System.out.println("Operador eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el operador.");
            }
        } else {
            System.out.println("Operacion cancelada.");
        }
    }
}
    

