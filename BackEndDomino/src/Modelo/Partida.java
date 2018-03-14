package Modelo;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Partida:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 13/03/2018
 */
public class Partida extends Juego implements Observer {

    public Partida(int numeroDeJuego, List<Jugador> jugadores, int jugadorActual, List<Jugada> jugadas, Ficha.Punto derecha, Ficha.Punto izquierda, Domino domino) {
        super(numeroDeJuego, jugadores, jugadorActual, jugadas, derecha, izquierda, domino);
    }

    public Partida() {
    }
    
    
    
    Jugada jugada = null;
    Boolean listoParaEnviar = false;

    public Partida(List<String> nicks) {
        super(nicks);
    }
    
    
    
   

    public Boolean getListoParaEnviar() {
        return listoParaEnviar;
    }

    public void setListoParaEnviar(Boolean listoParaEnviar) {
        this.listoParaEnviar = listoParaEnviar;
    }
    
    void hacerJugada() {
        if (jugada == null) {
            siguienteJugador();
            return;
        }
        if (jugada.secuencia == -1) {
            super.getActual().getFichas().add(super.domino.sacarFicha());
        } else if (realizarJugada(jugada.isDerecha(), jugada.getCual())) {
            jugada.guardaJugada();
            jugadas.add(jugada);
            siguienteJugador();
        }
        jugada = null;
        listoParaEnviar = true;
    }


    @Override
    public void update(Observable o, Object arg) {
        listoParaEnviar = false;
        jugada = (Jugada)arg;
    }

    @Override
    public Jugada esperarJugada() {
        return jugada;
    }

}





