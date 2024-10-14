package Reseau;

public class ReseauClient {

    public void traiterDemandeConnexion(PaquetReseau paquet) {
        System.out.println("Reseau: Reçu demande de connexion de " + paquet.getAdresseSource());
    }

    public void repondreConnexion(PaquetReseau paquet, boolean accepter) {
        if (accepter) {
            System.out.println("Reseau: Connexion acceptée");
        } else {
            System.out.println("Reseau: Connexion refusée");
        }
    }

    public void traiterTransfertDonnees(PaquetReseau paquet) {
        System.out.println("Reseau: Données reçues : " + paquet.getDonnees());
    }

    public void traiterLibérationConnexion(PaquetReseau paquet) {
        System.out.println("Reseau: Libération de la connexion de " + paquet.getAdresseSource());
    }
}

