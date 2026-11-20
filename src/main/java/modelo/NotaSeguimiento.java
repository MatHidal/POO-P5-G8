package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class NotaSeguimiento {
    private String fechaHora;
    private String texto;
    private String codigo;
    private Operador operador;
    private static int contadorNotas = 1;

    public NotaSeguimiento(Operador operador, String texto ){
        this.codigo = "NT - "+contadorNotas;
        contadorNotas++;
        this.texto = texto;
        this.operador = operador;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.fechaHora = LocalDateTime.now().format(formato);
    }
   
    public String getFechaHora() {
        return fechaHora;
    }

    public String getTexto() {
        return texto;
    }

    public Operador getOperador() {
        return operador;
    }

    public String getCodigo() {
        return codigo;
    }
    
    
    
    
    
}
