package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;
    MioThread(Socket s){
        this.s = s;
    
    try {

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());          

            String stringaRicevuta;
            String scelta;
            String cambio = " ";

            do {

                scelta = in.readLine();
                stringaRicevuta = in.readLine();
                System.out.println("Stringa ricevuta: " + stringaRicevuta);

                switch (scelta) {
                    case "M":
                        System.out.println("Stringa ricevuta: " + stringaRicevuta);
                        cambio = stringaRicevuta.toUpperCase();
                        break;
                
                    case "m":
                        System.out.println("Stringa ricevuta: " + stringaRicevuta);
                        cambio = stringaRicevuta.toLowerCase();
                        break;
                
                    case "R":
                        System.out.println("Stringa ricevuta: " + stringaRicevuta);
                        cambio = new StringBuilder(stringaRicevuta).reverse().toString();
                        break;
                
                    case "C":
                        System.out.println("Stringa ricevuta: " + stringaRicevuta);
                        cambio = stringaRicevuta.length()+"";
                        break;
                
                    case "!":
                        System.out.println("STOP");
                        cambio = scelta;
                        break;
                
                    default:
                        break;
                }        

                out.writeBytes(cambio + "\n");
                
            } while (!scelta.equals("!"));

            this.s.close();
            out.close();
            in.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
             
    }
}


