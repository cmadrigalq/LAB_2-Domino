/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Json.Json;
import Modelo.Jugada;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Observable;
import java.util.logging.*;

public class ServidorHilo extends Observable implements Runnable {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    List<ServidorHilo> hilos;
    boolean primeraVez = true;
    void actualizar() {
        String str = Json.toJson(Servidor.partida);
        for (ServidorHilo sh : hilos) {
            try {
                sh.dos.writeChars(str);
                sh.dos.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ServidorHilo(Socket socket, int id, List<ServidorHilo> hilos) {
        this.socket = socket;
        this.idSessio = id;
        this.hilos = hilos;
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String accion = "";
        String str = dis.toString();
        if (str.equals("echo")) {
            try {
                if (!primeraVez) {
                    dos.writeChars("echo");
                    dos.flush();
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Jugada jugada = (Jugada) Json.toObject(str, Jugada.class);
        notifyObservers(jugada);
        while (!Servidor.partida.getListoParaEnviar());
        actualizar();
        desconnectar();
    }
}
