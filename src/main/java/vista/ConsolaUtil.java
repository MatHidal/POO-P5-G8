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
    public static int validarCedula(Scanner sc, String pedir_dato){
        System.out.println(pedir_dato);
        int num= sc.nexLine().trim();
        while not (num.lenght()==10 || cedula.matches("\\d+")){
            System.out.printl("Ingrese nuevamente, se requieren 10 digitos");
            System.out.println(pedir_dato);
            num=sc.nextLine().trim();
        }
        return num;
    }
}
