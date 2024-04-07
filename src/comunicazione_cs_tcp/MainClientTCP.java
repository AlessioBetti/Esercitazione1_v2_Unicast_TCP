/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
public class MainClientTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(input);
        
        String nome = "";
        String colore = "";
        System.out.println("=> Inserisci il tuo nome:\n\n");
        try {
            nome = keyboard.readLine();
        } catch (IOException ex) {
           System.err.println("errore in input!");
        }
        
        System.out.println("=> Inserisci il colore con il quale vorresti essere visualizzato:\n\n");
        try {
            colore = keyboard.readLine();
        } catch (IOException ex) {
           System.err.println("errore in input!");
        }
        
        ClientTCP client = new ClientTCP(nome, colore);
        client.connetti("localhost", 6789);
        
        try {
            client.comunica();
        } catch (IOException ex) {
            Logger.getLogger(MainClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
