/**
 * Clase para probar el funcionamiento del Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class EnfrentamientoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());
    
    /**
     * Verificar que la clase Enfrentamientos almacene y recupere los datos 
     * 
     */
    @Test
    public void datosCompletosYEstadoPendienteCorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);
        Juez juez = new Juez ("marcos", "Agudelo", "mc@gmail.com", "7315674", "456");

        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        torneo.registrarJuez(juez);
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.of(2023,10,31,14,0), equipo1, equipo2);
        enfrentamiento.registrarJuez(juez);
        torneo.registrarEnfrentamiento(enfrentamiento);

        // Recuperación y verificación de datos
        assertEquals(lugar,enfrentamiento.getLugar());
        assertEquals(LocalDateTime.of(2023,10,31,14,0), enfrentamiento.getFechaYHora());
        assertEquals(equipo1,enfrentamiento.getEquipoLocal());
        assertEquals(equipo2,enfrentamiento.getEquipoVisitante());
        assertEquals(EstadoEnfrentamiento.PENDIENTE,enfrentamiento.getEstado());
        assertEquals(true, enfrentamiento.getJueces().contains(juez));
        LOG.info("Fin de prueba datos completos...");
    }

        /**
     * Verifica que no se pueda poner de estado En juego, si la fecha y hora no coincide con la actual
     * 
     */
    @Test
    public void EstadoPendienteIncorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);
        
        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.of(2023,11,24,14,0), equipo1, equipo2);

        // Recuperación y verificación de datos
        assertThrows(Throwable.class , ()-> torneo.registrarEnfrentamiento(enfrentamiento));
        LOG.info("Fin de prueba datos completos...");
    }

            /**
     * Verifica que se pueda poner de estado En juego, si la fecha y hora coincide con la fecha y hora del partido
     * 
     */
        @Test
    public void EstadoEnJuegoCorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);

        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.now(), equipo1, equipo2);
        enfrentamiento.SetCambiarEstado(EstadoEnfrentamiento.ENJUEGO);
        // Recuperación y verificación de datos
        assertEquals(EstadoEnfrentamiento.ENJUEGO, enfrentamiento.getEstado());
        LOG.info("Fin de prueba datos completos...");
    }

    /**
     * Verifica que no se pueda poner de estado En juego, si la fecha y hora no coincide con la fecha y hora del partido
     * 
     */
        @Test
    public void EstadoEnJuegoIncorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);

        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.of(2023,10,31,14,0), equipo1, equipo2);
        // Recuperación y verificación de datos
        assertThrows(Throwable.class , ()-> enfrentamiento.SetCambiarEstado(EstadoEnfrentamiento.ENJUEGO));
        LOG.info("Fin de prueba datos completos...");
    }
        /**
     * Verificar que los metodos RegistrarResultadoLocal y RegistrarResultadoVisitante funcionen de manera correcta y que al ser registrados cambien el estado del juego a FINALIZADO
     * 
     */
    @Test
    public void ResultadosCorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);

        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.now().plusDays(1), equipo1, equipo2);
        enfrentamiento.registrarResultadoLocal((byte)5);
        enfrentamiento.registrarResultadoVisitante((byte)0);

        // Recuperación y verificación de datos
        assertEquals((byte)5,enfrentamiento.getResultadoLocal());
        assertEquals((byte)0,enfrentamiento.getResultadoVisitante());
        assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamiento.getEstado());
        LOG.info("Fin de prueba datos completos...");
    }

    /**
     * Verificar que no se pueda cambiar el estado del juego a FINALIZADO, sin que se hayan registrado los puntos obtenidos por cada equipo
     * 
     */
    @Test
    public void EstadoFinalizadoIncorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);

        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.now().plusDays(5), equipo1, equipo2);

        // Recuperación y verificación de datos
        assertThrows(Throwable.class , ()-> enfrentamiento.SetCambiarEstado(EstadoEnfrentamiento.FINALIZADO));
        LOG.info("Fin de prueba datos completos...");
    }

    /**
     * Verifica que se pueda poner de estado Aplazado, si no se ha registrado un resultado
     * 
     */
        @Test
    public void EstadoAplazadoCorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);

        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.now(), equipo1, equipo2);
        enfrentamiento.SetCambiarEstado(EstadoEnfrentamiento.APLAZADO);
        // Recuperación y verificación de datos
        assertEquals(EstadoEnfrentamiento.APLAZADO, enfrentamiento.getEstado());
        LOG.info("Fin de prueba datos completos...");
    }

    /**
     * Verifica que no se pueda poner de estado Aplazado, si se ha registrado el resultado del partido
     * 
     */
        @Test
    public void EstadoAplazadoIncorrecto() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");

        var equipo1 = new Equipo("Uniquindio", representante1,TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2,TipoEquipo.MASCULINO);

        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento (lugar, LocalDateTime.now().plusDays(2), equipo1, equipo2);

        enfrentamiento.registrarResultadoLocal((byte)5);
        enfrentamiento.registrarResultadoVisitante((byte)0);
        // Recuperación y verificación de datos
        assertThrows(Throwable.class , ()-> enfrentamiento.SetCambiarEstado(EstadoEnfrentamiento.APLAZADO));
        LOG.info("Fin de prueba datos completos...");
    }
}