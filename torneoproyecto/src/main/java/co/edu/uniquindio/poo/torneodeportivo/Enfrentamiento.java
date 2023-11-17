package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

public class Enfrentamiento {

    public Lugar lugar;
    public LocalDateTime fechaYHora;
    public Equipo equipoLocal;
    public Equipo equipoVisitante;
    public Collection<Juez> jueces;
    public Byte resultadoLocal;
    public Byte resultadoVisitante;
    public EstadoEnfrentamiento estado;

    public Enfrentamiento (Lugar lugar, LocalDateTime fechaYHora, Equipo equipoLocal, Equipo equipoVisitante){

        assert lugar != null : "El lugar del enfrentamiento es requerido";
        assert fechaYHora != null : "La fecha y hora del enfrentamiento es requerida";
        assert equipoLocal != null : "El Equipo local que se enfrentará es requerido";
        assert equipoVisitante != null : "El Equipo visitante que se enfrentará es requerido";


        this.lugar = lugar;
        this.fechaYHora = fechaYHora;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.jueces = new LinkedList<>();
        this.resultadoLocal = null;
        this.resultadoVisitante = null;
        this.estado = EstadoEnfrentamiento.PENDIENTE;

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

}
