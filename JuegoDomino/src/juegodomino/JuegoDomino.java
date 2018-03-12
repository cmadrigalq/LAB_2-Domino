/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodomino;

import Juego.JuegoEnConsola;
import Modelo.Juego;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cynthia Madrigal
 * 
 */
public class JuegoDomino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // DominoVista dv = new DominoVista();
       // dv.setVisible(true);
        String j1 = "Cyn",j2 = "Dani";
        List<String>jugadores = new ArrayList<>();
        jugadores.add(j1);
        jugadores.add(j2);
        Juego juego = new JuegoEnConsola(jugadores);
        juego.iniciar();
    }
    
}
