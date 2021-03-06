package Juego;

import Modelo.Domino;
import Modelo.Ficha;
import Modelo.Juego;
import Modelo.Jugada;
import Modelo.Jugador;
import Modelo.Partida;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * JuegoEnConsola:
 *
 * @version 1.0.0
 * @author Cynthia Madrigal Quesada
 * @date 10/03/2018
 */
public class JuegoEnConsola extends Juego{

    Scanner sc;
    int secuencia = 0;
    int idHilo;
    public JuegoEnConsola(Partida p) {
        super(new ArrayList());
        this.numeroDeJuego = p.getNumeroDeJuego();
        this.jugadores = p.getJugadores();
        this.jugadorActual = p.getJugadorActual();
        this.jugadas = p.getJugadas();
        this.derecha = p.getDerecha();
        this.izquierda = p.getIzquierda();
        this.domino = p.getDomino();
    }

    public int getTurno() {
        return idHilo;
    }

    public void setTurno(int turno) {
        this.idHilo = turno;
    }

    @Override
    public Jugada esperarJugada() {
        System.err.println("TURNO DE " + getActual().getNombre());
        System.out.println(super.toString());
        if(idHilo != jugadorActual){
            return null;
        }
        
        Boolean isDerecha = preguntarDonde();
        if(isDerecha == null){
            return null;
        }
        if(idHilo != jugadorActual){
            return null;
        }
        Ficha f = getActual().getFichas().get(this.preguntarCualFicha(getActual()));
        Jugada jugada = new Jugada(getActual(),f,getNumeroDeJuego(),isDerecha,secuencia+1);
        return jugada;
    }

    Boolean preguntarDonde() {
        while (true) {
            System.out.println(getActual().mostrarFichas());
            System.out.println("Desea Jugar a l Izquierda (I) o a la derecha (D), (C) para comer, S para saltar turno");
            String a = sc.nextLine();
            if (a.equalsIgnoreCase("I") || a.equalsIgnoreCase("D") || a.equalsIgnoreCase("C")|| a.equalsIgnoreCase("S")) {
                if(a.equalsIgnoreCase("S") ||(a.equalsIgnoreCase("C")&&domino.getFichas().isEmpty())){
                    return null;
                }
                if(a.equalsIgnoreCase("C")){
                    getActual().getFichas().add(domino.sacarFicha());
                    continue;
                }
                return a.equalsIgnoreCase("D");
            }
            System.err.println("Debe ingresar una I para izquierda, o una D para derecha");
        }
    }

    int preguntarCualFicha(Jugador j) {
        while (true) {
            System.out.println("Ingrese el # de ficha a partir de 0");
            int num = sc.nextInt();
            if (num < 0 && num >= j.getFichas().size()) {
                System.err.println("El # de ficha debe ser entre 0 y n");
                continue;
            }
            return num;

        }
    }

}
