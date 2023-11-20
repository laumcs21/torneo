/**
 * Registro que agrupa los datos de un Equipo
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;
public class Equipo {

    private String nombre;
    private Persona representante;
    private Collection<Jugador> jugadores;
    private TipoEquipo tipoEquipo;
    private int victorias;
    private int empates;
    private int derrotas;
    public Equipo(){
    }

    
    public Equipo(String nombre, Persona representante, TipoEquipo tipoEquipo) {
        
        this.nombre = nombre;
        this.representante = representante;
        this.tipoEquipo = tipoEquipo;
        this.jugadores = new LinkedList<>(); // Inicializa la colección de jugadores
        this.victorias = 0;
        this.empates = 0;
        this.derrotas = 0;
    }


    // Método para registrar los resultados de un partido para devolver un numero de victorias, empates y derrotas
    public void registrarResultado(String resultado) {
        if (resultado.equals("victoria")) {
            this.victorias++;
        } else if (resultado.equals("empate")) {
            this.empates++;
        } else if (resultado.equals("derrota")) {
            this.derrotas++;
        }
    }

    public int getVictorias(){
        return this.victorias;
    }

    public int getEmpates() {
        return this.empates;
    }

    public int getDerrotas() {
        return this.derrotas;
    }

    public String getNombre() {
        return this.nombre;
    }
    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void incrementarVictorias() {
        this.victorias++;
    }
    public void incrementarEmpates() {
        this.empates++;
    }
    public void incrementarDerrotas() {
        this.derrotas++;
    }
    public Persona getRepresentante(){
        return this.representante;
    }
    /**
     * Permite registrar un jugador en un equipo siempre y cuando no exista ya un jugador registrado en el equipo con el mismo nombre y apellido
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Jugador jugador) {
        validarJugadorExiste(jugador);
        validarTipo(jugador);
        jugadores.add(jugador);
    }
// coleccion de jugadores
    public Collection<Jugador> getJugadores(){
        return Collections.unmodifiableCollection(jugadores);
    }

    /**
     * Permimte buscar un jugador en el equipo basado en su nombre y apellido.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador que coincida con el nombre y apellido del jugador buscado, 
     * o Optinal vacío en caso de no encontrar un jugador en el equipo con dicho nombre y apellido.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        Predicate<Jugador> nombreIgual = j->j.getNombre().equals(jugador.getNombre());
        Predicate<Jugador> apellidoIgual = j->j.getApellido().equals(jugador.getApellido());
        return jugadores.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }
// valida el tipo de jugador en el tipo de equipo
    private void validarTipo(Jugador jugador) {
        ASSERTION.assertion( tipoEquipo.esValido(jugador),"El jugador a inscribir no es aceptable en el tipo de equipo");
    }

// obtiene la lista de enfrentamientos del equipo por su nombre
    public List<Enfrentamiento> obtenerEnfrentamientosEquipo(Torneo torneo) {
        List<Enfrentamiento> enfrentamientosDelEquipo = new ArrayList<>();
    
        for (Enfrentamiento enfrentamiento : torneo.getEnfrentamientos()) {
            if (enfrentamiento.contieneEquipo(this.nombre)){
                enfrentamientosDelEquipo.add(enfrentamiento);
            }
        }
    
        return enfrentamientosDelEquipo;
    }
    
}