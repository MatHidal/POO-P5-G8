package vista;
import java.util.Scanner;
public class  ConsolaUtil {
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
            } else {
                System.out.println("Opción inválida, ingrese un número entre " + min + " y " + max);
            }
        } catch (NumberFormatException error) {
            System.out.println("Opción inválida, ingrese nuevamente un número.");
        }
    }

    return opcion;
}
