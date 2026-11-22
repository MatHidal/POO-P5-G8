package vista;

import controlador.SistemaECU911;
import modelo.LlamadaEmergencia;
import modelo.NotaSeguimiento;
import modelo.Operador;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Vista de consola para consultar y registrar notas de seguimiento de las llamadas.
 */
public class VistaNotaSeguimiento {

    /**
     * Solicita el código de una llamada y muestra el menú para ver o añadir notas.
     *
     * @param sc Scanner para leer las entradas del usuario.
     * @param sistema Controlador principal del sistema ECU 911.
     */
    public static void administrarNotasSeguimiento(Scanner sc, SistemaECU911 sistema) {
        System.out.println("\nSISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Versión 1.0)");
        System.out.println("[Menú Principal > 4. Notas de seguimiento]");
        String codigoLlamada = ConsolaUtil.validarTexto("> Ingrese el código de la llamada: ", sc);
        LlamadaEmergencia llamada = sistema.buscarLlamadaPorCodigo(codigoLlamada);

        if (llamada == null) {
            System.out.println("No se encontró una llamada con el código " + codigoLlamada + ".");
            esperarEnter(sc, "\nPresione ENTER para regresar al Menú Principal...");
            return;
        }
        boolean salir = false;
        while (!salir) {
            mostrarNotas(llamada);
            System.out.println("\n¿Qué acción desea realizar?");
            System.out.println("[1] Añadir una nueva nota");
            System.out.println("[2] Regresar al Menú Principal");
            int opcion = ConsolaUtil.validarOpcionMenu(sc, "> Seleccione una opción (1-2): ", 1, 2);

            if (opcion == 1) {
                agregarNota(sc, sistema, llamada);
                esperarEnter(sc, "\nPresione ENTER para actualizar y volver a Notas de seguimiento...");
            } 
            else if (opcion == 2) {
                salir = true;
            }
        }
    }
    /**
     * Muestra en consola las notas de una llamada desde la más reciente hasta la más antigua.
     *
     * @param llamada Llamada de emergencia de la cual se listan las notas.
     */
    private static void mostrarNotas(LlamadaEmergencia llamada) {
        System.out.println("\nNotas de la llamada " + llamada.getCodigo() + " (Ordenadas desde la más reciente):");
        System.out.println("FECHA Y HORA | OPERADOR | NOTA");
        ArrayList<NotaSeguimiento> notas = llamada.getNotas();

        if (notas == null || notas.isEmpty()) {
            System.out.println("No hay notas de seguimiento registradas para esta llamada.");
            return;
        }
        for (int i = notas.size() - 1; i >= 0; i--) {
            NotaSeguimiento nota = notas.get(i);
            String nombreOperador = "Sin operador";
            if (nota.getOperador() != null) {
                nombreOperador = nota.getOperador().getNombres() + " " + nota.getOperador().getApellidos();
            }
            System.out.println(nota.getFechaHora() + " | " + nombreOperador + " | " + nota.getTexto());
        }
    }
    /**
     * Pide seleccionar un operador y escribir el texto para guardar una nueva nota en la llamada.
     *
     * @param sc Scanner para leer las entradas del usuario.
     * @param sistema Controlador que contiene la lista de operadores.
     * @param llamada Llamada de emergencia a la que se agrega la nota.
     */
    private static void agregarNota(Scanner sc, SistemaECU911 sistema, LlamadaEmergencia llamada) {
        ArrayList<Operador> operadores = sistema.getOperadores();

        if (operadores == null || operadores.isEmpty()) {
            System.out.println("No hay operadores registrados para asignar la nota.");
            return;
        }
        System.out.println("\n[Notas de seguimiento > Añadir nota]");
        System.out.println("Seleccione el operador que registra la nota:");

        for (int i = 0; i < operadores.size(); i++) {
            Operador ope = operadores.get(i);
            System.out.println("[" + (i + 1) + "] " + ope.getNombres() + " " + ope.getApellidos());
        }
        int numeroOperador = ConsolaUtil.validarOpcionMenu(sc, "Seleccione el número de operador: ", 1, operadores.size());
        Operador operadorSeleccionado = operadores.get(numeroOperador - 1);
        String textoNota = ConsolaUtil.validarTexto("> Ingrese el texto de la nota:\n", sc);
        NotaSeguimiento nuevaNota = new NotaSeguimiento(operadorSeleccionado, textoNota);
        llamada.agregarNota(nuevaNota);

        System.out.println("GUARDANDO NOTA...");
        System.out.println("¡Éxito! La nota ha sido guardada en la llamada " + llamada.getCodigo() + ":");
        System.out.println("-> Registro: " + nuevaNota.getFechaHora());
        System.out.println("Operador: " + operadorSeleccionado.getNombres() + " " + operadorSeleccionado.getApellidos());
    }
    /**
     * Pausa la consola hasta que el usuario presione ENTER.
     *
     * @param sc Scanner para leer la entrada.
     * @param mensaje Mensaje que se muestra antes de pausar.
     */
    private static void esperarEnter(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        sc.nextLine();
    }
}
