package Reseau;

public class PaquetReseau {
    private int adresseSource;
    private int adresseDestination;
    private TypePaquet typePaquet;
    private String donnees;

    public PaquetReseau(int adresseSource, int adresseDestination, TypePaquet typePaquet, String donnees) {
        this.adresseSource = adresseSource;
        this.adresseDestination = adresseDestination;
        this.typePaquet = typePaquet;
        this.donnees = donnees;
    }

    public int getAdresseSource() {
        return adresseSource;
    }

    public int getAdresseDestination() {
        return adresseDestination;
    }

    public TypePaquet getTypePaquet() {
        return typePaquet;
    }

    public String getDonnees() {
        return donnees;
    }

    public void setTypePaquet(TypePaquet typePaquet) {
        this.typePaquet = typePaquet;
    }
}
