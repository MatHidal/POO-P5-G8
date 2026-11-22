package modelo;
/**
 * Representa a un operador del sistema ECU 911 que atiende llamadas de emergencia.
 * Hereda los datos básicos de {@link Persona}.
 */
public class Operador extends Persona{
    private String codigo;
    private String turno;
    private String email;
    private int llamadasAtendidas;
    private static int contadorOperadores = 1;
    /**
     * Crea un nuevo operador con código autogenerado con prefijo {@code "OP-"}
     * e inicializa el contador de llamadas atendidas en {@code 0}.
     *
     * @param nombres   Nombres del operador.
     * @param apellidos Apellidos del operador.
     * @param turno     Turno asignado al operador.
     * @param email     Correo electrónico del operador.
     */
    public Operador(String nombres,String apellidos, String turno,String email) {
        super(nombres, apellidos);//Llama al constructor de la clase padre para asignar sus atributos
        this.codigo = "OP-" + contadorOperadores;
        contadorOperadores++;
        this.turno = turno; 
        this.email = email;
        this.llamadasAtendidas = 0;
    }
    /**
     * Obtiene el código único del operador.
     *
     * @return Código del operador.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Obtiene el turno asignado al operador.
     *
     * @return Turno del operador.
     */
    public String getTurno() {
        return turno;
    }
    /**
     * Obtiene el correo electrónico del operador.
     *
     * @return Correo electrónico.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Obtiene la cantidad de llamadas atendidas por el operador.
     *
     * @return Número de llamadas atendidas.
     */
    public int getLlamadasAtendidas() {
        return llamadasAtendidas;
    }
    /**
     * Actualiza el turno asignado al operador.
     *
     * @param turno Nuevo turno del operador.
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }
    /**
     * Actualiza el correo electrónico del operador.
     *
     * @param email Nuevo correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Establece la cantidad de llamadas atendidas por el operador.
     *
     * @param llamadasatendidas Nueva cantidad de llamadas atendidas.
     */
    public void setLlamadasatendidas(int llamadasatendidas) {
        this.llamadasAtendidas = llamadasatendidas;
    }
    /**
     * Incrementa en uno el contador de llamadas atendidas por el operador.
     */
    public void incrementarLlamadas(){
        this.llamadasAtendidas++;
    }
    
    
}
