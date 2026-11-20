package vista;
import java.util.Scanner;
public class  ConsolaUtil {
    public static String validarTexto(String pedir_dato, Scanner sc){
        System.out.println(pedir_dato);
        String texto= sc.nextLine();
        while (texto==null || texto.trim().isEmpty()){
            System.out.println("Requisito necesario, por favor complete con la información pertinente");
            texto=sc.nextLine();   
        }
        return texto.trim();
        
    }
}
