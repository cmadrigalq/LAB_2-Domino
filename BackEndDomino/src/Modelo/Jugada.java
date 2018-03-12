package Modelo;

import java.io.Serializable;

/**
 * Jugada:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 09/03/2018
 */
public class Jugada implements Serializable{
    Jugador quien;
    Ficha cual;
    int numeroDeJuego;
    boolean derecha;
    int secuencia;

    public Jugada(Jugador quien, Ficha cual, int numeroDeJuego, boolean derecha, int secuencia) {
        this.quien = quien;
        this.cual = cual;
        this.numeroDeJuego = numeroDeJuego;
        this.derecha = derecha;
        this.secuencia = secuencia;
    }

    public void guardaJugada(){
        //TO-DO
    }
    
    public Jugador getQuien() {
        return quien;
    }

    public void setQuien(Jugador quien) {
        this.quien = quien;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public Ficha getCual() {
        return cual;
    }

    public void setCual(Ficha cual) {
        this.cual = cual;
    }

    public int getNumeroDeJuego() {
        return numeroDeJuego;
    }

    public void setNumeroDeJuego(int numeroDeJuego) {
        this.numeroDeJuego = numeroDeJuego;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
    
    
}
