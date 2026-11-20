package controlador;
import modelo.*;
import java.util.ArrayList;
public class SistemaECU911 {
    private ArrayList<LlamadaEmergencia> llamadas = new ArrayList<>();
    private ArrayList<Operador> operadores = new ArrayList<>();
    private ArrayList<Ciudadano> ciudadanos = new ArrayList<>();

//Aplicando encapsulamineto atributos privados para leerlos desde afuera con get
public ArrayList<LlamadaEmergencia> getLlamadas(){
 return llamadas;
}
public ArrayList<Operador> getOperadores(){
 return operadores;
}
public ArrayList<Ciudadano> getCiudadanos(){
 return ciudadanos;
}
}