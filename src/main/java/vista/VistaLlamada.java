package vista;
import controlador.SistemaECU911;
import java.util.Scanner;
import java.util.ArrayList;
import modelo.*;
public class VistaLlamada {
  private SistemaECU911 sistema;
  private Scanner scanner;

public VistaLlamada(SistemaECU911 sistema, Scanner scanner) { //Constructor - SG
        this.sistema = sistema;
        this.scanner = scanner;
    }
/*Pide la cedula ingresada, en caso de cumplir con el formato de un numero de cedula y no existir pide los datos del ciudadano para Registrarlo. 
Posteriormente imprime los operadores disponibles para que ingrese el codigo del operador que desea que lo atienda; en donde si el codigo no existe
cancela el registro caso contrario se continua con el registro pidiendo los datos necesarios para registrar la llamada e imprimer el codigo  con el 
que se la registró*/
    private void registrarLlamada(){
        System.out.println("\n--- REGISTRAR LLAMADA ---");
        System.out.println("Ingrese cedula del ciudadano: ");
        String cedula = scanner.nextLine().trim();
        
        if(!esCedulaValida(cedula)){
            System.out.println("Error: Cedula invalida. Debe tener 10 digitos numericos. Registro cancelado");
            return;
        }
        Ciudadano ciu = sistema.buscarCiudadano(cedula);
        if(ciu == null){
            System.out.println("Ciudadano no registrado. Ingrese los datos:");
            System.out.println("Nombres: ");
            String nombres = scanner.nextLine();
            System.out.print("Apellidos: ");
            String apellidos = scanner.nextLine();
            ciu = new Ciudadano(cedula,nombres,apellidos);
            sistema.getCiudadanos().add(ciu);
        }
        
        ArrayList<Operador> operadores = sistema.getOperadores();
        System.out.println("Operadores disponibles: ");
        for(Operador op: operadores){
            System.out.println(op.getCodigo()+" | " + op.getNombres() + " " + op.getApellidos());
        }
        System.out.println("Ingrese codigo del operador: ");
        String codOp = scanner.nextLine();
        Operador op = sistema.buscarOperadorPorCodigo(codOp);
        
        if(op == null){
            System.out.println("Operador no encontrado. Registro cancelado.");
            return;
        }
        System.out.println("Tipo de emergencia");
        String tipo = scanner.nextLine();
        System.out.println("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Direccion o sector: ");
        String direccion = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        
        LlamadaEmergencia nueva = sistema.registrarLlamada(ciu, op, tipo, ciudad, direccion, descripcion);
        System.out.println("Llamada registrada con codigo: "+ nueva.getCodigo());
    }

    /*Muestra en pantalla un mensaje para posteriormente obtener el ArrayList de objetos LlamadaEmergencia, recorre la lista en donde imprime
    cada llamada que está disponible para anular; si no está disponible ninguna se lo da a conocer al usuario y sale del método;
    caso contrario le pide al usuario el código de la llamada que desea anular, en donde el método busca la llamada y si la llamada no existe o
    no es posible anularla se lo da a conocer al usuario; caso contrario le pide que confirme anulándola y, de ser el caso, la elimina del sistema*/
    private void anularLlamada(){
        System.out.println("\nSISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Version 1.0)");
        System.out.println("[Menu Principal > 1. Llamadas > 1.4. Anular llamada]");
        
        ArrayList<LlamadaEmergencia> lista = sistema.getLlamadas();
        int disponibles = 0;
        
        System.out.println("Llamadas que se pueden anular (Registradas hoy):");
        for(LlamadaEmergencia ll: lista){
            if(ll.anular()){
                System.out.println(ll.getCodigo() + " | " + ll.getFechaHora() + " | " + ll.getCiudadano().getNombres() + " " + ll.getCiudadano().getApellidos());
                disponibles++;
            }
        }
        if(disponibles == 0){
            System.out.println("No existan llamadas que cumplan los requisistos para anular");
            System.out.println("\nPresione ENTER para regresar al Menu de llamadas...");
            scanner.nextLine();
            return;
        }
        System.out.println("> Ingrese el codigo de la llamada a anular (ej. LL-1): ");
        String codigo = scanner.nextLine();
        
        LlamadaEmergencia ll = sistema.buscarLlamadaPorCodigo(codigo);
        if( ll == null || ll.anular() == false){
            System.out.println("Error: Codigo no encontrado o la llamada no se puede anular");
        }else{
            System.out.println("Advertencia: Esta accion eliminara la llamada " + ll.getCodigo() + " del sistema");
            System.out.println("> Confirma la anulacion? (S/N): ");
            String respuesta = scanner.nextLine();
            
            if (respuesta.equalsIgnoreCase("S")) {
                sistema.getLlamadas().remove(ll);
                System.out.println("Llamada anulada correctamente");
            }else{
                System.out.println("Anulacion cancelada");
            }
        }
        System.out.println("\nPresione ENTER para regresar al Menu de Llamadas...");
        scanner.nextLine();
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
                    registrarLlamada();
                    break;
                case 3:
                    actualizarEstado();
                    break;
                case 4:
                    anularLlamada();
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
    /*Verifica que la cédula ingresada tenga una longitud de 10, tambien que cada carácter no sea menor a '0' ni mayor a '9' si cumple eso retorna 
    un booleano true, de no ser el caso retorna false*/
    private boolean esCedulaValida(String cedula){
        if(cedula.length() != 10){
            return false;
        }
        for (int i = 0; i < cedula.length(); i++) {
            char c = cedula.charAt(i);
            if(c < '0' || c > '9' ){
                return false;
            }
        }
        return true;
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