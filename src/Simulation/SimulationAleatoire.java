package Simulation;
import java.util.Random;

public class SimulationAleatoire {

    private static Random random = new Random();

    /**
     * Simule l'acceptation ou le refus d'une connexion basée sur l'adresse source.
     * Si l'adresse est multiple de 19 ou de 13, la connexion est refusée ou pas de réponse.
     *
     * @param adresseSource Adresse source de la connexion.
     * @return True si la connexion est acceptée, False sinon.
     */
    public static boolean tirageConnexion(int adresseSource) {
        try {
            if (adresseSource % 19 == 0) {
                System.out.println("Aucune réponse à la demande de connexion (multiples de 19).");
                return false;
            } else if (adresseSource % 13 == 0) {
                System.out.println("Refus de connexion par la station distante (multiples de 13).");
                return false;
            }
            System.out.println("Connexion acceptée.");
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la simulation de connexion: " + e.getMessage());
            return false;
        }
    }

    /**
     * Simule les acquittements (positif, négatif ou absence) pour chaque paquet.
     *
     * @param adresseSource Adresse source de la connexion.
     * @param numeroPaquet Numéro du paquet en cours de transmission.
     * @return Chaîne représentant l'état de l'acquittement (Positif, Négatif ou Aucun).
     */
    public static String tirageAcquittement(int adresseSource, int numeroPaquet) {
        try {
            // Si l'adresse source est multiple de 15, simuler une absence de réponse
            if (adresseSource % 15 == 0) {
                System.out.println("Aucun acquittement reçu pour le paquet " + numeroPaquet);
                return "AucunAcquittement";
            }

            // Tirage aléatoire pour les acquittements
            int tirage = random.nextInt(8);
            if (tirage == numeroPaquet) {
                System.out.println("Acquittement négatif pour le paquet " + numeroPaquet);
                return "AcquittementNegatif";
            }

            System.out.println("Acquittement positif pour le paquet " + numeroPaquet);
            return "AcquittementPositif";
        } catch (Exception e) {
            System.err.println("Erreur lors du tirage d'acquittement pour le paquet " + numeroPaquet + ": " + e.getMessage());
            return "ErreurAcquittement";
        }
    }
}
