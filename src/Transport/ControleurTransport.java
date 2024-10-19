package Transport;

import Reseau.PaquetReseau;

public class ControleurTransport {

    // N_CONNECT.req : Transport demande une connexion via le réseau
    public PaquetReseau demandeConnexion(int adresseSource, int adresseDestination) {
        System.out.println("Transport: Demande de connexion de " + adresseSource + " vers " + adresseDestination);
        return new PaquetReseau(adresseSource, adresseDestination, "N_CONNECT.req", null);
    }

    // N_CONNECT.conf : Transport reçoit la confirmation de connexion
    public void confirmerConnexion(PaquetReseau paquet) {
        System.out.println("Transport: Connexion confirmée pour " + paquet.getAdresseSource() + " vers " + paquet.getAdresseDestination());
    }

    // N_DATA.req : Transport envoie des données à la couche réseau
    public PaquetReseau envoyerDonnees(int adresseSource, int adresseDestination, String donnees) {
        System.out.println("Transport: Envoi des données: " + donnees);
        return new PaquetReseau(adresseSource, adresseDestination, "N_DATA.req", donnees);
    }

    // N_DISCONNECT.req : Transport demande la libération de la connexion via le réseau
    public void libererConnexion(int adresseSource, int adresseDestination) {
        System.out.println("Transport: Libération de connexion de " + adresseSource + " vers " + adresseDestination);
    }
}
