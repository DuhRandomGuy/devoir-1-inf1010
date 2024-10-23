package Reseau;
import Gestion.N_CONNECT;
import DataLink.DataLinkLayer;
import java.io.*;
import java.util.*;
abstract public class NetworkEntity implements N_CONNECT {
    private File lEcrFile;
    private DataLinkLayer dataLinkLayer;
    private int sourceAddr;
    private int destAddr;
    private int numero_de_connexion;
    public NetworkEntity(int sourceAddr, int destAddr) {
       this.sourceAddr = sourceAddr;
       this.destAddr = destAddr;
       this.dataLinkLayer = new DataLinkLayer();
    }

    // Primitive pour etablire la connection
    @Override
    public void req(int sourceAddr, int destAddr) {
        // 1. attribution du numero
         this.numero_de_connexion = new Random().nextInt(100);
        System.out.println("Couche Réseau: Attribution du numéro de connexion logique: " + numero_de_connexion);

        // 2. Construction du paquet d'appel
        System.out.println("Couche Réseau: Construction du paquet d'appel.");

        PaquetReseau paquetAppel = new PaquetReseau(sourceAddr,destAddr,"00001011",null);
        //attribution du numero de connexion au paquet
        paquetAppel.setNumConnexion(numero_de_connexion);

        String connectionRequestPacket = "N_CONNECT.req : Connexion de " + sourceAddr + " à " + destAddr
                + " [Numéro de connexion: " + numero_de_connexion + "]";

        // 3. Choix d’une route (simulé ici comme une simple impression)
        System.out.println("Couche Réseau: Choix d'une route vers " + destAddr);

        // 4. Utilisation du service de liaison pour acheminer le paquet et Récupération de la réponse via la couche de liaison

        PaquetReseau reponse = dataLinkLayer.sendPacket(paquetAppel);
        // 5. Récupération de la réponse via la couche de liaison
        String response = dataLinkLayer.receivePacket();
        System.out.println("Couche Réseau: Réponse reçue: " + response);

    }

    public void demandeDeConnection(int sourceAddr, int destAddr, int connectionId){


    }



}
