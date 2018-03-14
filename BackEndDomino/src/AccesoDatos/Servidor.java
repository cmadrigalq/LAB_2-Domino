/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.Partida;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Céspedes
 */
import java.util.logging.*;
public class Servidor {
    static public Partida partida;
    static{
        List jugadores = new ArrayList<>();
        jugadores.add("Jugador1");
        jugadores.add("Jugador2");
        partida = new Partida(jugadores);
    }
    public static void main(String args[]) throws IOException {
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
         List<ServidorHilo>hilos = new ArrayList<>();
        try {
            ss = new ServerSocket(10578);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                ServidorHilo sh =  new ServidorHilo(socket, idSession,hilos);
                hilos.add(sh);
                sh.addObserver(Servidor.partida);
                Thread t = new Thread(sh);
                t.start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
