package co.edu.uniquindio.poo.torneodeportivo;

public enum CaracterTorneo {
  //Crea un torneo el cual el tipo de equipo debe ser femenino
    TorneoFemenino {
        public boolean esValido(Equipo equipo){
            return equipo.getTipoEquipo() == TipoEquipo.FEMENINO;
        }
    },
//Crea un torneo el cual el tipo de equipo debe ser masculino
    TorneoMasculino{
        public boolean esValido(Equipo equipo){
            return equipo.getTipoEquipo() == TipoEquipo.MASCULINO;
        }
    },
//Crea un torneo el cual el tipo de equipo debe ser mixto
    TorneoMixto{
        public boolean esValido(Equipo equipo) {
            boolean alMenosUnHombre = false;
            boolean alMenosUnaMujer = false;
                    
                for (Jugador jugador : equipo.getJugadores()) {
                    if (jugador.getGenero() == genero.MASCULINO) {
                        alMenosUnHombre = true;
                    }
                    if (jugador.getGenero() == genero.FEMENINO) {
                        alMenosUnaMujer = true;
                    }
                        
                    // Si ya encontramos al menos un hombre y una mujer, no necesitamos seguir buscando.
                    if (alMenosUnHombre && alMenosUnaMujer) {
                        return true;
                    }
                }
                    
                return false;
                }
            };
        
        // metodo abstracto para validar un equipo
    public abstract boolean esValido(Equipo equipo);
}
