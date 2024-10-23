package Transport;
import Reseau.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ControleurTransport {

    public PaquetReseau demandeConnexion(int adresseSource, int adresseDestination) throws IOException {
        System.out.println("Transport: Demande de connexion de " + adresseSource + " vers " + adresseDestination);
        ecrireDansS_ecr("Demande de connexion de " + adresseSource + " vers " + adresseDestination);
        return new PaquetReseau(adresseSource, adresseDestination, TypePaquet.N_CONNECT_REQ, null);
    }

    public void confirmerConnexion(PaquetReseau paquet) throws IOException {
        System.out.println("Transport: Connexion confirmée pour " + paquet.getAdresseSource() + " vers " + paquet.getAdresseDestination());
        ecrireDansS_ecr("Connexion confirmée pour " + paquet.getAdresseSource());
    }

    public PaquetReseau envoyerDonnees(int adresseSource, int adresseDestination, String donnees) throws IOException {
        System.out.println("Transport: Envoi des données: " + donnees);
        ecrireDansS_ecr("Données envoyées: " + donnees);
        return new PaquetReseau(adresseSource, adresseDestination, TypePaquet.N_DATA_REQ, donnees);
    }

    public void libererConnexion(int adresseSource, int adresseDestination) throws IOException {
        System.out.println("Transport: Libération de connexion de " + adresseSource + " vers " + adresseDestination);
        ecrireDansS_ecr("Libération de connexion de " + adresseSource);
    }

    // Lire depuis S_lec
    public String lireDepuisS_lec() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("S_lec.txt"));
        String donnees = reader.readLine();
        reader.close();
        return donnees;
    }

    // Écrire dans S_ecr
    private void ecrireDansS_ecr(String contenu) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("S_ecr.txt", true));
        writer.write(contenu);
        writer.newLine();
        writer.close();
    }
}
