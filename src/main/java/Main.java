import controlador.SistemaECU911;
import java.util.Scanner;
import vista.VistaPrincipal;

/**
 * Clase principal de arranque para el Sistema de Registro de Llamadas ECU 911.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SistemaECU911 sistema = new SistemaECU911();
        sistema.cargarDatosIniciales();

        Scanner scanner = new Scanner(System.in);
        VistaPrincipal vistaPrincipal = new VistaPrincipal(sistema, scanner);
        vistaPrincipal.iniciar();

        scanner.close();
    }
}
