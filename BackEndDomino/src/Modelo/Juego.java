package Modelo;

import Modelo.Ficha.Punto;
import java.util.ArrayList;
import java.util.List;

/**
 * Juego:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 09/03/2018
 */
public abstract class Juego {
    protected int numeroDeJuego;
    protected List<Jugador>jugadores;
    protected int jugadorActual;
    protected List<Jugada>jugadas;
    protected Punto derecha;
    protected Punto izquierda;
    protected Domino domino;

    public Juego(int numeroDeJuego, List<Jugador> jugadores, int jugadorActual, List<Jugada> jugadas, Punto derecha, Punto izquierda, Domino domino) {
        this.numeroDeJuego = numeroDeJuego;
        this.jugadores = jugadores;
        this.jugadorActual = jugadorActual;
        this.jugadas = jugadas;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.domino = domino;
    }

    public Juego() {
    }
    
    
    
    public abstract Jugada esperarJugada();
    public boolean realizarJugada(boolean derecha,Ficha f){
        Jugador j = getActual();
        if(ponerFicha(f, derecha)){
            j.ponerFicha(f);
            return true;
        }
        return false;
    }
    public Juego(List<String>nicks){
        this.jugadores = new ArrayList<>();
        jugadas = new ArrayList<>();
        derecha = null;
        izquierda = null;
        domino = new Domino();
        numeroDeJuego = 0;//consultarlo, tomar el ultimo registrado en la DB y sumarle 1
        for(String n:nicks){
            Jugador j = new Jugador(n,0,this.domino);
            this.jugadores.add(j);
        }
    }
    public Jugada iniciar(){
        if(!hayGanador() &&aunHayJugadas()){
            Jugada jugada = esperarJugada();
            if(jugada == null){
                siguienteJugador();
            }
            else if(realizarJugada(jugada.isDerecha(), jugada.getCual())){
                jugada.guardaJugada();
                jugadas.add(jugada);
                siguienteJugador();
            }       
            return jugada;
        }
        return null;
    }
    
    
  
    
    void siguienteJugador(){
        ++this.jugadorActual;
        this.jugadorActual %= this.jugadores.size();//actual = actual % size
    }
    
    final void init() {
        Ficha.Punto[] puntos = Ficha.Punto.values();
        for (int i = 6;i>=0;i++) {
            Ficha f = new Ficha(puntos[i], puntos[i]);
            for (int j = 0;j<jugadores.size();j++) {
                if (jugadores.get(j).getFichas().indexOf(f) != -1) {
                    jugadorActual = j;
                    return;
                }
            }
        }
        jugadorActual = 0;
    }

    public boolean ponerFicha(Ficha f, boolean isDerecha){
        if(this.derecha == null && izquierda == null){
            derecha = f.getValor2();
            izquierda = f.getValor1();
            f.voltear(false);
            return true;
        }
        if(isDerecha){
            if(derecha == f.getValor1()){
                derecha = f.getValor2();
                f.voltear(false);
                return true;
            }
            if(derecha == f.getValor2()){
                derecha = f.getValor1();
                f.voltear(true);
                return true;
            }
        }else{
            if(izquierda == f.getValor1()){
                izquierda = f.getValor2();
                f.voltear(true);
                return true;
            }
            if(izquierda == f.getValor2()){
                izquierda = f.getValor1();
                f.voltear(false);
                return true;
            }
        }
        return false;
    }
    

    public int getNumeroDeJuego() {
        return numeroDeJuego;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public Jugador getActual(){
        return this.jugadores.get(this.jugadorActual);
    }
    
    public List<Jugada> getJugadas() {
        return jugadas;
    }

    public Punto getDerecha() {
        return derecha;
    }

    public Punto getIzquierda() {
        return izquierda;
    }

    public Domino getDomino() {
        return domino;
    }

    public void setNumeroDeJuego(int numeroDeJuego) {
        this.numeroDeJuego = numeroDeJuego;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setJugadorActual(int jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void setJugadas(List<Jugada> jugadas) {
        this.jugadas = jugadas;
    }

    public void setDerecha(Punto derecha) {
        this.derecha = derecha;
    }

    public void setIzquierda(Punto izquierda) {
        this.izquierda = izquierda;
    }

    public void setDomino(Domino domino) {
        this.domino = domino;
    }


    public boolean aunHayJugadas() {
        if (!this.getDomino().getFichas().isEmpty()) {
            return true;
        }
        for (Jugador j : this.getJugadores()) {
            for (Ficha f : j.getFichas()) {
                if (f.getValor1() == this.getDerecha()
                        || f.getValor1() == this.getIzquierda()
                        || f.getValor2() == this.getDerecha()
                        || f.getValor2() == this.getIzquierda()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean hayGanador(){
        return jugadores.get(getJugadorActual()).gano();
    }
    @Override
    public String toString(){
        String r = "";
        if(getJugadas().isEmpty()){
            r+= "AUN NO SE HAN REALIZADO JUGADAS";
        }
        for(Jugada j : this.getJugadas()){
            if(j.isDerecha()){
                r+= j.getCual().toString();
            }else{
                r = j.getCual().toString() + r;
            }
        }
        return "\n\tTablero Actual\n"+r+"\n\n";
    }
    
    
}
