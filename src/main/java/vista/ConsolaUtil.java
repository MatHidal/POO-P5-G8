package vista;
import java.util.Scanner;
/**
 * Clase con métodos de utilidad para validar entradas por consola.
 */
public class  ConsolaUtil {
    /**
     * Solicita un dato y valida que no esté vacío o con espacios en blanco.
     *
     * @param pedir_dato Mensaje que se muestra al usuario.
     * @param sc Scanner para leer la entrada.
     * @return Texto ingresado sin espacios al inicio ni al final.
     */
    public static String validarTexto(String pedir_dato, Scanner sc){
        System.out.println(pedir_dato);
        String texto= sc.nextLine();
        while (texto==null || texto.trim().isEmpty()){
            System.out.println("Requisito necesario, por favor complete con la información pertinente");
            System.out.println(pedir_dato);
            texto=sc.nextLine();   
        }
        return texto.trim();
    }
    /**
     * Solicita y valida que una cédula tenga exactamente 10 dígitos numéricos.
     *
     * @param sc Scanner para leer la entrada.
     * @param pedir_dato Mensaje que se muestra al usuario.
     * @return Cédula válida de 10 dígitos.
     */
   public static String validarCedula(Scanner sc, String pedir_dato) {
    System.out.print(pedir_dato);
    String cedula = sc.nextLine().trim();
    while (cedula.length() != 10 || !cedula.matches("\\d+")) {
        System.out.println("Ingrese nuevamente, se requieren 10 dígitos numéricos.");
        System.out.print(pedir_dato);
        cedula = sc.nextLine().trim();
    }
    return cedula;
}
/**
     * Solicita una opción numérica de menú dentro de un rango permitido.
     *
     * @param sc Scanner para leer la entrada.
     * @param pedir_dato Mensaje que se muestra al usuario.
     * @param min Valor mínimo permitido.
     * @param max Valor máximo permitido.
     * @return Número de opción válido seleccionado por el usuario.
     */
    public static int validarOpcionMenu(Scanner sc, String pedir_dato, int min, int max) {
        int opcion = 0;
        boolean valida = false;
        while (!valida) {
            System.out.print(pedir_dato);
            String entrada = sc.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
                if (opcion >= min && opcion <= max) {
                    valida = true;
                } 
                else {
                    System.out.println("Opción inválida, ingrese un número entre " + min + " y " + max);
                }
            } 
            catch (NumberFormatException error) {
                System.out.println("Opción inválida, ingrese nuevamente un número.");
            }
        }

        return opcion;
    }
    /**
     * Solicita una confirmación de sí (S) o no (N) al usuario.
     *
     * @param pedir_dato Pregunta de confirmación que se muestra.
     * @param sc Scanner para leer la entrada.
     * @return true si el usuario ingresa S, false si ingresa N.
     */
    public static boolean leerConfirmacion(String pedir_dato, Scanner sc){
        System.out.println(pedir_dato);
        String resp= sc.nextLine().trim().toUpperCase();
        while (!resp.equals("S") && !resp.equals("N")) {
            System.out.println("Respuesta no válida. Ingrese S para confirmar o N para cancelar.");
            System.out.print(pedir_dato);
            resp = sc.nextLine().trim().toUpperCase();
        }
        return resp.equals("S");
    }
/**
     * Muestra un mensaje y pausa la ejecución hasta que el usuario presione ENTER.
     *
     * @param sc Scanner para leer la entrada.
     * @param mensaje Mensaje de pausa que se muestra en consola.
     */
    public static void esperarEnter(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        sc.nextLine();
    }
}
