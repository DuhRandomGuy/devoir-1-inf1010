package Reseau;

public class PaquetReseau {
    private int numeroConnexion;       // Numéro de connexion (8 bits)
    private TypePaquet typePaquet;     // Type de paquet (utilise l'enum TypePaquet)
    private int adresseSource;         // Adresse source (8 bits)
    private int adresseDestination;    // Adresse destination (8 bits)
    private String donnees;            // Données utiles (segment provenant de S_lec.txt)
    private int raison;                // Champ raison (dans les paquets d'indication de libération, 8 bits)
    private int numeroSequence;        // Numéro de séquence pour les paquets segmentés (3 bits)
    private boolean bitM;              // Indicateur M pour la segmentation

    // Constructeur du paquet réseau
    public PaquetReseau(int numeroConnexion, TypePaquet typePaquet, int adresseSource, int adresseDestination, String donnees, int raison, int numeroSequence, boolean bitM) {
        this.numeroConnexion = numeroConnexion;
        this.typePaquet = typePaquet;
        this.adresseSource = adresseSource;
        this.adresseDestination = adresseDestination;
        this.donnees = donnees;
        this.raison = raison;
        this.numeroSequence = numeroSequence;
        this.bitM = bitM;
    }

    // Getters et Setters pour chaque champ
    public int getNumeroConnexion() {
        return numeroConnexion;
    }

    public TypePaquet getTypePaquet() {
        return typePaquet;
    }

    public int getAdresseSource() {
        return adresseSource;
    }

    public int getAdresseDestination() {
        return adresseDestination;
    }

    public String getDonnees() {
        return donnees;
    }

    public int getRaison() {
        return raison;
    }

    public int getNumeroSequence() {
        return numeroSequence;
    }

    public boolean isBitM() {
        return bitM;
    }

    public void setTypePaquet(TypePaquet typePaquet) {
        this.typePaquet = typePaquet;
    }

    // Méthode pour afficher le paquet
    public String afficherPaquet() {
        return "Numéro de Connexion: " + numeroConnexion +
                ", Type de Paquet: " + typePaquet +
                ", Adresse Source: " + adresseSource +
                ", Adresse Destination: " + adresseDestination +
                ", Données: " + (donnees != null ? donnees : "N/A") +
                ", Raison: " + (raison != 0 ? raison : "N/A") +
                ", Numéro de Séquence: " + numeroSequence +
                ", Bit M: " + (bitM ? "1" : "0");
    }
}
