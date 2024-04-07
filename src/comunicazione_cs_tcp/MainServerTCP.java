/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comunicazione_cs_tcp;
import java.io.*;
import java.net.*;

public class MainServerTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
       ServerTCP server = new ServerTCP(6789);
        server.attendi();
        server.comunica();
    }
    
}
