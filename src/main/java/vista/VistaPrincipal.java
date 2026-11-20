package vista;

import controlador.SistemaECU911;
import java.util.Scanner;

/**
 * Vista principal que gestiona el menú raíz del sistema ECU 911.
 */
public class VistaPrincipal {

    private SistemaECU911 sistema;
    private Scanner scanner;
    private VistaLlamada vistaLlamada;
    private VistaOperador vistaOperador;

    /**
     * Constructor de la vista principal.
     *
     * @param sistema Controlador principal del sistema ECU 911.
     * @param scanner Objeto Scanner compartido para lectura por consola.
     */
    public VistaPrincipal(SistemaECU911 sistema, Scanner scanner) {
        this.sistema = sistema;
        this.scanner = scanner;
        this.vistaLlamada = new VistaLlamada(sistema, scanner);
        this.vistaOperador = new VistaOperador(sistema, scanner);
    }

    /**
     * Inicia el bucle del menú principal de la aplicación.
     */
    public void iniciar() {
        String opcion = "";

        while (!opcion.equals("5")) {
            System.out.println("\nSISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Version 1.0)");
            System.out.println("[Menu Principal]");
            System.out.println("[1] Administrar llamadas de emergencia");
            System.out.println("[2] Administrar operadores");
            System.out.println("[3] Administrar ciudadanos");
            System.out.println("[4] Consultar y registrar seguimiento de llamadas");
            System.out.println("[5] Salir del sistema");
            System.out.print("> Seleccione una opcion (1-5): ");

            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    vistaLlamada.mostrarMenu();
                    break;
                case "2":
                    vistaOperador.mostrarMenu();
                    break;
                case "3":
                    System.out.println("Modulo de ciudadanos en construccion.");
                    break;
                case "4":
                    System.out.println("Modulo de seguimiento en construccion.");
                    break;
                case "5":
                    System.out.println("Cerrando el Sistema ECU 911. Hasta pronto.");
                    break;
                default:
                    System.out.println("Error: Opcion invalida. Ingrese un numero del 1 al 5.");
            }
        }
    }
}
