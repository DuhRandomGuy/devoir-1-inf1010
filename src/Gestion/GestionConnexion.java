package Gestion;
import java.util.HashMap;
import java.util.Map;

public class GestionConnexion {

    // Table pour stocker l'état des connexions
    private Map<Integer, String> tableConnexions;

    public GestionConnexion() {
        tableConnexions = new HashMap<>();
    }

    // Ajouter une nouvelle connexion dans l'état "En attente"
    // (N_CONNECT.req envoyé, N_CONNECT.conf en attente)
    public void ajouterConnexion(int idConnexion) {
        tableConnexions.put(idConnexion, "En attente de confirmation");
        System.out.println("Nouvelle connexion ajoutée avec l'identifiant: " + idConnexion);
    }

    // Mettre à jour l'état d'une connexion apres N_CONNECT.Conf
    public void mettreAJourEtat(int idConnexion, String nouvelEtat) {
        if (tableConnexions.containsKey(idConnexion)) {
            tableConnexions.put(idConnexion, nouvelEtat);
            System.out.println("Connexion " + idConnexion + " mise à jour : " + nouvelEtat);
        } else {
            System.out.println("Erreur : Connexion non trouvée.");
        }
    }
    

    // Supprimer une connexion après N_DISCONNECT.req et N_DISCONNECT.ind
    public void supprimerConnexion(int idConnexion) {
        if (tableConnexions.containsKey(idConnexion)) {
            tableConnexions.remove(idConnexion);
            System.out.println("Connexion " + idConnexion + " supprimée.");
        }
    }

    // Afficher les connexions en cours
    public void afficherConnexionsEnCours() {
        if (tableConnexions.isEmpty()) {
            System.out.println("Aucune connexion en cours.");
        } else {
            System.out.println("Connexions en cours :");
            for (Map.Entry<Integer, String> connexion : tableConnexions.entrySet()) {
                System.out.println("Connexion ID : " + connexion.getKey() + " | État : " + connexion.getValue());
            }
        }
    }

    // Vérifier l'état d'une connexion
    public String obtenirEtatConnexion(int idConnexion) {
        return tableConnexions.get(idConnexion);
    }

    // Reinitialiser toutes les connexions
    public void reinitialiser() {
        tableConnexions.clear();
        System.out.println("Réinitialisation des connexions.");
    }
    
}

