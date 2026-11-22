package modelo;//Clase hija de Persona
public class Operador extends Persona{
    private String codigo;
    private String turno;
    private String email;
    private int llamadasAtendidas;
    private static int contadorOperadores = 1;
    //Constructor de la clase asignando el codigo con "OP-" que aumenta con cada operador e inicialliza las llamadas en 0
    public Operador(String nombres,String apellidos, String turno,String email) {
        super(nombres, apellidos);//Llama al constructor de la clase padre para asignar sus atributos
        this.codigo = "OP-" + contadorOperadores;
        contadorOperadores++;
        this.turno = turno; 
        this.email = email;
        this.llamadasAtendidas = 0;
    }
    //Getters y Setters de sus atributos
    public String getCodigo() {
        return codigo;
    }

    public String getTurno() {
        return turno;
    }

    public String getEmail() {
        return email;
    }

    public int getLlamadasAtendidas() {
        return llamadasAtendidas;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLlamadasatendidas(int llamadasatendidas) {
        this.llamadasAtendidas = llamadasatendidas;
    }
    //Incrementa 1 llamada al atributo llamadasAtendidas cada que se lo invoca
    public void incrementarLlamadas(){
        this.llamadasAtendidas++;
    }
    
    
}
