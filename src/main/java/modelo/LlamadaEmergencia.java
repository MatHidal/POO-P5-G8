package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Representa una llamada de emergencia registrada en el sistema ECU 911,
 * junto con su estado, fecha y hora, ciudadano, operador y notas de seguimiento.
 */
public class LlamadaEmergencia {
    private String codigo;
    private String fechaHora;
    private String tipoEmergencia;
    private String ciudad;
    private String barrio;
    private String detalle;
    private String estado;
    private Ciudadano ciudadano;
    private Operador operador;
    private ArrayList<NotaSeguimiento> notas = new ArrayList<>();
    private static int contadorLlamadas = 1;
    /**
     * Crea una nueva llamada de emergencia con estado inicial {@code "Registrada"},
     * código autogenerado con prefijo {@code "LL-"} y la fecha y hora actuales.
     *
     * @param ciudadano      Ciudadano que reporta la emergencia.
     * @param operador       Operador que atiende la llamada.
     * @param tipoEmergencia Tipo de emergencia reportada.
     * @param ciudad         Ciudad donde ocurre la emergencia.
     * @param barrio         Barrio o sector de la emergencia.
     * @param detalle        Descripción detallada del suceso.
     */
    public LlamadaEmergencia(Ciudadano ciudadano,Operador operador, String tipoEmergencia, String ciudad, String barrio, String detalle){
        this.codigo = "LL-" + contadorLlamadas;
        contadorLlamadas++;
        this.ciudadano = ciudadano;
        this.operador = operador;
        this.tipoEmergencia = tipoEmergencia;
        this.ciudad = ciudad;
        this.barrio = barrio;
        this.detalle = detalle;
        this.estado = "Registrada";
        
 
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
        this.fechaHora = LocalDateTime.now().format(formato);
    }
    
    
    /**
     * Obtiene el código único de la llamada.
     *
     * @return Código de la llamada.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Obtiene la fecha y hora de registro de la llamada.
     *
     * @return Fecha y hora formateada.
     */
    public String getFechaHora() {
        return fechaHora;
    }
    /**
     * Obtiene el tipo de emergencia de la llamada.
     *
     * @return Tipo de emergencia.
     */
    public String getTipoEmergencia() {
        return tipoEmergencia;
    }
    /**
     * Obtiene la ciudad donde se reportó la emergencia.
     *
     * @return Nombre de la ciudad.
     */
    public String getCiudad() {
        return ciudad;
    }
    /**
     * Obtiene el barrio o sector de la emergencia.
     *
     * @return Barrio o sector.
     */
    public String getBarrio() {
        return barrio;
    }
    /**
     * Obtiene el detalle de la emergencia.
     *
     * @return Detalle de la llamada.
     */
    public String getDetalle() {
        return detalle;
    }
    /**
     * Obtiene el estado actual de la llamada.
     *
     * @return Estado de la llamada.
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Obtiene el ciudadano asociado a la llamada.
     *
     * @return Objeto {@link Ciudadano}.
     */
    public Ciudadano getCiudadano() {
        return ciudadano;
    }
    /**
     * Obtiene el operador asignado a la llamada.
     *
     * @return Objeto {@link Operador}.
     */
    public Operador getOperador() {
        return operador;
    }
    /**
     * Obtiene la lista de notas de seguimiento asociadas a la llamada.
     *
     * @return Lista de objetos {@link NotaSeguimiento}.
     */
    public ArrayList<NotaSeguimiento> getNotas() {
        return notas;
    }
    /**
     * Agrega una nota de seguimiento a la llamada.
     *
     * @param nota Nota de seguimiento a incorporar.
     */
    public void agregarNota(NotaSeguimiento nota){
        notas.add(nota);
    }
    /**
     * Avanza el estado de la llamada de {@code "Registrada"} a {@code "En atención"},
     * o de {@code "En atención"} a {@code "Atendida"}. Si ya está {@code "Atendida"},
     * no realiza cambios.
     */
    public void actualizarEstado(){
        if(this.estado.equals("Registrada")){
            this.estado = "En atención";
        }else  if (this.estado.equals("En atención")){
            this.estado =  "Atendida";
        }
    }
    /**
     * Verifica si la llamada puede anularse, lo cual ocurre si su estado es
     * {@code "Registrada"} y fue registrada en la fecha actual.
     *
     * @return {@code true} si cumple las condiciones para anularse; {@code false} en caso contrario.
     */
    public boolean anular(){
        DateTimeFormatter formatoSoloFecha  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaHoy = LocalDateTime.now().format(formatoSoloFecha);
        String fechaRegistro = this.fechaHora.split(" ")[0];
        
        if (this.estado.equals("Registrada")  && fechaRegistro.equals(fechaHoy)){
            return true;
        }
        return false;
    }
    
    
    
}
