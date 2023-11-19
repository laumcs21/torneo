/**
 * Clase que agrupa los datos de un Jugador
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Juez extends Persona {
    private final String licencia;
    public List<Enfrentamiento> enfrentamientos;
    


    public Juez(String nombre, String apellido, String email, String celular,String licencia ) {
        super(nombre, apellido, email, celular);
        ASSERTION.assertion( licencia != null , "La licencia es requerida");
        this.licencia = licencia;
        this.enfrentamientos = new ArrayList<>();
        
    }
    

    public String getLicencia() {
        return licencia;
    }



    public List<Enfrentamiento> obtenerEnfrentamientosPorJuez(String numeroLicencia) {
        return enfrentamientos.stream()
                .filter(enfrentamiento -> enfrentamiento.getJueces().stream()
                        .anyMatch(juez -> juez.getLicencia().equals(numeroLicencia)))
                .collect(Collectors.toList());
}
}