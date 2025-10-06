// Importando librerías necesarias
import java.util.Scanner; // Para la entrada de datos
import java.util.Date; // Para obtener la fecha completa
import java.text.SimpleDateFormat; // Para darle formato a la fecha
import java.time.LocalTime; // Para obtener la hora actual de forma moderna

// ==============================================================================================
// Clase main...........................................................................PRINCIPAL
public class Main {
    // Metodo principal
    public static void main(String[] args) {
        // Variables para la entrada, el objeto elegido y la hora actual
        Scanner sc = new Scanner(System.in);
        // PILAR #3: POLIMORFISMO (Declaramos la variable del tipo Padre para guardar objetos hijos)
        Padre objetoElegido; 
        // Preguntar el nombre
        System.out.print("Por favor, introduce tu nombre: ");
        String nombre = sc.nextLine();
        // Determinar la parte del día automáticamente con la hora del sistema
        int horaActual = LocalTime.now().getHour(); // Obtiene la hora actual en formato 24h (0-23)
        // instanciar objetos (La instanciación depende de la hora actual).........................
        switch (horaActual) {
            case 6: case 7: case 8: case 9: case 10: case 11: // 6:00 a 11:59 (Mañana)
                objetoElegido = new Manana();
                break;
            case 12: case 13: case 14: case 15: case 16: case 17: case 18: // 12:00 a 18:59 (Tarde)
                objetoElegido = new Tarde();
                break;
            default: // Cualquier otra hora (19:00 a 5:59) (Noche)
                objetoElegido = new Noche();
                break;
        }
        // Asignar el nombre al objeto instanciado antes de saludar
        objetoElegido.nombreUsuario = nombre;
        
        // Invocar funciones (Siempre se llama al método 'saludo') ..............................
        objetoElegido.saludo();
        sc.close(); // Cerramos el Scanner
    } // end main
} // end class 0

// ==============================================================================================
// Clase Padre..............................................................................PADRE
// Esta clase solo se encarga de contener todas las variables de datos y el contrato.
class Padre {
    
    // PILAR #2: ENCAPSULAMIENTO (Variables protegidas y centralizadas)
    // Tipo Date (Objeto: Almacena la fecha y hora actuales del sistema)
    protected Date fechaActual = new Date();
    // Tipo String (Para el nombre del usuario)
    protected String nombreUsuario;
    // Tipo String (Datos de fecha y hora ya formateados y listos para imprimir)
    protected String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
    protected String horaFormateada = new SimpleDateFormat("HH:mm").format(fechaActual);
    // Tipo double (Número decimal)
    protected double distancia = 1.5; // por el momento es absoluta (fija)
    // Tipo String (saludos)
    protected String formatoManana = "¡Buenos días %s!, Son las %s, Hoy es %s. Es un gran día, ¡aprovéchalo!"; 
    protected String formatoTarde = "¡Buenas tardes %s!, Son las %s, Hoy es %s. Estás a %.1f km de distancia del próximo restaurante";
    protected String formatoTardes= "¡Buenas tardes %s!, Son las %s, Hoy es %s. No hay ningun restaurante cercano.";
    protected String formatoNoche = "¡Buenas noches %s!, Son las %s, Hoy es %s. Ya casi es hora de dormir, debes descansar."; 
    // PILAR #4: ABSTRACCIÓN (Define el contrato o interfaz que las hijas deben usar)
    public void saludo() {} // El método por defecto (será reescrito por las clases hijas)
} // end class PADRE

// ==============================================================================================
// Clase 1.................................................................................MANANA
// PILAR #1: HERENCIA (Extiende la clase Padre)
class Manana extends Padre { 
    @Override // PILAR 3: POLIMORFISMO (Sobrescribimos el método 'saludo' para esta clase)
    public void saludo() {
        System.out.println(String.format(formatoManana, nombreUsuario, horaFormateada, fechaFormateada));
    }
} // end class 1

// ==============================================================================================
// Clase 2..................................................................................TARDE
// PILAR #1: HERENCIA (Extiende la clase Padre)
class Tarde extends Padre { 
    @Override // PILAR 3: POLIMORFISMO (Sobrescribimos el método 'saludo' para esta clase)
    public void saludo() {
        // Ejemplo donde podrías usar un if/else para otra condición si fuera necesario, 
        // pero se mantiene el original para respetar el formato.
        if (distancia > 1.0) {
           System.out.println(String.format(formatoTarde, nombreUsuario, horaFormateada, fechaFormateada, distancia));
        } else {
            System.out.println(String.format(formatoTardes, nombreUsuario, horaFormateada, fechaFormateada, distancia));
        }
    }
} // end class 2

// ==============================================================================================
// Clase 3..................................................................................NOCHE
// PILAR #1: HERENCIA (Extiende la clase Padre)
class Noche extends Padre { 
    @Override // PILAR 3: POLIMORFISMO (Sobrescribimos el método 'saludo' para esta clase)
    public void saludo() {
        System.out.println(String.format(formatoNoche, nombreUsuario, horaFormateada, fechaFormateada));
    }
} // end class 3