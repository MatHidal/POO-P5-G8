package vista;
import controlador.SistemaECU911;
import modelo.LlamadaEmergencia;
import modelo.NotaSeguimiento;
import modelo.Operador;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaNotaSeguimiento {
    public static void administrarNotasSeguimiento(Scanner sc, SistemaECU911 sistema) {
        System.out.println("\nSISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Versión 1.0)");
        System.out.println("[Menú Principal > 4. Notas de seguimiento]");
        String codigoLlamada = ConsolaUtil.validarTexto(sc, "> Ingrese el código de la llamada: ").trim();
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
        int numeroOperador = ConsolaUtil.validarOpcionMenu(sc,"Seleccione el número de operador: ",1,operadores.size());
        Operador operadorSeleccionado = operadores.get(numeroOperador - 1);
        String textoNota = ConsolaUtil.validarTexto(sc, "> Ingrese el texto de la nota:\n");
        NotaSeguimiento nuevaNota = new NotaSeguimiento(operadorSeleccionado, textoNota);
        llamada.agregarNota(nuevaNota);
        
        System.out.println("GUARDANDO NOTA...");
        System.out.println("¡Éxito! La nota ha sido guardada en la llamada " + llamada.getCodigo() + ":");
        System.out.println("-> Registro: " + nuevaNota.getFechaHora());
        System.out.println("Operador: " + operadorSeleccionado.getNombres() + " " + operadorSeleccionado.getApellidos());
    }
    private static void esperarEnter(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        sc.nextLine();
    }
}
