package Reseau;

public class ReseauClient {

    // N_CONNECT.ind : Réseau reçoit la demande de connexion et la traite
    public void traiterDemandeConnexion(PaquetReseau paquet) {
        System.out.println("Reseau: Reçu demande de connexion de " + paquet.getAdresseSource());
    }

    // N_CONNECT.resp : Réseau envoie une réponse (accepter ou refuser la connexion)
    public void repondreConnexion(PaquetReseau paquet, boolean accepter) {
        if (accepter) {
            System.out.println("Reseau: Connexion acceptée");
        } else {
            System.out.println("Reseau: Connexion refusée");
        }
    }

    // N_DATA.ind : Réseau reçoit des données et les traite
    public void traiterTransfertDonnees(PaquetReseau paquet) {
        System.out.println("Reseau: Données reçues : " + paquet.getDonnees());
    }

    // N_DISCONNECT.ind : Réseau reçoit la demande de libération de connexion
    public void traiterLibérationConnexion(PaquetReseau paquet) {
        System.out.println("Reseau: Libération de la connexion de " + paquet.getAdresseSource());
    }
}

