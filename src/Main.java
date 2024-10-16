import java.util.List;
import java.util.Scanner;
import Reseau.*;
import Transport.ControleurTransport;
import Gestion.GestionConnexion;
import Simulation.SimulationAleatoire;
import Segmentation.Segmentation;
import java.util.Random;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ControleurTransport transport = new ControleurTransport();
    private static ReseauClient reseau = new ReseauClient();
    private static GestionConnexion gestionConnexion = new GestionConnexion();

    public static void main(String[] args) {
        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la ligne

            switch (choix) {
                case 1:
                    etablissementConnexion();
                    break;
                case 2:
                    transfertDonnees();
                    break;
                case 3:
                    liberationConnexion();
                    break;
                case 4:
                    System.out.println("Sortie du programme.");
                    System.exit(0);
                default:
                    System.out.println("Choix non valide, veuillez réessayer.");
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n=== Menu de gestion réseau ===");
        System.out.println("1. Établir une connexion");
        System.out.println("2. Transférer des données");
        System.out.println("3. Libérer une connexion");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option: ");
    }

    // Phase 1 : Établissement de connexion
    private static void etablissementConnexion() {
        System.out.print("Entrez l'adresse source: ");
        int adresseSource = scanner.nextInt();
        System.out.print("Entrez l'adresse destination: ");
        int adresseDestination = scanner.nextInt();

        PaquetReseau demande = transport.demandeConnexion(adresseSource, adresseDestination);
        reseau.traiterDemandeConnexion(demande);

        boolean accepte = SimulationAleatoire.tirageConnexion(adresseSource);
        reseau.repondreConnexion(demande, accepte);
        
        if (accepte) {
            transport.confirmerConnexion(demande);
            gestionConnexion.ajouterConnexion(adresseSource);
        } else {
            System.out.println("Connexion refusée.");
        }
    }

    // Phase 2 : Transfert de données
    private static void transfertDonnees() {
        System.out.print("Entrez l'adresse source: ");
        int adresseSource = scanner.nextInt();

        if (!gestionConnexion.estConnecte(adresseSource)) {
            System.out.println("Aucune connexion active pour cette adresse source.");
            return;
        }

        System.out.print("Entrez l'adresse destination: ");
        int adresseDestination = scanner.nextInt();
        scanner.nextLine();  // Consommer la ligne
        System.out.print("Entrez les données à transférer: ");
        String donnees = scanner.nextLine();

        // Segmentation des données en paquets de 128 octets
        List<PaquetReseau> paquets = Segmentation.segmenterDonnees(adresseSource, adresseDestination, donnees);
        for (PaquetReseau paquet : paquets) {
            reseau.traiterTransfertDonnees(paquet);

            // Tirage aléatoire pour l'acquittement de chaque paquet
            String acquittement = SimulationAleatoire.tirageAcquittement(adresseSource, paquets.indexOf(paquet));
            if (acquittement.equals("AcquittementNegatif")) {
                System.out.println("Erreur: Le paquet n'a pas été reçu correctement.");
            } else if (acquittement.equals("AucunAcquittement")) {
                System.out.println("Aucun acquittement reçu pour ce paquet.");
            }
        }
    }


    // Phase 3 : Libération de connexion
    private static void liberationConnexion() {
        System.out.print("Entrez l'adresse source: ");
        int adresseSource = scanner.nextInt();
        System.out.print("Entrez l'adresse destination: ");
        int adresseDestination = scanner.nextInt();

        transport.libererConnexion(adresseSource, adresseDestination);
        reseau.traiterLibérationConnexion(new PaquetReseau(adresseSource, adresseDestination, TypePaquet.N_DISCONNECT, null));
        gestionConnexion.supprimerConnexion(adresseSource);
    }
}

