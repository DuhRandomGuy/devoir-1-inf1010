package DataLink;

import Reseau.PaquetReseau;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataLinkLayer {

    private File lEcrFile;

    // Simuler la couche de liaison
    public PaquetReseau sendPacket(PaquetReseau paquet) {

        System.out.println("Couche Liaison: Envoi du paquet: ");
        // Écriture dans le fichier L_ecr pour simuler l'envoi d'un paquet

        if (paquet.getAdresseSource()!=0){

        }
            else{

        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lEcrFile, true))) {
            writer.write("ConnId: " + paquet.getNumConnexion() + ", Src: " + paquet.getAdresseSource() + ", Dst: " + paquet.getAdresseDestination() + ", Type: " + paquet.getTypePaquet());
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return paquet;
    }

    public String receivePacket() {
        // Simule une réponse de l'entité distante (pour cet exemple, on suppose une connexion réussie)
        return "ACK: Connexion réussie";
    }
}
