package vista;
import controlador.SistemaECU911;
import modelo.Ciudadano;
import modelo.LlamadaEmergencia;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Vista de consola para administrar y consultar los ciudadanos registrados.
 */
public class VistaCiudadano {
    /**
     * Muestra el listado de ciudadanos y permite consultar el historial de llamadas por cédula.
     *
     * @param sc Scanner para leer las entradas del usuario.
     * @param sistema Controlador principal de ECU 911.
     */
   public static void administrarCiudadanos(Scanner sc, SistemaECU911 sistema) {
        boolean salir = false;
        while (!salir) {
            System.out.println("SISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Versión 1.0)");
            System.out.println("[Menú Principal > 3. Administrar ciudadanos]\n");
            mostrarCiudadanos(sistema);
            System.out.println("\n¿Qué acción desea realizar?");
            System.out.println("[1] Consultar historial de llamadas de un ciudadano");
            System.out.println("[2] Regresar al Menú Principal");

            int opcion = ConsolaUtil.validarOpcionMenu(sc, "> Seleccione una opción (1-2): ", 1, 2);
            if (opcion == 1) {
                consultarHistorial(sc, sistema);
                ConsolaUtil.esperarEnter(sc, "\nPresione ENTER para regresar a Administrar ciudadanos");
            } 
            else if (opcion == 2) {
                salir = true;
            }
        }
    }
   /**
     * Lista todos los ciudadanos registrados junto con el total de llamadas que ha realizado cada uno.
     *
     * @param sistema Controlador que contiene las listas de ciudadanos y llamadas.
     */
    private static void mostrarCiudadanos(SistemaECU911 sistema) {
        System.out.println("Listado de ciudadanos registrados:");
        System.out.println("CÉDULA | NOMBRE COMPLETO | TOTAL LLAMADAS");

        ArrayList<Ciudadano> ciudadanos = sistema.getCiudadanos();
        ArrayList<LlamadaEmergencia> llamadas = sistema.getLlamadas();
        if (ciudadanos == null || ciudadanos.isEmpty()) {
            System.out.println("No hay ciudadanos registrados");
            return;
        }
        for (Ciudadano c : ciudadanos) {
            int totalLlamadas = 0;
            if (llamadas != null) {
                for (LlamadaEmergencia llamada : llamadas) {
                    if (llamada.getCiudadano() != null && llamada.getCiudadano().getCedula().equals(c.getCedula())) {
                        totalLlamadas++;
                    }
                }
            }
            System.out.println(c.getCedula() + " | " + c.getNombres() + " " + c.getApellidos() + " | " + totalLlamadas);
        }
    } 
   /**
     * Solicita la cédula de un ciudadano y muestra el historial de sus llamadas registradas.
     *
     * @param sc Scanner para leer la cédula ingresada.
     * @param sistema Controlador principal del sistema ECU 911.
     */
    private static void consultarHistorial(Scanner sc, SistemaECU911 sistema) {
        String cedula = ConsolaUtil.validarCedula(sc, "> Ingrese la cédula del ciudadano: ");
        Ciudadano ciudadano = sistema.buscarCiudadano(cedula);
        if (ciudadano == null) {
            System.out.println("No se encontró un ciudadano con la cédula " + cedula + ".");
            return;
        }
        System.out.println("\nHISTORIAL DE LLAMADAS - " + ciudadano.getNombres() + " " + ciudadano.getApellidos() + " (C.I. " + ciudadano.getCedula() + ")");
        System.out.println("CÓDIGO | FECHA Y HORA | TIPO | BARRIO | ESTADO");
        ArrayList<LlamadaEmergencia> llamadas = sistema.getLlamadas();
        boolean tieneLlamadas = false;

        if (llamadas != null) {
            for (LlamadaEmergencia llamada : llamadas) {
                if (llamada.getCiudadano() != null && llamada.getCiudadano().getCedula().equals(cedula)) {
                    tieneLlamadas = true;
                    System.out.println(llamada.getCodigo() + " | " + llamada.getFechaHora() + " | " + llamada.getTipoEmergencia() + " | " + llamada.getBarrio() + " | " + llamada.getEstado());
                }
            }
        }
        if (!tieneLlamadas) {
            System.out.println("No se registran llamadas para este ciudadano.");
        }
    } 
