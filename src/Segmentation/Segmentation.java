package Segmentation;
import java.util.ArrayList;
import java.util.List;
import Reseau.*;

public class Segmentation {

    private static final int TAILLE_MAX_DONNEES = 128; // Taille maximale d'un paquet en octets

    public static List<PaquetReseau> segmenterDonnees(int adresseSource, int adresseDestination, String donnees) {
        List<PaquetReseau> paquets = new ArrayList<>();

        int tailleDonnees = donnees.length();
        for (int i = 0; i < tailleDonnees; i += TAILLE_MAX_DONNEES) {
            String segment = donnees.substring(i, Math.min(tailleDonnees, i + TAILLE_MAX_DONNEES));
            paquets.add(new PaquetReseau(adresseSource, adresseDestination, TypePaquet.N_DATA_REQ, segment));
        }

        return paquets;
    }
}
