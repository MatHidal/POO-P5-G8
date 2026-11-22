package modelo;
/**
 * Representa a un ciudadano registrado en el sistema ECU 911.
 * Hereda los datos básicos de {@link Persona} y añade su número de cédula.
 */
public class Ciudadano extends Persona {
    private String cedula;
    /**
     * Crea una nueva instancia de {@code Ciudadano}.
     *
     * @param cedula    Número de cédula del ciudadano.
     * @param nombres   Nombres del ciudadano.
     * @param apellidos Apellidos del ciudadano.
     */
    public Ciudadano(String cedula, String nombres, String apellidos) {
        super(nombres, apellidos);
        this.cedula = cedula;
        
    /**
     * Obtiene el número de cédula del ciudadano.
     *
     * @return Cédula del ciudadano.
     */
    }

    public String getCedula() {
        return cedula;
    }
    
   
}
