package Reseau;

public class PaquetReseau {
    private final int adresseSource;
    private final int adresseDestination;
    private String typePaquet;
    private String donnees;

    private int numConnexion;

    public PaquetReseau(int adresseSource, int adresseDestination, String typePaquet, String donnees) {
        this.adresseSource = adresseSource;
        this.adresseDestination = adresseDestination;
        this.typePaquet = typePaquet;
        this.donnees = donnees;
    }

    // methodes pour acceder aux information du paquet
    public int getAdresseSource() {
        return adresseSource;
    }

    public int getAdresseDestination() {
        return adresseDestination;
    }

    public String getTypePaquet() {
        return typePaquet;
    }

    public String getDonnees() {
        return donnees;
    }

    public int getNumConnexion() {
        return numConnexion;
    }

    public void setNumConnexion(int numero){
        this.numConnexion = numero;
    }
}
