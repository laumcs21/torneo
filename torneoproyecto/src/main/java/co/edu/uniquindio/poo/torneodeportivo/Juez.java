/**
 * Clase que agrupa los datos de un Jugador
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Juez extends Persona {
    private final String licencia;

    public Juez(String nombre, String apellido, String email, String celular,String licencia ) {
        super(nombre, apellido, email, celular);
        ASSERTION.assertion( licencia != null , "La licencia es requerida");
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }
}