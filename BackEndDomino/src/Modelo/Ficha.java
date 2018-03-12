package Modelo;

import java.io.Serializable;

/**
 * Ficha:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 08/03/2018
 */
public class Ficha implements Serializable {

    public enum Punto implements Serializable {
        Punto_0("0"),Punto_1("1"),Punto_2("2"),
        Punto_3("3"),Punto_4("4"),Punto_5("5"),
        Punto_6("6");
        private final String representacion;
        private Punto(String representacion) {
            this.representacion = representacion;
        }
        
        public Integer getIntValue(){
            return Integer.valueOf(representacion);
        }
    }
    int id_ficha;
    Punto valor1;
    Punto valor2;
    Boolean noVolteada;
    //<<<>>><<<>>>Constructores<<<>>><<<>>>
    public Ficha(Punto valor1, Punto valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
        noVolteada = null;
    }
    
    public Ficha(int id_ficha, Punto valor1, Punto valor2, boolean derecha){
        this.id_ficha = id_ficha;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.noVolteada = derecha;
    }
    //<<<>>><<<>>>SET y GET<<<>>><<<>>>

    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }

    public Boolean fueVolteada() {
        return noVolteada;
    }

    public void voltear(Boolean derecha) {
        this.noVolteada = derecha;
    }

    public Punto getValor1() {
        return valor1;
    }

    public Punto getValor2() {
        return valor2;
    }
    @Override
    public String toString(){
        if(noVolteada == null || !noVolteada){
            return "["+valor1.representacion + "|" + valor2.representacion+"]";
        }
        return "["+valor2.representacion + "|" + valor1.representacion+"]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        if (this.valor1 != other.valor1) {
            return false;
        }
        if (this.valor2 != other.valor2) {
            return false;
        }
        return true;
    }
    
     
}
