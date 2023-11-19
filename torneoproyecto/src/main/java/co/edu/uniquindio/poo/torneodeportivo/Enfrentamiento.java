package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class Enfrentamiento {
    
      
    
     
    public Torneo torneo;
    public Lugar lugar;
    public LocalDateTime fechaYHora;
    public Equipo equipoLocal;
    public Equipo equipoVisitante;
    public Collection<Juez> jueces;
    public Byte resultadoLocal;
    public Byte resultadoVisitante;
    public EstadoEnfrentamiento estado;
    public List<Enfrentamiento> enfrentamientos;
    public Juez juez;
    


    public Enfrentamiento (Lugar lugar, LocalDateTime fechaYHora, Equipo equipoLocal, Equipo equipoVisitante){

        assert lugar != null : "El lugar del enfrentamiento es requerido";
        assert fechaYHora != null : "La fecha y hora del enfrentamiento es requerida";
        assert equipoLocal != null : "El Equipo local que se enfrentará es requerido";
        assert equipoVisitante != null : "El Equipo visitante que se enfrentará es requerido";

    
        this.enfrentamientos = new ArrayList<>();
        this.lugar = lugar;
        this.fechaYHora = fechaYHora;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.jueces = new LinkedList<>();
        this.resultadoLocal = null;
        this.resultadoVisitante = null;
        this.estado = EstadoEnfrentamiento.PENDIENTE;
        

    }
public Enfrentamiento(Lugar lugar, LocalDateTime fechaYHora, Equipo equipoLocal, Equipo equipoVisitante, Juez juez) {
        assert lugar != null : "El lugar del enfrentamiento es requerido";
        assert fechaYHora != null : "La fecha y hora del enfrentamiento es requerida";
        assert equipoLocal != null : "El Equipo local que se enfrentará es requerido";
        assert equipoVisitante != null : "El Equipo visitante que se enfrentará es requerido";

        assert juez != null: "El juez no es válido";

        this.enfrentamientos = new ArrayList<>();
        this.lugar = lugar;
        this.fechaYHora = fechaYHora;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.jueces = new LinkedList<>();
        this.resultadoLocal = null;
        this.resultadoVisitante = null;
        this.estado = EstadoEnfrentamiento.PENDIENTE;
        this.jueces.add(juez);
    }
    public Lugar getLugar(){
        return this.lugar;
    }
    
    public LocalDateTime getFechaYHora(){
        return this.fechaYHora;
    }

    public Equipo getEquipoLocal(){
        return this.equipoLocal;
    }

    public Equipo getEquipoVisitante(){
        return this.equipoVisitante;
    }
    
    public Byte getResultadoLocal(){
        return this.resultadoLocal;
    }

    public Byte getResultadoVisitante(){
        return this.resultadoVisitante;
    }

    public EstadoEnfrentamiento getEstado(){
        return this.estado;
    }
   

        // permite registrar jueces en el enfrentamiento
        public void registrarJuez(Juez juez) {
        jueces.add(juez);
    }


    /**
     * Permite obtener una copia no modificable de la lista de los jueces registrados.
     * @return Collection<juez> no modificable de los jueces registrados en el torneo.
     */
    public Collection<Juez> getJueces() {
        return Collections.unmodifiableCollection(jueces);
    }


    public void SetCambiarEstado(EstadoEnfrentamiento estado){
        assert estado.esValido(fechaYHora, resultadoLocal, resultadoVisitante);
        this.estado = estado;
    }

    public Byte registrarResultadoLocal (Byte resultado){
        assert resultado != null : "El resultado es requerido";
        this.resultadoLocal = resultado;

        if (resultadoLocal != null && resultadoVisitante != null && fechaYHora.isAfter(LocalDateTime.now())) {
            this.estado = EstadoEnfrentamiento.FINALIZADO;
        }

        return this.resultadoLocal;
    }
    

    public Byte registrarResultadoVisitante (Byte resultado){
        assert resultado != null : "El resultado es requerido";
        this.resultadoVisitante = resultado;

        if (resultadoLocal != null && resultadoVisitante != null && fechaYHora.isAfter(LocalDateTime.now())) {
            this.estado = EstadoEnfrentamiento.FINALIZADO;
        }
        
        return this.resultadoVisitante;
    }
    class ListaEnfrentamientos {
        public List<Enfrentamiento> enfrentamientos;
        public Collection<Juez> jueces;
        public ListaEnfrentamientos() {
        this.enfrentamientos = new ArrayList<>();
        this.jueces = new LinkedList<>();
        }
        public Collection<Juez> getJueces() {
        return Collections.unmodifiableCollection(jueces);
    }
        public void agregarEnfrentamiento(Enfrentamiento enfrentamiento) {
            enfrentamientos.add(enfrentamiento);
        }
        public void añadirJuez(Juez juez){
            jueces.add(juez);
        }
}
}
/* public class Enfrentamiento {
    private Lugar lugar;
    private LocalDate fechaEnfrentamiento;
    private LocalDateTime horaEnfrentamiento;
    private Equipo equipo1;
    private Equipo equipo2;
    private Collection<Juez> juecez;
    private EstadoPartido estadoPartido;
    private Resultado resultado;


    public Enfrentamiento(Lugar lugar, LocalDate fechaEnfrentamiento,
                          LocalDateTime horaEnfrentamiento, Equipo equipo1, Equipo equipo2) {
        ASSERTION.assertion(lugar!=null, "el lugar no es válido");
        ASSERTION.assertion(fechaEnfrentamiento!=null, "La fetch de enfrentamiento  no es válida");
        ASSERTION.assertion(horaEnfrentamiento!=null, "La hora de enfrentamiento no es válida");
        ASSERTION.assertion(equipo1!=null, "el equipo no es válido");
        ASSERTION.assertion(equipo2!=null, "el equipo no es valido");
        this.lugar = lugar;
        this.fechaEnfrentamiento = fechaEnfrentamiento;
        this.horaEnfrentamiento = horaEnfrentamiento;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.juecez = new LinkedList<>();
        this.estadoPartido = EstadoPartido.PENDIENTE;
    }

    public Enfrentamiento(Lugar lugar, LocalDate fechaEnfrentamiento,
                          LocalDateTime horaEnfrentamiento, Equipo equipo1, Equipo equipo2, Juez juez) {
        ASSERTION.assertion(lugar != null, "El lugar no es válido");
        ASSERTION.assertion(fechaEnfrentamiento != null, "La fecha de enfrentamiento no es válida");
        ASSERTION.assertion(horaEnfrentamiento != null, "La hora de enfrentamiento no es válida");
        ASSERTION.assertion(equipo1 != null, "El equipo 1 no es válido");
        ASSERTION.assertion(equipo2 != null, "El equipo 2 no es válido");
        ASSERTION.assertion(juez != null, "El juez no es válido");

        this.lugar = lugar;
        this.fechaEnfrentamiento = fechaEnfrentamiento;
        this.horaEnfrentamiento = horaEnfrentamiento;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.juecez = new LinkedList<>();
        this.juecez.add(juez);
        this.estadoPartido = EstadoPartido.PENDIENTE;
    } */