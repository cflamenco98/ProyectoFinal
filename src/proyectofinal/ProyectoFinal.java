package proyectofinal;

import java.util.Date;

/**
 *
 * @author cflamenco y Heyzi Irias c:
 */
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lista L1 = new Lista();
      L1.Nombre = "Personal";
        
      Tarea T1 = new Tarea();
      Tarea T2 = new Tarea();
      T1.Descripcion = "Ir a cortar el Pelo";
      T1.EsImportante = true;
      T1.Completa = false;
      T1.FechaDia = new Date();
      T1.FechaVencimiento = new Date(2021,03,18);
      
      T2.Descripcion = "Ir al super mall";
      T2.EsImportante = false;
      T2.Completa = false;
      T2.FechaDia = new Date();
      T2.FechaVencimiento = new Date(2021,03,18);
      
      /*
      System.out.println(T1.FechaDia);
      System.out.println(T1.FechaVencimiento);
      */
      
      L1.Tareas.add(T1);
      L1.Tareas.add(T2); 
      
      L1.mostrarTareas();
      
      System.out.println("Tareas importantes: " + L1.TareasImportantes());
      L1.TareasImportantes();
      System.out.println("Tareas completas: " + L1.TareasCompletas());
    
    } 
}
    
    

