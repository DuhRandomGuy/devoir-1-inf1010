import java.util.List;
import java.util.Scanner;
import Reseau.*;
import Transport.ControleurTransport;
import Gestion.GestionConnexion;
import Simulation.SimulationAleatoire;
import Segmentation.Segmentation;
import java.io.IOException;

public class Main {

    // Scanner pour interagir avec l'utilisateur
    private static Scanner scanner = new Scanner(System.in);
    // Gestion des couches
    private static ControleurTransport transport = new ControleurTransport();
    private static ReseauClient reseau = new ReseauClient();
    private static GestionConnexion gestionConnexion = new GestionConnexion();

    // Variables globales pour stocker les adresses source et destination
    private static int adresseSource;
    private static int adresseDestination;

    public static void main(String[] args) throws IOException {
        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la ligne

            switch (choix) {
                case 1:
                    etablissementConnexion(); // Établir la connexion et stocker les adresses
                    break;
                case 2:
                    transfertDonnees(); // Utiliser les mêmes adresses source et destination
                    break;
                case 3:
                    liberationConnexion(); // Utiliser les mêmes adresses source et destination
                    break;
                case 4:
                    System.out.println("Sortie du programme.");
                    System.exit(0);
                default:
                    System.out.println("Choix non valide, veuillez réessayer.");
            }
        }
    }

    // Affichage du menu
    private static void afficherMenu() {
        System.out.println("\n=== Menu de gestion réseau ===");
        System.out.println("1. Établir une connexion");
        System.out.println("2. Transférer des données");
        System.out.println("3. Libérer une connexion");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option: ");
    }

    // Phase 1 : Établissement de connexion
    private static void etablissementConnexion() throws IOException {
        // Utilisation de la méthode dans GestionConnexion pour générer des adresses aléatoires distinctes
        int[] adresses = GestionConnexion.genererAdressesDistinctes();
        adresseSource = adresses[0];  // Stocker l'adresse source globalement
        adresseDestination = adresses[1];  // Stocker l'adresse destination globalement

        // Phase 1 : Demande de connexion de la couche transport vers la couche réseau
        PaquetReseau demande = transport.demandeConnexion(adresseSource, adresseDestination);
        reseau.traiterDemandeConnexion(demande);

        // Tirage aléatoire pour simuler l'acceptation ou le refus de la connexion par la station distante
        boolean accepte = SimulationAleatoire.tirageConnexion(adresseSource);
        reseau.repondreConnexion(demande, accepte);

        if (accepte) {
            // Confirmer la connexion et l'ajouter dans la gestion des connexions
            transport.confirmerConnexion(demande);
            gestionConnexion.ajouterConnexion(adresseSource);
        } else {
            // Si la connexion est refusée, afficher un message
            System.out.println("Connexion refusée.");
        }
    }

    // Phase 2 : Transfert de données
    private static void transfertDonnees() throws IOException {
        // Vérification si une connexion active existe pour l'adresse source
        if (!gestionConnexion.estConnecte(adresseSource)) {
            System.out.println("Aucune connexion active pour cette adresse source.");
            return;
        }

        // Lire les données à transférer depuis S_lec (simulant la couche supérieure)
        String donnees = transport.lireDepuisS_lec();
        if (donnees == null || donnees.isEmpty()) {
            System.out.println("Aucune donnée à transférer.");
            return;
        }

        // Segmentation des données en paquets de 128 octets
        List<PaquetReseau> paquets = Segmentation.segmenterDonnees(adresseSource, adresseDestination, donnees);
        for (PaquetReseau paquet : paquets) {
            // Transfert des données vers la couche réseau
            reseau.traiterTransfertDonnees(paquet);

            // Tirage aléatoire pour simuler l'acquittement des paquets depuis L_lec (simulant la couche liaison)
            String acquittement = reseau.lireDepuisL_lec();
            if (acquittement.equals("AcquittementNegatif")) {
                System.out.println("Erreur: Le paquet n'a pas été reçu correctement.");
            } else if (acquittement.equals("AucunAcquittement")) {
                System.out.println("Aucun acquittement reçu pour ce paquet.");
            } else {
                System.out.println("Acquittement positif reçu pour ce paquet.");
            }
        }
    }

    // Phase 3 : Libération de connexion
    private static void liberationConnexion() throws IOException {
        // Utilisation des adresses source et destination globalement stockées
        transport.libererConnexion(adresseSource, adresseDestination);
        // Transmettre à la couche réseau la libération de la connexion
        reseau.traiterLibérationConnexion(new PaquetReseau(adresseSource, adresseDestination, TypePaquet.N_DISCONNECT, null));
        // Supprimer la connexion de la gestion des connexions
        gestionConnexion.supprimerConnexion(adresseSource);
    }
}
