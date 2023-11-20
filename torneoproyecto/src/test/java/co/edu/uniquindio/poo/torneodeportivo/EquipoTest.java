/**
 * Clase para probar el registro de los equipos
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class EquipoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());
    
    /**
     * Verificar que sea posible registrar un equipo en el torneo 
     * 
     */
    @Test
    public void registrarEquipo() {
        LOG.info("Inicio de prueba registrarEquipo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|0\|0\|LOCAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        var equipo = new Equipo("Uniquindio", representante,TipoEquipo.MASCULINO);

        torneo.registrarEquipo(equipo);

        // Recuperación y verificación de datos
        assertTrue(torneo.getEquipos().contains(equipo));
        assertEquals(1, torneo.getEquipos().size());
        LOG.info("Fin de prueba registrarEquipo...");
    }

    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos con nombre repetido
     * 
     */
    @Test
    public void nombreEquipoRepetido() {
        LOG.info("Inicio de prueba nombreEquipoRepetido...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|0\|0\|LOCAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        var equipo = new Equipo("Uniquindio", representante, TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("Uniquindio", representante, TipoEquipo.MASCULINO);
        torneo.registrarEquipo(equipo);

        assertThrows(Throwable.class, ()-> torneo.registrarEquipo(equipo2));
        
        LOG.info("Fin de prueba nombreEquipoRepetido...");
    }

    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos cuando las inscripciones ya cerraron
     * 
     */
    @Test
    public void inscripcionCerrada() {
        LOG.info("Inicio de prueba inscripcionCerrada...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual-1 días\|24\|0\|0\|LOCAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().minusDays(1), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        var equipo = new Equipo("Uniquindio", representante, TipoEquipo.MASCULINO);

        assertThrows(Throwable.class, ()-> torneo.registrarEquipo(equipo));
        
        LOG.info("Fin de prueba inscripcionCerrada...");
    }

    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos cuando las inscripciones aun no han abrierto
     * 
     */
    @Test
    public void inscripcionNoAbierta() {
        LOG.info("Inicio de prueba inscripcionNoAbierta...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual + 1 día\|fechaActual+15 días\|24\|0\|0\|LOCAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().plusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        var equipo = new Equipo("Uniquindio", representante, TipoEquipo.MASCULINO);

        assertThrows(Throwable.class, ()-> torneo.registrarEquipo(equipo));
        
        LOG.info("Fin de prueba inscripcionNoAbierta...");
    }
    @Test
    public void testRegistrarVictoria() {
        Equipo equipo = new Equipo();
        equipo.registrarResultado("victoria");
        assertEquals(1, equipo.getVictorias());
    }

    @Test
    public void testRegistrarEmpate() {
        Equipo equipo = new Equipo();
        equipo.registrarResultado("empate");
        assertEquals(1, equipo.getEmpates());
    }

    @Test
    public void testRegistrarDerrota() {
        Equipo equipo = new Equipo();
        equipo.registrarResultado("derrota");
        assertEquals(1, equipo.getDerrotas());
    }
    @Test
    public void obtenerEnfrentamientosEquipo() {
        LOG.info("Inicio de prueba datos completos...");
    
        // Crear los objetos necesarios
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Marcos", "Gomez", "mg@email.com", "6067479300");
        var juez1 = new Juez("Bryan", "perez", "123", "234", "xd");
        var equipo1 = new Equipo("Uniquindio", representante1, TipoEquipo.MASCULINO);
        var equipo2 = new Equipo("LosMejores", representante2, TipoEquipo.MASCULINO);
    
        torneo.registrarEquipo(equipo1);
        torneo.registrarEquipo(equipo2);
        torneo.registrarJuez(juez1);
    
        Lugar lugar = new Lugar("p1", "carrera 5");
        Enfrentamiento enfrentamiento = new Enfrentamiento(lugar, LocalDateTime.now().plusDays(2), equipo1, equipo2, juez1);
        // registra el enfrentamiento
        enfrentamiento.registrarResultadoLocal((byte) 5);
        enfrentamiento.registrarResultadoVisitante((byte) 0);
    
        torneo.registrarEnfrentamiento(enfrentamiento);
    
        // Recuperación y verificación de datos
        assertEquals (1, equipo1.obtenerEnfrentamientosEquipo(torneo).size());
        assertTrue(equipo1.obtenerEnfrentamientosEquipo(torneo).contains(enfrentamiento));
        assertEquals("Uniquindio", equipo1.obtenerEnfrentamientosEquipo(torneo).get(0).getEquipoLocal().getNombre());
    
        LOG.info("Fin de prueba datos completos...");
    }
    
}
