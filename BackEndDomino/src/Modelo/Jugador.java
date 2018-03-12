package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Jugador:
 *
 * @version 1.0.0
 * @author Cynthia Madrigal Quesada
 * @date 09/03/2018
 */
public class Jugador implements Serializable {

    String nombre;
    int partidasGanadas;
    List<Ficha> fichas;
    Domino domino;

    public Jugador(String nombre, int partidasGanadas, Domino domino) {
        this.nombre = nombre;
        this.partidasGanadas = partidasGanadas;
        this.domino = domino;
        fichas = new ArrayList<>();
        init();
    }

    public Jugador(String nombre, int partidasGanadas, List<Ficha> fichas, Domino domino) {
        this.nombre = nombre;
        this.partidasGanadas = partidasGanadas;
        this.fichas = fichas;
        this.domino = domino;
    }

    public boolean gano() {
        return this.fichas.isEmpty();
    }

    public boolean ponerFicha(Ficha f) {
        return fichas.remove(f);
    }

    final void init() {
        for (int i = 0; i < 7; i++) {
            fichas.add(domino.sacarFicha());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public String mostrarFichas() {
        String res = "";
        for (Ficha f : this.getFichas()) {
            res += (f.toString()+"   ");
        }
        return res;
    }

}
