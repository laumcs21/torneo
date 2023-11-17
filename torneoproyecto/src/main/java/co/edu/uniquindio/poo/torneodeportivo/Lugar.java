package co.edu.uniquindio.poo.torneodeportivo;

public class Lugar {
    
    public String nombre;
    public String ubicacion;

    public Lugar (String nombre, String ubicacion){
        assert nombre != null : "El nombre es requerido";
        assert ubicacion !=null : "La ubicación del lugar es requerida";

        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getUbicacion(){
        return this.ubicacion;
    }
}
