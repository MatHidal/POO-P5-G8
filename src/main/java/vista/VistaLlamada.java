package vista;
import controlador.SistemaECU911;
import java.util.Scanner;
import java.util.ArrayList;
import modelo.*;
/**
 * Vista de consola encargada de gestionar las operaciones relacionadas con las
 * llamadas de emergencia del sistema ECU 911.
 */
public class VistaLlamada {
  private SistemaECU911 sistema;
  private Scanner scanner;
  /**
     * Crea la vista de llamadas con el controlador del sistema y el lector de consola.
     *
     * @param sistema Controlador principal del sistema ECU 911.
     * @param scanner Scanner para leer las entradas del usuario.
     */
public VistaLlamada(SistemaECU911 sistema, Scanner scanner) { 
        this.sistema = sistema;
        this.scanner = scanner;
    }
    /**
     * Solicita la cédula del ciudadano y valida su formato; si es inválida, cancela el registro.
     * Si el ciudadano no está registrado, solicita sus datos y lo agrega al sistema. Luego muestra
     * los operadores disponibles, valida el código ingresado (cancelando si no existe), solicita los
     * detalles de la emergencia, registra la llamada e imprime su código generado.
     */
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

    /**
     * Lista las llamadas de emergencia disponibles para anular (registradas hoy en estado registrada).
     * Si no hay disponibles, informa al usuario y retorna; de lo contrario, solicita el código de la
     * llamada, verifica que exista y pueda anularse, pide confirmación y, si se confirma, la elimina
     * del sistema.
     */
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
    /**
     * Muestra el menu de opciones para administrar las llamadas de emergencia.
     */
    public void mostrarMenu() {
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
/**
     * Muestra en consola todas las llamadas registradas, ordenadas desde la mas reciente.
     */
    private void listarLlamadas() { 
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
    /**
     * Verifica que la cédula tenga exactamente 10 caracteres y que cada uno sea un dígito
     * entre {@code '0'} y {@code '9'}.
     *
     * @param cedula Cadena con el número de cédula a validar.
     * @return {@code true} si cumple con el formato; {@code false} en caso contrario.
     */
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
    /**
     * Lista las llamadas registradas, solicita un codigo y pide al sistema actualizar su estado.
     */
    private void actualizarEstado() { 
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