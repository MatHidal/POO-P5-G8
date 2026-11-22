package modelo;
/**
 * Clase base que representa a una persona con nombres y apellidos
 * dentro del sistema ECU 911.
 */
public class Persona {
    private String nombres;
    private String apellidos;
    /**
     * Crea una nueva instancia de {@code Persona}.
     *
     * @param nombres   Nombres de la persona.
     * @param apellidos Apellidos de la persona.
     */
    public Persona(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    /**
     * Obtiene los nombres de la persona.
     *
     * @return Nombres de la persona.
     */
    public String getNombres() {
        return nombres;
    }
    /**
     * Actualiza los nombres de la persona.
     *
     * @param nombres Nuevos nombres.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    /**
     * Obtiene los apellidos de la persona.
     *
     * @return Apellidos de la persona.
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Actualiza los apellidos de la persona.
     *
     * @param apellidos Nuevos apellidos.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
