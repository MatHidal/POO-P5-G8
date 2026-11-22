package modelo;//Clase padre de Operador y Ciudadano
public class Persona {
    private String nombres;
    private String apellidos;
    //Constructor
    public Persona(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    //Getters y Setters de sus atributos
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
