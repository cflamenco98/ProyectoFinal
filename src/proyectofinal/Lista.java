package proyectofinal;
import java.util.ArrayList;
/**
 *
 * @author  cFlamenco y Heyzi Irias
 */
public class Lista {
    public String Nombre;
    public ArrayList<Tarea> Tareas = new ArrayList<Tarea>();
    
    public Lista(){
    }
    
    public void mostrarTareas() {
        for(int i = 0; i<Tareas.size(); i++) {
        Tareas.get(i).imprimirTareas();
        }
    }
    
    public void mostrarTareasCompletas() {
        for(int i = 0; i<Tareas.size(); i++) {
        Tareas.get(i).imprimirTareas();
        }
    }
    
    public void mostrarTareasImportantes() {
        for(int i = 0; i<Tareas.size(); i++) {
        if(Tareas.get(i).EsImportante == true)
            Tareas.get(i).imprimirTareas();
        }
    }
        
    public int TareasImportantes() {
        int TImportantes = 0;
        for(int i = 0; i<Tareas.size(); i++) {
        if(Tareas.get(i).EsImportante == true) 
            TImportantes++;
        }
        return TImportantes;
     }
    
    public int TareasCompletas() {
        int TareasCompletas = 0;
        for(int i = 0; i<Tareas.size(); i++) {
        if(Tareas.get(i).Completa == true) 
            TareasCompletas++;
        }
        return TareasCompletas ;
     }
}
