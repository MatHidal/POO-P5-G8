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
     * Muestra el encabezado de registro, solicita y valida el código del operador (cancelando
     * si no existe), pide y valida la cédula del ciudadano en bucle hasta que tenga 10 dígitos,
     * registra al ciudadano si no existe o reutiliza sus datos, solicita tipo, ciudad, barrio y
     * detalle de la emergencia, registra la llamada e imprime el resumen con su código generado.
     */
    private void registrarLlamada(){
        System.out.println("\nSISTEMA DE REGISTRO DE LLAMADAS ECU 911 (Version 1.0)");
        System.out.println("[Menu Principal > 1. Llamadas > 1.2. Registrar llamada]");
        
        ArrayList<Operador> operadores = sistema.getOperadores();
        System.out.println("\nSeleccione el operador que atiende la llamada:");
        for(Operador op: operadores){
            System.out.println("[" + op.getCodigo() + "] " + op.getNombres() + " " + op.getApellidos());
        }
                System.out.println("Ingrese codigo del operador: ");
        String codOp = scanner.nextLine();
        Operador op = sistema.buscarOperadorPorCodigo(codOp);
        
        if(op == null){
            System.out.println("Operador no encontrado. Registro cancelado.");
            return;
        }
        
        System.out.println("\nDATOS DEL CIUDADANO");
        System.out.println("3. Cedula de identidad (10 digitos): ");
        String cedula = scanner.nextLine().trim();
        
        while(!esCedulaValida(cedula)){
            System.out.println("¡Error! La cedula debe contener exactamente 10 digitos numericos. Intente nuevamente.");
            System.out.println("3. Cedula de identidad (10 digitos):");
            cedula = scanner.nextLine().trim();
        }
        Ciudadano ciu = sistema.buscarCiudadano(cedula);
        if(ciu == null){
            System.out.println("1. Nombres: ");
            String nombres = scanner.nextLine();
            System.out.print("2. Apellidos: ");
            String apellidos = scanner.nextLine();
            ciu = new Ciudadano(cedula,nombres,apellidos);
            sistema.getCiudadanos().add(ciu);
        } else {
            System.out.println("Ciudadano ya registrado en el sistema: " + ciu.getNombres() + " " + ciu.getApellidos() + ".");
            System.out.println("Se utilizaran los datos existentes. Continuando con el registro de la emergencia...");
        }
        System.out.println("\nDATOS DE LA EMERGENCIA");
        System.out.println("4. Tipo de emergencia:");
        System.out.println("[1] Energia");
        System.out.println("[2] Civil");
        System.out.println("[3] Transito");
        System.out.println("[4] Medio ambiente");
        System.out.println("[5] Otros");
        System.out.print("Seleccione una opcion (1-5): ");
        String opTipo = scanner.nextLine().trim();
        
        String tipo;
        switch (opTipo) {
            case "1":
                tipo = "Energia";
                break;
            case "2":
                tipo = "Civil";
                break;
            case "3":
                tipo = "Transito";
                break;
            case "4":
                tipo = "Medio ambiente";
                break;
            case "5":
                tipo = "Otros";
                break;
            default:
                tipo = opTipo;
                break;
        }
        System.out.println("5. Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("6. Barrio: ");
        String direccion = scanner.nextLine();
        System.out.print("7. Detalle: ");
        String descripcion = scanner.nextLine();
        
        System.out.println("\nPROCESANDO REGISTRO...");
        LlamadaEmergencia nueva = sistema.registrarLlamada(ciu, op, tipo, ciudad, direccion, descripcion);
        
        System.out.println("¡Exito! La llamada ha sido registrada correctamente:");
        System.out.println("-> Codigo: " + nueva.getCodigo());
        System.out.println("-> Fecha y hora: " + nueva.getFechaHora());
        System.out.println("-> Ciudadano: " + ciu.getNombres() + " " + ciu.getApellidos() + " (C.I. " + ciu.getCedula() + ")");
        System.out.println("-> Emergencia: " + nueva.getTipoEmergencia() + " - " + nueva.getCiudad() + ", " + nueva.getBarrio());
        System.out.println("-> Operador: " + op.getNombres() + " " + op.getApellidos());
        System.out.println("-> Estado: " + nueva.getEstado());
        
        System.out.println("\nPresione ENTER para regresar al Menu de Llamadas...");
        scanner.nextLine();
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