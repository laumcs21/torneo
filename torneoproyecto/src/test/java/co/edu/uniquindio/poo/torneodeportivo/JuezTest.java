/**
 * Clase para probar el registro de los jugadores
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
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class JuezTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(JugadorTest.class.getName());

    /**
     * Verificar que sea posible registrar un juez en el torneo 
     * 
     */
    @Test
    public void registrarJuezTorneo() {
        LOG.info("Inicio de prueba registrarJugadorTorneo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var juez = new Juez("Ramon", "Perez", "ramonp@email.com", "6067471234","123");

        torneo.registrarJuez(juez);

        // Recuperación y verificación de datos
        assertTrue(torneo.getJueces().contains(juez));
        assertEquals(1, torneo.getJueces().size());
        LOG.info("Fin de prueba registrarJugadorTorneo...");
    }

    /**
     * Verificar que no sea posible registrar dos jugadores con el mismo nombre y apellido en un mismo  torneo 
     * 
     */
    @Test
    public void registrarJuecesRepetidosTorneo() {
        LOG.info("Inicio de prueba registrarJugadoresRepetidosTorneo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300},  Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Equipo{Quindío}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL, CaracterTorneo.TorneoMasculino);
        var juez1 = new Juez("Ramon", "Perez", "ramonp@email.com", "6067471234","123");
        var juez2 = new Juez("Ramon", "Perez", "ramonp@email.com", "6067471234","123");

        torneo.registrarJuez(juez1);     
        assertThrows(Throwable.class,()->torneo.registrarJuez(juez2));

        // Recuperación y verificación de datos
        
        LOG.info("Fin de prueba registrarJugadoresRepetidosTorneo...");
    }


}