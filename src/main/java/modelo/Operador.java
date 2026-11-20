package modelo;
public class Operador extends Persona{
    private String codigo;
    private String turno;
    private String email;
    private int llamadasAtendidas;
    private static int contadorOperadores = 1;

    public Operador(String nombres,String apellidos, String turno,String email) {
        super(nombres, apellidos);
        this.codigo = "OP-" + contadorOperadores;
        contadorOperadores++;
        this.turno = turno; 
        this.email = email;
        this.llamadasAtendidas = 0;
    }

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
    
    public void incrementarLlamadas(){
        this.llamadasAtendidas++;
    }
    
    
}
