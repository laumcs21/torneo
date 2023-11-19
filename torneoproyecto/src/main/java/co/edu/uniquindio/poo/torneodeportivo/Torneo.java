/**
 * Clase que agrupa los datos de un Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;
import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;
public class Torneo {
    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    private final Collection<Equipo> equipos;
    public final Collection<Juez> jueces;
    private final CaracterTorneo caracterTorneo;
    private final Collection<Enfrentamiento> enfrentamientos;


    public Torneo(String nombre, LocalDate fechaInicio,
            LocalDate fechaInicioInscripciones,
            LocalDate fechaCierreInscripciones, byte numeroParticipantes,
            byte limiteEdad, int valorInscripcion,TipoTorneo tipoTorneo, CaracterTorneo caracterTorneo) {
        
        ASSERTION.assertion( nombre != null , "El nombre es requerido");
        ASSERTION.assertion( caracterTorneo != null , "El caracter del torneo es requerido");
        
        
        
        ASSERTION.assertion( numeroParticipantes >= 0, "El número de participantes no puede ser negativo");
        ASSERTION.assertion( limiteEdad >= 0,"El limite de edad no puede ser negativo");
        ASSERTION.assertion( valorInscripcion >= 0,"El valor de la inscripción no puede ser negativo");
        
        
        this.nombre = nombre;
        
        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones); 
        setFechaInicio(fechaInicio);
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.equipos = new LinkedList<>();
        this.jueces = new LinkedList<>();
        this.caracterTorneo = caracterTorneo;
        this.enfrentamientos = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public CaracterTorneo getCaracterTorneo(){
        return this.caracterTorneo;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        ASSERTION.assertion( fechaInicio != null , "La fecha de inicio es requerida");
        ASSERTION.assertion( ( fechaInicioInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones) ) &&
                ( fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones) ),"La fecha de inicio no es válida" );
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        ASSERTION.assertion( fechaInicioInscripciones != null , "La fecha de inicio de inscripciones es requerida");
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }


    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        ASSERTION.assertion( fechaCierreInscripciones != null , "La fecha de cierre es requerida");
        ASSERTION.assertion( fechaCierreInscripciones.isAfter(fechaInicioInscripciones),"La fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones" );
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }
    
    /**
     * Permite registrar un equipo en el torneo
     * @param equipo Equipo a ser registrado
     * @throws Se genera un error si ya existe un equipo registrado con el mismo nombre, o en caso de que las inscripciones del torneo no esten abiertas.
     */
    public void registrarEquipo(Equipo equipo) {
        validarEquipoExiste(equipo); 
        validarCaracter(equipo);
        validarInscripciopnesAbiertas(); 

        equipos.add(equipo);
    }

    /**
     * Valida que el tipo de equipo a registrar es acorde al caracter del torneo
     */
    private void validarCaracter(Equipo equipo) {
        ASSERTION.assertion( caracterTorneo.esValido(equipo),"El equipo a inscribir no es aceptable para el tipo de torneo");
    }
    

    /**
     * Valida que las inscripciones del torneo esten abiertas, en caso de no estarlo genera un assertion error.
     */
    private void validarInscripciopnesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now()) && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion( inscripcionAbierta,"Las inscripciones no están abiertas");
    }

    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de haberlo genera un assertion error.
     */
    private void validarEquipoExiste(Equipo equipo) {
        boolean existeEquipo = buscarEquipoPorNombre(equipo.getNombre()).isPresent();
        ASSERTION.assertion( !existeEquipo,"El equipo ya esta registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los equipos registrados.
     * @return Collection<Equipo> no modificable de los equipos registrados en el torneo.
     */
    public Collection<Equipo> getEquipos() {
        return Collections.unmodifiableCollection(equipos);
    }
    
    /**
     * Permite buscar un equipo por su nomnbre entre los equipos registrados en el torneo
     * @param nombre Nombre del equipo que se está buscando
     * @return Un Optional<Equipo> con el equipo cuyo nombre sea igual al nombre buscado, o un Optional vacio en caso de no encontrar un equipo con nombre igual al dado.
     */
    public Optional<Equipo> buscarEquipoPorNombre(String nombre){
        Predicate<Equipo> condicion = equipo->equipo.getNombre().equals(nombre);
        return equipos.stream().filter(condicion).findAny();
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     *  
     * @param nombre Nombre del equipo en que se desea registrar el jugador
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(String nombre, Jugador jugador) {
        var equipo = buscarEquipoPorNombre(nombre);
        equipo.ifPresent( (e)->registrarJugador(e, jugador) );
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     * 
     * @param equipo Equipo en el que se desea registrar el jugador.
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Equipo equipo, Jugador jugador) {
        ASSERTION.assertion( !LocalDate.now().isAfter(fechaCierreInscripciones) , "No se pueden registrar jugadores después del a fecha de cierre de inscripciones");
        validarLimiteEdadJugador(jugador); 
        validarTipo(equipo, jugador);
        validarJugadorExiste(jugador);
        equipo.registrarJugador(jugador);
    }

    private void validarTipo(Equipo equipo, Jugador jugador) {
        ASSERTION.assertion( equipo.getTipoEquipo().esValido(jugador),"El jugador a inscribir no es aceptable en el tipo de equipo");
    }
    /**
     * Permite buscar un jugador basado en su nombre y apellido en todos los equipos registrados en el torneo.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador encontrado o un optional vacío en caso de no haber encontrado un jugador con el nombre y apellido del jugador buscado.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        return equipos.stream()
            .map(equipo->equipo.buscarJugador(jugador))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }

    /**
     * Valida que no exista se puedan registrar jugadores que al momento del inicio del torneo excedan el limite de edad.
     */
    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadAlInicioTorneo = jugador.calcularEdad(fechaInicio);
        ASSERTION.assertion( limiteEdad == 0 || limiteEdad >= edadAlInicioTorneo , "No se pueden registrar jugadores que excedan el limite de edad del torneo"); 
    }

        /**
     * Permite registrar un juez en el torneo
     * @param juez juez a ser registrado
     * @throws Se genera un error si ya existe un juez registrado con el mismo numero de licencia.
     */
    public void registrarJuez(Juez juez) {
        validarJuezExiste(juez);
        jueces.add(juez);
    }

    /**
     * Valida que no exista ya un juez registrado con el mismo numero de licencia.
     */
    private void validarJuezExiste(Juez juez) {
        boolean existeJuez = buscarJuez(juez).isPresent();
        ASSERTION.assertion( !existeJuez,"El equipo ya esta registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los jueces registrados.
     * @return Collection<juez> no modificable de los jueces registrados en el torneo.
     */
    public Collection<Juez> getJueces() {
        return Collections.unmodifiableCollection(jueces);
    }
    
    /**
     * Permite buscar un juez por su nombre y apenllido entre los jueces registrados en el torneo
     * @param juez Nombre y Apellido del juez que se está buscando
     * @return Un Optional<juez> con el juez cuyo nombre y apellido sea igual al del juez que se está buscando, o un Optional vacio en caso de no encontrar un juez con nombre y apellido igual al dado.
     */
    public Optional<Juez> buscarJuez(Juez juez){
        Predicate<Juez> nombreIgual = j->j.getNombre().equals(juez.getNombre());
        Predicate<Juez> apellidoIgual = j->j.getApellido().equals(juez.getApellido());
        return jueces.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

    // permite registrar los enfrentamientos al torneo
    public void registrarEnfrentamiento(Enfrentamiento enfrentamiento) {
        validarEstado(enfrentamiento); 
        validarJuezEnfrentamiento(enfrentamiento);
        validarEquiposEnfrentamiento(enfrentamiento.getEquipoLocal());
        validarEquiposEnfrentamiento(enfrentamiento.getEquipoVisitante());
        enfrentamientos.add(enfrentamiento);
}

    //Devuelve un listado de enfrentamientos
    public Collection<Enfrentamiento> getEnfrentamientos() {
        return Collections.unmodifiableCollection(enfrentamientos);
    }


    //valida que los jueces registrados en el enfrentamiento, sean parte del listado de jueces del torneo
    public void validarJuezEnfrentamiento(Enfrentamiento enfrentamiento) {
        boolean todosJuecesValidos = enfrentamiento.jueces.stream().allMatch(juez ->
                jueces.stream()
                        .anyMatch(j -> j.getNombre().equals(juez.getNombre()) && j.getApellido().equals(juez.getApellido()))
        );

        ASSERTION.assertion(todosJuecesValidos, "El listado de jueces no contiene jueces válidos");
    }


    private void validarEquiposEnfrentamiento(Equipo equipo) {
        boolean existeEquipo = buscarEquipoPorNombre(equipo.getNombre()).isPresent();
        ASSERTION.assertion( existeEquipo,"El equipo no está registrado");
    }

        /**
     * Valida que el tipo de estado del enfrentamiento a registrar es acorde a la fecha y hora
     */

    private void validarEstado(Enfrentamiento enfrentamiento) {
        ASSERTION.assertion( enfrentamiento.getEstado().esValido(enfrentamiento.getFechaYHora(), enfrentamiento.getResultadoLocal(), enfrentamiento.getResultadoVisitante()),"El estado del enfrentamiento a inscribir no es aceptable");
    }
    public void registrarPartido(String equipoLocal, String equipoVisitante, String resultado) {
        Equipo local = buscarEquipoPorNombre(equipoLocal).orElse(null);
        Equipo visitante = buscarEquipoPorNombre(equipoVisitante).orElse(null);

        if (local != null && visitante != null) {
            if (resultado.equals("victoria")) {
                local.registrarResultado("victoria");
                visitante.registrarResultado("derrota");
            } else if (resultado.equals("empate")) {
                local.registrarResultado("empate");
                visitante.registrarResultado("empate");
            } else if (resultado.equals("derrota")) {
                local.registrarResultado("derrota");
                visitante.registrarResultado("victoria");
            }
        }
    }
}
