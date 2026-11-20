package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    
    

    public String getCodigo() {
        return codigo;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getEstado() {
        return estado;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public Operador getOperador() {
        return operador;
    }

    public ArrayList<NotaSeguimiento> getNotas() {
        return notas;
    }
    
    public void agregarNota(NotaSeguimiento nota){
        notas.add(nota);
    }
    
    public void actualizarEstado(){
        if(this.estado.equals("Registrada")){
            this.estado = "En atención";
        }else  if (this.estado.equals("En atención")){
            this.estado =  "Atendida";
        }
    }
    
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
