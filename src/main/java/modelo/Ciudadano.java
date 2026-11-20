package modelo;
public class Ciudadano extends Persona {
    private String cedula;

    public Ciudadano(String cedula, String nombres, String apellidos) {
        super(nombres, apellidos);
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }
    
   
}
