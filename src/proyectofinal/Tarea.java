/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;
import java.util.Date;
/**
 *
 * @author cFlamenco y Heyzi Irias
 */
public class Tarea {    
    ////////////////////////////////////////////////////////////////////
    
    public int tareaId;
    public String Descripcion;
    public boolean EsImportante;
    public boolean Completa;
    public Date FechaVencimiento;
    public Date FechaDia;
    public String ListaId;
    
    ////////////////////////////////////////////////////////////////////
    
    public Tarea(){
    }

    public Tarea(String Descripcion, boolean EsImportante, boolean Completa,
            Date FechaVencimiento, Date FechaDia, String ListaId) {
        this.Descripcion = Descripcion;
        this.EsImportante = EsImportante;
        this.Completa = Completa;
        this.FechaVencimiento = FechaVencimiento;
        this.ListaId = ListaId;
    }
    
    ////////////////////////////////////////////////////////////////////
    
     public Tarea imprimirTareas() {
         Tarea t = new Tarea(Descripcion,EsImportante,Completa,FechaVencimiento,FechaDia,ListaId);
         return t;
    }
     
     ////////////////////////////////////////////////////////////////////

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

    public Date getFechaDia() {
        return FechaDia;
    }

    public void setFechaDia(Date FechaDia) {
        this.FechaDia = FechaDia;
    }

    public String getListaId() {
        return ListaId;
    }

    public void setListaId(String ListaId) {
        this.ListaId = ListaId;
    }
    
    ////////////////////////////////////////////////////////////////////
     
}
