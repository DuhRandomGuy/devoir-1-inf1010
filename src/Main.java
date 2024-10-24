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
        // Générer des adresses aléatoires distinctes
        int[] adresses = GestionConnexion.genererAdressesDistinctes();
        adresseSource = adresses[0];
        adresseDestination = adresses[1];

        // Demande de connexion
        PaquetReseau demande = transport.demandeConnexion(adresseSource, adresseDestination);
        reseau.traiterDemandeConnexion(demande);

        // Tirage aléatoire pour simuler l'acceptation ou le refus de la connexion
        boolean accepte = SimulationAleatoire.tirageConnexion(adresseSource);
        reseau.repondreConnexion(demande, accepte);

        if (accepte) {
            // Ajouter la connexion dans la gestion des connexions
            gestionConnexion.ajouterConnexion(adresseSource);
            transport.confirmerConnexion(demande);
            System.out.println("Connexion acceptée.");
        } else {
            System.out.println("Connexion refusée.");
        }
    }

    // Phase 2 : Transfert de données
    private static void transfertDonnees() throws IOException {
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
            reseau.traiterTransfertDonnees(paquet);

            // Simuler les acquittements depuis L_lec
            String acquittement = reseau.lireDepuisL_lec();
            if (acquittement == null) {
                System.out.println("Erreur : Aucun acquittement reçu.");
            } else if (acquittement.equals("AcquittementNegatif")) {
                System.out.println("Erreur : Le paquet n'a pas été reçu correctement.");
            } else {
                System.out.println("Acquittement positif reçu.");
            }
        }
    }

    // Phase 3 : Libération de connexion
    private static void liberationConnexion() throws IOException {
        if (!gestionConnexion.estConnecte(adresseSource)) {
            System.out.println("Aucune connexion active pour cette adresse source.");
            return;
        }

        // Libérer la connexion
        PaquetReseau liberation = transport.libererConnexion(adresseSource, adresseDestination);
        reseau.traiterLibérationConnexion(liberation);

        // Supprimer la connexion de la gestion des connexions
        gestionConnexion.supprimerConnexion(adresseSource);
    }
}
