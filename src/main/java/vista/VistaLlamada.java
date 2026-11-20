package vista;
import controlador.SistemaECU911;
import java.util.Scanner;
public class VistaLlamada {
  private SistemaECU911 sistema;
  private Scanner scanner;

public VistaLlamada(SistemaECU911 sistema, Scanner scanner){ //Constructor
 this.sistema = sistema;
 this.scanner = scanner;
}
public void mostrarMenu() { //Menú Principal
        String opcion = "";

        while (!opcion.equals("5")) {
            System.out.println("\nSISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Version 1.0)");
            System.out.println("[Menu Principal > 1. Administrar llamadas de emergencia]");
            System.out.println("[1] Lista de llamadas registradas");
            System.out.println("[2] Registrar llamada");
            System.out.println("[3] Actualizar estado de una llamada");
            System.out.println("[4] Anular llamada");
            System.out.println("[5] Regresar al Menu Principal");
            System.out.print("> Seleccione una opcion (1-5): ");

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Pendiente: Listar llamadas.");
                    break;
                case "2":
                    System.out.println("Pendiente: Registrar llamada.");
                    break;
                case "3":
                    System.out.println("Pendiente: Actualizar estado.");
                    break;
                case "4":
                    System.out.println("Pendiente: Anular llamada.");
                    break;
                case "5":
                    System.out.println("Regresando al Menu Principal...");
                    break;
                default:
                    System.out.println("Error: Opcion invalida. Ingrese un numero del 1 al 5.");
            }
        }
    }  
}
