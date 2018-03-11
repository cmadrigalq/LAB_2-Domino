package Modelo;

import Modelo.Ficha.Punto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Domino:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 09/03/2018
 */
public class Domino implements Serializable{
    List<Ficha>fichas;

    public Domino() {
        fichas = new ArrayList<>();
        init();
    }

    final void init() {
        Ficha.Punto[] puntos = Ficha.Punto.values();
        for (int i = 0; i < puntos.length; i++) {
            for (int j = i; j < puntos.length; j++) {
                Ficha f = new Ficha(puntos[i],puntos[j]);
                fichas.add(f);
            }
        }
    }
    
    public Ficha sacarFicha(){
        int rand = ((int)Math.random())%fichas.size();
        return fichas.remove(rand);
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    
    

}
