package Modelo;
/**
 *    Arriba
 *    =======    ----> Vertical 1
 *   | V1    |
 *   |       |
 * D ----o----  Iz
 *   | v2    | 
 *   |       |
 *   =========
 *    Abajo
 * 
 * *    Abajo
 *    =======    ----> Vertical 2
 *   | V2    |
 *   |       |
 * I ----o----  Dr
 *   | v1    | 
 *   |       |
 *   =========
 *    Arriba
 * 
 *       Arriba
 *   ================       ----> Horizontal 1
 *   |       |       |
 * I |  v2   | v1    |  Der
 *   ================
 *     abajo
 * 
 *        Abajo
 *   ================       ----> Horizontal 2
 *   |       |       |
 * D |  v1   | v2    |  Izq
 *   ================
 *        Arriba
 * 
 * 
 */
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
        Punto_0,Punto_1,Punto_2,Punto_3,
        Punto_4,Punto_5,Punto_6
    }
    public enum Direccion implements Serializable{
        Arriba,Abajo,Derecha,Izquierda
    }
    public enum Posicion implements Serializable{
        Vertical1/*con el valor1 hacia arriba*/,
        Vertical2/*con el valor2 hacia arriba*/,
        Horizontal1/*con el valor 1 hacia la derecha*/,
        Horizontal2/*con el valor 2 hacia la derecha*/
    }
    Punto valor1;
    Punto valor2;
    Ficha fichaArriba;
    Ficha fichaAbajo;
    Ficha fichaDerecha;
    Ficha fichaIzquierda;
    Posicion posicion;
    boolean esPar;//si ambos valores son iguales
    //<<<>>><<<>>>Constructores<<<>>><<<>>>

    
}
