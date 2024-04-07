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
    
    public void comunica() throws IOException{
        scrivi();
        leggi();
    }
    
    public void scrivi()throws IOException{
         
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader keyboard = new BufferedReader(input);
            
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            
            while(!messaggio.equals("FINE")){
                System.out.println("Messaggio da inviare al server: \n");
                try {
                    messaggio = keyboard.readLine();
                    System.out.println("Invio del messaggio in corso...\n");
                    dos.writeBytes(messaggio + "\n");
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
            System.out.println("Risposta del server: " + risposta + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
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
