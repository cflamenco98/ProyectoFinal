package proyectofinal;

//Modelo del objeto lista
public class Lista {    
    public int listaId;
    public String Nombre;  

    public Lista(){
    }

    public Lista(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public Lista(int listaId, String Nombre) {
        this.listaId = listaId;
        this.Nombre = Nombre;
    }
    
    public int getListaId() {
        return listaId;
    }

    public void setListaId(int listaId) {
        this.listaId = listaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
