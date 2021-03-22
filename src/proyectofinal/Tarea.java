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
    public String Descripcion;
    public boolean EsImportante;
    public boolean Completa;
    public Date FechaVencimiento;
    public Date FechaDia;
    public String ListaId; 
    
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
    
     public void imprimirTareas() {
        System.out.println("=========================================");
        System.out.println("Descripcion: " + Descripcion);
        System.out.println("Fecha dia: " + FechaDia);
        System.out.println("Fecha vencimiento: " + FechaVencimiento);
        System.out.println("Es importante: " + EsImportante);
        System.out.println("Esta completa: " + Completa);
        System.out.println("=========================================");
    }
}
