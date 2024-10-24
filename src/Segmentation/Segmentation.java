package Segmentation;

import java.util.ArrayList;
import java.util.List;
import Reseau.PaquetReseau;
import Reseau.TypePaquet;

public class Segmentation {

    private static final int TAILLE_MAX_DONNEES = 128;  // Taille maximale d'un paquet en octets

    public static List<PaquetReseau> segmenterDonnees(int adresseSource, int adresseDestination, String donnees) {
        List<PaquetReseau> paquets = new ArrayList<>();
        int tailleDonnees = donnees.length();
        int numeroSequence = 0;  // Le premier numéro de séquence

        // Parcourir les données par segments de 128 octets
        for (int i = 0; i < tailleDonnees; i += TAILLE_MAX_DONNEES) {
            // Obtenir un segment des données
            String segment = donnees.substring(i, Math.min(tailleDonnees, i + TAILLE_MAX_DONNEES));

            // Si c'est le dernier paquet, bit M = 0, sinon bit M = 1
            boolean bitM = (i + TAILLE_MAX_DONNEES < tailleDonnees);

            // Créer un paquet pour ce segment
            PaquetReseau paquet = new PaquetReseau(
                    1,  // Numéro de connexion
                    TypePaquet.N_DATA_REQ,  // Type du paquet (données)
                    adresseSource,  // Adresse source
                    adresseDestination,  // Adresse destination
                    segment,  // Segment des données
                    0,  // Pas de raison dans un paquet de données
                    numeroSequence,  // Numéro de séquence du paquet
                    bitM  // Bit M pour indiquer si c'est le dernier paquet ou non
            );

            // Ajouter le paquet à la liste des paquets
            paquets.add(paquet);

            // Incrémenter le numéro de séquence modulo 8 (puisque la numérotation est sur 3 bits)
            numeroSequence = (numeroSequence + 1) % 8;
        }

        return paquets;
    }
}
