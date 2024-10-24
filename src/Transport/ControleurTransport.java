package Transport;

import Reseau.PaquetReseau;
import Reseau.TypePaquet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ControleurTransport {

    // Méthode pour créer une demande de connexion
    public PaquetReseau demandeConnexion(int adresseSource, int adresseDestination) {
        System.out.println("Demande de connexion envoyée de " + adresseSource + " vers " + adresseDestination);
        return new PaquetReseau(1, TypePaquet.N_CONNECT_REQ, adresseSource, adresseDestination, null, 0, 0, false);
    }

    // Méthode pour confirmer la connexion
    public void confirmerConnexion(PaquetReseau paquet) {
        System.out.println("Connexion confirmée pour l'adresse source " + paquet.getAdresseSource() + " vers l'adresse destination " + paquet.getAdresseDestination());
    }

    // Méthode pour envoyer des données
    public PaquetReseau envoyerDonnees(int adresseSource, int adresseDestination, String donnees, int numeroSequence, boolean bitM) {
        System.out.println("Envoi de données de " + adresseSource + " vers " + adresseDestination + ": " + donnees);
        return new PaquetReseau(1, TypePaquet.N_DATA_REQ, adresseSource, adresseDestination, donnees, 0, numeroSequence, bitM);
    }

    // Méthode pour libérer la connexion
    public PaquetReseau libererConnexion(int adresseSource, int adresseDestination) {
        System.out.println("Libération de la connexion entre " + adresseSource + " et " + adresseDestination);
        return new PaquetReseau(1, TypePaquet.N_DISCONNECT, adresseSource, adresseDestination, null, 0, 0, false);
    }

    // Méthode pour lire les données depuis S_lec.txt
    public String lireDepuisS_lec() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("S_lec.txt"));
        StringBuilder donnees = new StringBuilder();
        String ligne;

        while ((ligne = reader.readLine()) != null) {
            donnees.append(ligne).append("\n");
        }
        reader.close();

        if (donnees.length() == 0) {
            return null;  // Retourner null si aucun contenu n'est trouvé
        }

        return donnees.toString().trim();  // Retourner les données
    }

    // Méthode pour écrire des données dans S_ecr.txt
    public void ecrireDansS_ecr(String contenu) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("S_ecr.txt", true));  // Écriture en mode append
        writer.write(contenu);
        writer.newLine();
        writer.close();
    }
}
