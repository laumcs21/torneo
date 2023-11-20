package co.edu.uniquindio.poo.torneodeportivo;
import java.time.LocalDateTime;

public enum EstadoEnfrentamiento {
// devuelve que estado tiene el enfrentamiento por su fecha; hora y resultado
    PENDIENTE {
    
        public boolean esValido(LocalDateTime fechaYHora, Byte resultadoLocal, Byte resultadoVisitante) {
            return fechaYHora.isBefore(LocalDateTime.now()) && resultadoLocal == null && resultadoVisitante == null;
        }
    },
    ENJUEGO {
        public boolean esValido(LocalDateTime fechaYHora, Byte resultadoLocal, Byte resultadoVisitante) {
            return fechaYHora.isEqual(LocalDateTime.now()) || fechaYHora.isAfter(LocalDateTime.now()) && resultadoLocal == null && resultadoVisitante == null;
        }
    },
    FINALIZADO {
        public boolean esValido(LocalDateTime fechaYHora, Byte resultadoLocal, Byte resultadoVisitante) {
            return fechaYHora.isAfter(LocalDateTime.now()) && resultadoLocal != null && resultadoVisitante != null;
        }
    }, 

    APLAZADO {
        public boolean esValido(LocalDateTime fechaYHora, Byte resultadoLocal, Byte resultadoVisitante) {
            return fechaYHora.isBefore(LocalDateTime.now()) || fechaYHora.isEqual(LocalDateTime.now()) || fechaYHora.isAfter(LocalDateTime.now()) && resultadoLocal == null && resultadoVisitante == null;
        }
    };

    public abstract boolean esValido(LocalDateTime fechaYHora, Byte resultadoLocal, Byte resultadoVisitante);
}
