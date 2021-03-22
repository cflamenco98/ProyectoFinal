package proyectofinal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AccionesCRUD {
    File archivo;

    public AccionesCRUD() {
    }
    
    public String GuardarLista(String Nombre)
    {        
        String respuesta = "";
        
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        try {        
        File file = new File("Listas.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        bw.write("1|");
        bw.write(Nombre+"|");
        bw.newLine();
        respuesta = "¡Lista creada con exito!";
        
        } catch (IOException e) {
            respuesta = "¡ERROR!";
        } finally {
            try {
                if (bw != null)
                  bw.close();
                if (fw != null)
                  fw.close();
        } catch (IOException ex) {
        }
    }
        return respuesta;    
    }
    
}
