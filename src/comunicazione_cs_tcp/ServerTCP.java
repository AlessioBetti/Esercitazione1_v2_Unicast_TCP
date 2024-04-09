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
public class ServerTCP {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    int porta = 6789; //porta del server
    BufferedReader stringaClient;
    DataOutputStream dos;
    
    public ServerTCP (int porta){
        this.porta = porta;
    }
    
      public Socket attendi(){
         
        try {
            System.out.println("Server attivo");
            System.out.println(" ___________________________________________\n");
            
            server = new ServerSocket(porta); //inizializzazione del servizio
            System.out.println("Server in ascolto");
            System.out.println("porta: " + porta);
            System.out.println(" ___________________________________________\n");
            
            
            client = server.accept(); //il server si mette in ascolto sulla porta precedentemente aperta
            System.out.println("      Connessione con un client stabilita!");
            System.out.println(" ___________________________________________\n");
            
            stringaClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            dos = new DataOutputStream(client.getOutputStream());
          
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return client; 
    }
      
    public void comunica(){
        leggi(); 
    }
    
    public void leggi(){
        try{
            while(true){
                stringaClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
                dos = new DataOutputStream(client.getOutputStream());
                stringaRicevuta = stringaClient.readLine();
                System.out.println("Stringa ricevuta: " + stringaRicevuta);
                scrivi();
            }
            
        } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        System.out.println("Chiusura della connessione...");
        chiudi();
    }
    
    public void scrivi(){
        try {
            dos = new DataOutputStream(client.getOutputStream());
            
            System.out.println("Elaborazione della stringa...");
            int risposta = contaVocali(stringaRicevuta);
            System.out.println("vocali: " + risposta);
            dos.writeBytes("[SERVER] - risposta: " + risposta + "\n");
            System.out.println("Elaborazione terminata");
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    public void chiudi(){
        try {
            dos.writeBytes(stringaRicevuta + "chiusura del server in corso...\n");
            System.out.println("Echo sul server in chiusura: " + stringaRicevuta);
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int contaVocali(String str){
        int count = 0;
        String strMinuscola = str.toLowerCase();
        for (int i = 0; i < str.length(); i++)
        {
            if (strMinuscola.charAt(i) == 'a' || strMinuscola.charAt(i) == 'e' || strMinuscola.charAt(i) == 'i'
                    || strMinuscola.charAt(i) == 'o' || strMinuscola.charAt(i) == 'u')
            {
                count++;
            }
        }
        return count;
    }
}