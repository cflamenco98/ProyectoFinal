package proyectofinal;
import java.util.Date;

//Modelo del objeto tarea
public class Tarea {
    public int tareaId;
    public String Descripcion;
    public boolean EsImportante;
    public boolean Completa;
    public Date FechaVencimiento;
    public int ListaId;    
    
    public Tarea(){
    }

    public Tarea(String Descripcion, boolean EsImportante, boolean Completa, Date FechaVencimiento, int ListaId) {
        this.Descripcion = Descripcion;
        this.EsImportante = EsImportante;
        this.Completa = Completa;
        this.FechaVencimiento = FechaVencimiento;
        this.ListaId = ListaId;
    }    

    public int getTareaId() {
        return tareaId;
    }

    public void setTareaId(int tareaId) {
        this.tareaId = tareaId;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public boolean isEsImportante() {
        return EsImportante;
    }

    public void setEsImportante(boolean EsImportante) {
        this.EsImportante = EsImportante;
    }

    public boolean isCompleta() {
        return Completa;
    }

    public void setCompleta(boolean Completa) {
        this.Completa = Completa;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public int getListaId() {
        return ListaId;
    }

    public void setListaId(int ListaId) {
        this.ListaId = ListaId;
    }
     
}
