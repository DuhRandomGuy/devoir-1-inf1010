package Reseau;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReseauClient {

    public void traiterDemandeConnexion(PaquetReseau paquet) throws IOException {
        System.out.println("Reseau: Reçu demande de connexion de " + paquet.getAdresseSource());
        ecrireDansL_ecr("Demande de connexion reçue de " + paquet.getAdresseSource());
    }

    public void repondreConnexion(PaquetReseau paquet, boolean accepter) throws IOException {
        if (accepter) {
            System.out.println("Reseau: Connexion acceptée");
            ecrireDansL_ecr("Connexion acceptée pour " + paquet.getAdresseSource());
        } else {
            System.out.println("Reseau: Connexion refusée");
            ecrireDansL_ecr("Connexion refusée pour " + paquet.getAdresseSource());
        }
    }

    public void traiterTransfertDonnees(PaquetReseau paquet) throws IOException {
        System.out.println("Reseau: Données reçues : " + paquet.getDonnees());
        ecrireDansL_ecr("Données transférées: " + paquet.getDonnees());
    }

    public void traiterLibérationConnexion(PaquetReseau paquet) throws IOException {
        System.out.println("Reseau: Libération de la connexion de " + paquet.getAdresseSource());
        ecrireDansL_ecr("Connexion libérée pour " + paquet.getAdresseSource());
    }

    // Lire depuis L_lec
    public String lireDepuisL_lec() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("L_lec.txt"));
        String ligne = reader.readLine();
        reader.close();
        return ligne;
    }

    // Écrire dans L_ecr
    private void ecrireDansL_ecr(String contenu) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("L_ecr.txt", true));
        writer.write(contenu);
        writer.newLine();
        writer.close();
    }
}
