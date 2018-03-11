package Modelo;

import java.io.Serializable;

/**
 * Partida:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 09/03/2018
 */
public abstract class Partida implements Serializable{
    Juego juego;

    public Partida(Juego juego) {
        this.juego = juego;
        juego.setPartida(this);
    }
    abstract Jugada esperarJugada(Jugador j);
    public boolean realizarJugada(boolean derecha,Ficha f){
        Jugador j = juego.getActual();
        if(juego.ponerFicha(f, derecha)){
            j.ponerFicha(f);
            return true;
        }
        return false;
    }
    
    public boolean hayGanador(){
        return juego.jugadores.get(juego.getJugadorActual()).gano();
    }
    public boolean aunHayJugadas() {
        if (!juego.getDomino().getFichas().isEmpty()) {
            return true;
        }
        for (Jugador j : juego.getJugadores()) {
            for (Ficha f : j.getFichas()) {
                if (f.getValor1() == juego.getDerecha()
                        || f.getValor1() == juego.getIzquierda()
                        || f.getValor2() == juego.getDerecha()
                        || f.getValor2() == juego.getIzquierda()) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
