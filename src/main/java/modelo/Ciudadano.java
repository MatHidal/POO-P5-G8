package modelo;
public class Ciudadano extends Persona {
    private String cedula;
    //Constructor de la clase Ciudadano
    public Ciudadano(String cedula, String nombres, String apellidos) {
        super(nombres, apellidos);
        this.cedula = cedula;
    }
    //Getter cedula
    public String getCedula() {
        return cedula;
    }
    
   
}
