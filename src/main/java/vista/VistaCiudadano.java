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
                ConsolaUtil.esperarEnter(sc, "\nPresione ENTER para regresar a Administrar ciudadanos...");
            } 
            else if (opcion == 2) {
                salir = true;
            }
        }
    }
