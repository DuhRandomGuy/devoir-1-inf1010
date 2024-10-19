package Segmentation;
import java.util.ArrayList;
import java.util.List;

import Reseau.PaquetReseau;

public class Segmentation {

    private static final int TAILLE_MAX_PAQUET = 128;

    // Méthode pour segmenter les données en plusieurs paquets de 128 octets
    public static List<PaquetReseau> segmenterDonnees(int adresseSource, int adresseDestination, String donnees) {
        List<PaquetReseau> paquets = new ArrayList<>();

        int longueurDonnees = donnees.length();
        int nombrePaquets = (int) Math.ceil((double) longueurDonnees / TAILLE_MAX_PAQUET);

        for (int i = 0; i < nombrePaquets; i++) {
            int debut = i * TAILLE_MAX_PAQUET;
            int fin = Math.min(debut + TAILLE_MAX_PAQUET, longueurDonnees);
            String segment = donnees.substring(debut, fin);
            String typePaquet = (i == nombrePaquets - 1) ? "Dernier segment" : "Segment intermédiaire";

            PaquetReseau paquet = new PaquetReseau(adresseSource, adresseDestination, "N_DATA.req", segment);
            paquets.add(paquet);
            System.out.println("Paquet segmenté : " + segment + " (" + typePaquet + ")");
        }

        return paquets;
    }
}

