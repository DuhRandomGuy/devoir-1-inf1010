package Transport;

import Reseau.PaquetReseau;

public class ControleurTransport {

    public PaquetReseau demandeConnexion(int adresseSource, int adresseDestination) {
        System.out.println("Transport: Demande de connexion de " + adresseSource + " vers " + adresseDestination);
        return new PaquetReseau(adresseSource, adresseDestination, "N_CONNECT.req", null);
    }

    public void confirmerConnexion(PaquetReseau paquet) {
        System.out.println("Transport: Connexion confirmée pour " + paquet.getAdresseSource() + " vers " + paquet.getAdresseDestination());
    }

    public PaquetReseau envoyerDonnees(int adresseSource, int adresseDestination, String donnees) {
        System.out.println("Transport: Envoi des données: " + donnees);
        return new PaquetReseau(adresseSource, adresseDestination, "N_DATA.req", donnees);
    }

    public void libererConnexion(int adresseSource, int adresseDestination) {
        System.out.println("Transport: Libération de connexion de " + adresseSource + " vers " + adresseDestination);
    }
}
