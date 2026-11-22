package controlador;
import modelo.*;
import java.util.ArrayList;
import vista.VistaLlamada;
public class SistemaECU911 {
    private ArrayList<LlamadaEmergencia> llamadas = new ArrayList<>();
    private ArrayList<Operador> operadores = new ArrayList<>();
    private ArrayList<Ciudadano> ciudadanos = new ArrayList<>();
    //Constructor que llama a cargarDatosIniciales
    public SistemaECU911() {
        cargarDatosIniciales();
    }
//Aplicando encapsulamineto atributos privados para leerlos desde afuera con get
public ArrayList<LlamadaEmergencia> getLlamadas(){
 return llamadas;
}
public ArrayList<Operador> getOperadores(){
 return operadores;
}
public ArrayList<Ciudadano> getCiudadanos(){
 return ciudadanos;
}
//En la ArrayList de llamadas busca la que coincida con el codigo ingresado para devolver dicha llamada, caso contrario devuelve nulo
public LlamadaEmergencia buscarLlamadaPorCodigo(String Codigo){
    for(LlamadaEmergencia ll: llamadas){
        if(ll.getCodigo().equals(Codigo)){
            return ll;
        }
    }
    return null;
}
/*Metodo que recibe todos los datos necesarios para crear un objeto LlamadaEmergencia y añadirlo a llamadas, además 
incrimenta 1 llamada  atendida al operador y retorna el objeto creado*/
public LlamadaEmergencia registrarLlamada(Ciudadano ciudadano, Operador operador, String tipoEmergencia, String ciudad, String direccion, String descripcion){
    LlamadaEmergencia nueva = new LlamadaEmergencia(ciudadano, operador, tipoEmergencia, ciudad, direccion, descripcion);
    llamadas.add(nueva);
    operador.incrementarLlamadas();
    return nueva;
}
/**
 * Precarga los datos iniciales requeridos al arrancar la aplicación:
 * 1 operador, 1 ciudadano, 1 llamada de emergencia y 1 nota de seguimiento.
 */
public void cargarDatosIniciales() {
    Operador op1 = new Operador("Laura", "Mendoza", "Matutino", "l.mendoza@ecu911.gob.ec");
    operadores.add(op1);
    Ciudadano ciu1 = new Ciudadano("0934567890", "Luis", "Andrade Mora");
    ciudadanos.add(ciu1);
    LlamadaEmergencia ll1 = new LlamadaEmergencia(
                ciu1,
                op1,
                "Medio ambiente",
                "Guayaquil",
                "Alborada",
                "Quema de maleza reportada cerca del parque."
        );
        llamadas.add(ll1);
        op1.incrementarLlamadas();
        NotaSeguimiento nota1 = new NotaSeguimiento(
                op1,
                "Se coordina despacho con el Cuerpo de Bomberos de Guayaquil."
        );
        ll1.agregarNota(nota1);
    }
    /**
     * Registra un nuevo operador en el sistema.
     *
     * @param nombres   Nombres del operador.
     * @param apellidos Apellidos del operador.
     * @param turno     Turno asignado (Matutino, Vespertino, Nocturno).
     * @param email     Correo electrónico (puede ser vacío).
     * @return El objeto Operador creado con su código autogenerado.
     */
    public Operador registrarOperador(String nombres, String apellidos, String turno, String email) {
        Operador nuevo = new Operador(nombres, apellidos, turno, email);
        operadores.add(nuevo);
        return nuevo;
    }
    /**
     * Busca un operador por su código único.
     *
     * @param codigo Código del operador.
     * @return El objeto Operador si existe, o null si no se encuentra.
     */
    public Operador buscarOperadorPorCodigo(String codigo) {
        for (Operador op : operadores) {
            if (op.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return op;
            }
        }
        return null;
    }
    /**
     * Cuenta cuántas llamadas tiene registradas un operador específico.
     *
     * @param operador El operador a consultar.
     * @return Cantidad de llamadas atendidas por dicho operador.
     */
    public int contarLlamadasDeOperador(Operador operador) {
        int contador = 0;
        for (LlamadaEmergencia ll : llamadas) {
            if (ll.getOperador().getCodigo().equalsIgnoreCase(operador.getCodigo())) {
                contador++;
            }
        }
        return contador;
    }
    /**
     * Elimina un operador del sistema por su código siempre y cuando no tenga
     * llamadas asociadas.
     *
     * @param codigo Código del operador que se desea eliminar.
     * @return true si fue eliminado exitosamente; false si no existe o tiene
     *         llamadas registradas.
     */
    public boolean eliminarOperador(String codigo) {
        Operador operador = buscarOperadorPorCodigo(codigo);
        if (operador == null || contarLlamadasDeOperador(operador) > 0) {
            return false;
        }
        return operadores.remove(operador);
    }
////En la ArrayList ciudadanos busca el ciudadano que tenga la cédula ingresada y lo retorna,caso contrario retorna nulo
    public Ciudadano buscarCiudadano(String cedula) {
        for (Ciudadano c : ciudadanos) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }
    public boolean actualizarEstadoLlamada(String codigo) {
        for (int i = 0; i < llamadas.size(); i++) {
            LlamadaEmergencia ll = llamadas.get(i);
            if (ll.getCodigo().equals(codigo) && !ll.getEstado().equals("Atendida")) {
                ll.actualizarEstado();
                return true;
            }
        }
        return false;
    }

}
