/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicazione_cs_tcp;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ClientTCP {

    String nome;
    String colore;
    Socket socket;
    BufferedReader keyboard;
    DataInputStream dis;
    DataOutputStream dos;
    String messaggio;

    public ClientTCP(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    public Socket connetti(String nomeServer, int portaServer) {
        try {
            System.out.println("---------------------------");
            System.out.println("Client in esecuzione\n");
            System.out.println("---------------------------");
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nomeServer, portaServer);

            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException ex) {
            System.err.println("host sconosciuto!");
        } catch (IOException e) {
            System.err.println("Impossibile connettersi!!");
            System.err.println("Riprova!!\n" + e.getMessage());
            System.exit(1);
        }

        return socket;
    }

    public void comunica() {
        scrivi();
    }

    public void scrivi() {

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(input);

        try {
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Scrivi una frase ed il server");
        System.out.println("rispondera' con il numero di vocali");
        System.out.println("in essa contenute");
        System.out.println("---------------------------");
        String nomeColorato;

        while (true) {
            nomeColorato = ("[" + this.nome + "]");
            
            cambioColore(nomeColorato, this.colore);
            try {
                messaggio = keyboard.readLine();
                if (messaggio.equals("esci")) {
                    break;
                }
                dos.writeBytes(messaggio + "\n");
                leggi();

            } catch (IOException ex) {
                Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        System.out.println("Chiusura della connessione...");
        System.out.println("---------------------------");
        chiudi();

    }

    public void leggi() {
        System.out.println("In attesa di una risposta dal server...\n");
        try {
            dis = new DataInputStream(socket.getInputStream());
            String risposta = dis.readLine();
            System.out.println(risposta + "\n");
            System.out.println("---------------------------");
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cambioColore(String nome, String colore) {
        nome = this.nome;
        colore = this.colore;

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";

        switch (colore) {
            case "rosso" ->
                System.out.println(ANSI_RED + nome + ANSI_RESET + ": ");
            case "verde" ->
                System.out.println(ANSI_GREEN + nome + ANSI_RESET + ": ");
            case "giallo" ->
                System.out.println(ANSI_YELLOW + nome + ANSI_RESET + ": ");
            case "blu" ->
                System.out.println(ANSI_BLUE + nome + ANSI_RESET + ": ");
            default ->
                System.out.println("Scelta non valida");
        }
        System.out.println("---------------------------");
    }

    public void chiudi() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
