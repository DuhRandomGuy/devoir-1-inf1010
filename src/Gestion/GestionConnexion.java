package Gestion;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GestionConnexion {

    private Map<Integer, Boolean> connexionsActives = new HashMap<>(); // Associer chaque adresse source à un état de connexion
    private static Random random = new Random();

    public void ajouterConnexion(int adresseSource) {
        connexionsActives.put(adresseSource, true);
        System.out.println("Connexion ajoutée pour l'adresse source " + adresseSource);
    }

    public void supprimerConnexion(int adresseSource) {
        if (connexionsActives.containsKey(adresseSource)) {
            connexionsActives.remove(adresseSource);
            System.out.println("Connexion supprimée pour l'adresse source " + adresseSource);
        } else {
            System.out.println("Aucune connexion active pour l'adresse source " + adresseSource);
        }
    }

    public boolean estConnecte(int adresseSource) {
        return connexionsActives.getOrDefault(adresseSource, false);
    }

    /**
     * Génère deux adresses distinctes aléatoirement entre 0 et 254.
     * @return Un tableau d'entiers où la première valeur est l'adresse source et la deuxième est l'adresse destination.
     */
    public static int[] genererAdressesDistinctes() {
        int adresseSource = random.nextInt(255); // Génère une adresse source entre 0 et 254
        int adresseDestination;
        do {
            adresseDestination = random.nextInt(255); // Génère une adresse destination distincte de l'adresse source
        } while (adresseDestination == adresseSource);
        return new int[] { adresseSource, adresseDestination };
    }
}
