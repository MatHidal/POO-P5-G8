package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Representa una nota de seguimiento registrada sobre una llamada de emergencia.
 */
public class NotaSeguimiento {
    private String fechaHora;
    private String texto;
    private String codigo;
    private Operador operador;
    private static int contadorNotas = 1;
    /**
     * Crea una nueva nota de seguimiento con código autogenerado con prefijo {@code "NT-"}
     * y registra la fecha y hora actuales.
     *
     * @param operador Operador que redacta la nota.
     * @param texto    Contenido de la nota de seguimiento.
     */
    public NotaSeguimiento(Operador operador, String texto ){
        this.codigo = "NT-"+contadorNotas;
        contadorNotas++;
        this.texto = texto;
        this.operador = operador;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.fechaHora = LocalDateTime.now().format(formato);
    }
    /**
     * Obtiene la fecha y hora de creación de la nota.
     *
     * @return Fecha y hora formateada.
     */
    public String getFechaHora() {
        return fechaHora;
    }
    /**
     * Obtiene el texto de la nota de seguimiento.
     *
     * @return Texto de la nota.
     */
    public String getTexto() {
        return texto;
    }
    /**
     * Obtiene el operador que registró la nota.
     *
     * @return Objeto {@link Operador}.
     */
    public Operador getOperador() {
        return operador;
    }
    /**
     * Obtiene el código único de la nota.
     *
     * @return Código de la nota.
     */
    public String getCodigo() {
        return codigo;
    }
}
