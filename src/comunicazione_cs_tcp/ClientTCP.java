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
    
    public ClientTCP(String nome, String colore){
        this.nome = nome;
        this.colore = colore;
    }
    
    
    public Socket connetti (String nomeServer, int portaServer){
        try{
            System.out.println(" ______________________________\n");
            System.out.println("      Client in esecuzione\n");
            System.out.println(" ______________________________\n");
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nomeServer, portaServer);
            
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch(UnknownHostException ex){
            System.err.println("host sconosciuto!");
        } catch (IOException e){
            System.err.println("Impossibile connettersi!!");
            System.err.println("Riprova!!\n" + e.getMessage());
            System.exit(1);
        }
        
        return socket;
    }
    
    public void comunica(){
        scrivi();
    }
    
    public void scrivi(){
         
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader keyboard = new BufferedReader(input);
            
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            System.out.print("Il server contera' le vocali presenti nella tua stringa \n");
            while(true){
                String stringaNome = ("[" + this.nome + "] ");
                cambioColore(stringaNome, colore);
                try {
                    messaggio = keyboard.readLine();
                    if(messaggio.equals("esci")){
                        break;
                    }
                    dos.writeBytes(messaggio + "\n");
                    leggi();
                } catch (IOException ex) {
                    Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        
            System.out.println("Chiusura della connessione...");
            chiudi();
        
    }
    
    public void leggi(){
        System.out.println("In attesa di una risposta dal server...\n");
        try {
            String risposta = dis.readLine();
            System.out.println(risposta + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cambioColore(String text, String colore){
        nome = this.nome;
        colore = this.colore;
        String stringaNome = null;
        
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        
        System.out.println("-------------------");
        
        switch (colore) {
            
            case "rosso" -> System.out.println(ANSI_RED + text + ANSI_RESET);
            case "giallo" -> System.out.println(ANSI_YELLOW + text + ANSI_RESET);
            case "blu" -> System.out.println(ANSI_BLUE + text + ANSI_RESET);
            case "verde" -> System.out.println(ANSI_GREEN + text + ANSI_RESET);
            
            default -> System.out.println("Colore non riconosciuto!");
        }
    }
    
    public void chiudi(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
