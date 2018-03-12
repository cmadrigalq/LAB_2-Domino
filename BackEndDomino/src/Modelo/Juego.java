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
    int numeroDeJuego;
    List<Jugador>jugadores;
    int jugadorActual;
    List<Jugada>jugadas;
    Punto derecha;
    Punto izquierda;
    Domino domino;
    
    
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
    
    public void iniciar(){
        do{
            Jugada jugada = esperarJugada();
            if(realizarJugada(jugada.isDerecha(), jugada.getCual())){
                jugada.guardaJugada();
                ++this.jugadorActual;
                this.jugadorActual %= this.jugadores.size();//actual = actual % size
            }            
        }while(!hayGanador() &&aunHayJugadas());
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
            derecha = f.getValor1();
            izquierda = f.getValor2();
            return true;
        }
        if(isDerecha){
            if(derecha == f.getValor1()){
                derecha = f.getValor2();
                return true;
            }
            if(derecha == f.getValor2()){
                derecha = f.getValor1();
                return true;
            }
        }else{
            if(izquierda == f.getValor1()){
                izquierda = f.getValor2();
                return true;
            }
            if(izquierda == f.getValor2()){
                izquierda = f.getValor1();
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
    
    
}
