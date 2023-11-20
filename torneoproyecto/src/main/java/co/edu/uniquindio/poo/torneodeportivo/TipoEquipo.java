package co.edu.uniquindio.poo.torneodeportivo;

public enum TipoEquipo {

// valida el tipo de equipo
   
    FEMENINO {
            public boolean esValido(Jugador jugador){
                return jugador.getGenero() == genero.FEMENINO;
            }
        },
    
    MASCULINO{
            public boolean esValido(Jugador jugador){
                return jugador.getGenero() == genero.MASCULINO;
            }
        },

    MIXTO{
            public boolean esValido(Jugador jugador){
                return jugador.getGenero() == genero.MASCULINO || jugador.getGenero() == genero.FEMENINO;
            }
        };
    
        public abstract boolean esValido(Jugador jugador);
}
