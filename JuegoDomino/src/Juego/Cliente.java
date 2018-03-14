/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author Manuel Céspedes
 */
import Json.Json;
import Modelo.Jugada;
import Modelo.Partida;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;
class Persona extends Thread {
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id;
    public Persona(int id) {
        this.id = id;
        try {
            sk = new Socket("127.0.0.1", 10578);
            dis = new DataInputStream(sk.getInputStream());
            dos = new DataOutputStream(sk.getOutputStream());
            dos.writeUTF("echo");
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        try {

            
            String respuesta = dis.readUTF();
            if (respuesta.equals("echo")) {
                return;
            } else {

                Partida p = (Partida) Json.toObject(respuesta, Partida.class);
                JuegoEnConsola jc = new JuegoEnConsola(p);
                jc.setTurno(id);
                Jugada j = jc.iniciar();
                if(p.getJugadorActual() == id){
                    String out = Json.toJson(j);
                    dos.writeUTF(out);
                }else{
                    dos.writeUTF("echo");
                }
            }
            dos.flush();
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
public class Cliente {
    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 2; i++) {
            clients.add(new Persona(i));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
}