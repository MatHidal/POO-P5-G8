package vista;
import controlador.SistemaECU911;
import java.util.Scanner;
import java.util.ArrayList;
import modelo.LlamadaEmergencia;
public class VistaLlamada {
  private SistemaECU911 sistema;
  private Scanner scanner;

public VistaLlamada(SistemaECU911 sistema, Scanner scanner) { //Constructor - SG
        this.sistema = sistema;
        this.scanner = scanner;
    }

    public void mostrarMenu() { //Menu Principal - SG
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- MENU DE LLAMADAS ---");
            System.out.println("1. Listar llamadas");
            System.out.println("2. Registrar llamada");
            System.out.println("3. Actualizar estado");
            System.out.println("4. Anular llamada");
            System.out.println("5. Regresar");
            System.out.print("Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listarLlamadas();
                    break;
                case 2:
                    System.out.println("Pendiente.");
                    break;
                case 3:
                    actualizarEstado();
                    break;
                case 4:
                    System.out.println("Pendiente.");
                    break;
                case 5:
                    System.out.println("Regresando...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private void listarLlamadas() { //Listar Llamada - SG
        System.out.println("\n--- LISTA DE LLAMADAS ---");
        ArrayList<LlamadaEmergencia> lista = sistema.getLlamadas();

        if (lista.size() == 0) {
            System.out.println("No hay llamadas registradas.");
        } else {
            for (int i = lista.size() - 1; i >= 0; i--) {
                LlamadaEmergencia ll = lista.get(i);
                String nombre = ll.getCiudadano().getNombres() + " " + ll.getCiudadano().getApellidos();
                System.out.println(ll.getCodigo() + " | " + ll.getFechaHora() + " | " + nombre + " | " + ll.getTipoEmergencia() + " | " + ll.getCiudad() + " | " + ll.getEstado());
            }
        }
    }

    private void actualizarEstado() { //Actualizar Estado - SG
        System.out.println("\n--- ACTUALIZAR ESTADO ---");
        ArrayList<LlamadaEmergencia> lista = sistema.getLlamadas();

        if (lista.size() == 0) {
            System.out.println("No hay llamadas registradas.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                LlamadaEmergencia ll = lista.get(i);
                System.out.println(ll.getCodigo() + " | " + ll.getCiudadano().getNombres() + " | " + ll.getEstado());
            }

            System.out.print("Ingrese el codigo de la llamada: ");
            String codigo = scanner.nextLine();

            boolean cambio = sistema.actualizarEstadoLlamada(codigo);
            if (cambio == true) {
                System.out.println("Estado actualizado.");
            } else {
                System.out.println("No se encontro la llamada o ya fue atendida.");
            }
        }
    }
}