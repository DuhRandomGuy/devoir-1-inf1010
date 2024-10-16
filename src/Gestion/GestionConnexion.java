package Gestion;
import java.util.HashMap;
import java.util.Map;

public class GestionConnexion {

    private Map<Integer, Boolean> connexionsActives = new HashMap<>(); // Associer chaque adresse source à un état de connexion

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
}
