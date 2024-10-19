/*****************************************************************************************************************************************************
 **********************************        Auteurs du travail:          ******************************************************************************
 **********************************                                     ******************************************************************************
 **********************************     1. ISAAC KAMPANGA MWEMBIA       ******************************************************************************
 **********************************     2. BEN YAHIA AYMEN              ******************************************************************************
 **********************************     3. MELCHISEDECK MVITA           ******************************************************************************
 **********************************     4. BENI DIKITELE                ******************************************************************************
 **********************************     5.                              ******************************************************************************
 *****************************************************************************************************************************************************
 ***************************************************************************************************************************************************** 
 *****************************************************************************************************************************************************/

 import java.util.List;
 import java.util.Scanner;
 
 import Gestion.GestionConnexion;
 import Gestion.GestionFichier;
 import Reseau.PaquetReseau;
 import Transport.ControleurTransport;
 import Reseau.ReseauClient;
 import Segmentation.Segmentation;
 
 public class Main {
 
     // Scanner pour lire les entrées utilisateur
     private static Scanner scanner = new Scanner(System.in);
 
     // Initialisation des objets pour gérer les couches transport et réseau
     private static ControleurTransport transport = new ControleurTransport();
     private static ReseauClient reseau = new ReseauClient();
     private static GestionConnexion gestionConnexion = new GestionConnexion();
 
     public static void main(String[] args) {
         while (true) {
             // Afficher le menu et capturer le choix utilisateur
             afficherMenu();
             int choix = scanner.nextInt();
             scanner.nextLine();  // Consommer la ligne vide après l'entrée
 
             // Gérer les choix selon l'option sélectionnée
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
                     afficherConnexionsEnCours();  
                     break;
                 case 5:
                     afficherHistorique();  
                     break;
                 case 6:
                     reinitialiserConnexions();  
                     break;
                 case 7:
                     quitterProgramme();  
                     break;
                 default:
                     System.out.println("Choix non valide, veuillez réessayer.");
             }
         }
     }
 
     // Méthode pour afficher le menu des options
     private static void afficherMenu() {
         System.out.println("\n=== Menu de gestion réseau ===");
         System.out.println("1. Établir une connexion");
         System.out.println("2. Transférer des données");
         System.out.println("3. Libérer une connexion");
         System.out.println("4. Afficher les connexions en cours");
         System.out.println("5. Afficher l'historique des actions");
         System.out.println("6. Réinitialiser toutes les connexions");
         System.out.println("7. Quitter");
         System.out.print("Choisissez une option: ");
     }
 
     /*****************************************************************************************************************************************************/
     /*****************************************  PHASE 1:  ETABLISSEMENT DE CONNEXION   *******************************************************************/
     
     private static void etablissementConnexion() {
         // N_CONNECT.req : Transport demande une connexion via le réseau
         System.out.print("Entrez l'adresse source: ");
         int adresseSource = scanner.nextInt();
         System.out.print("Entrez l'adresse destination: ");
         int adresseDestination = scanner.nextInt();
 
         // Vérification de la plage des adresses source et destination
         if (!estAdresseSourceValide(adresseSource)) {
             System.out.println("Connexion refusée : Adresse source hors de la plage autorisée.");
             return;
         }
 
         if (!estAdresseDestinationValide(adresseDestination)) {
             System.out.println("Connexion refusée : Adresse destination hors de la plage autorisée.");
             return;
         }
 
         // N_CONNECT.req : La couche transport envoie une demande de connexion
         PaquetReseau demande = transport.demandeConnexion(adresseSource, adresseDestination);
 
         // N_CONNECT.ind : La couche réseau reçoit et traite la demande de connexion
         reseau.traiterDemandeConnexion(demande);
 
         // Écrire dans un fichier que la demande de connexion a été faite
         GestionFichier.ecrireDansFichier("S_ecr.txt", "Connexion demandée de " + adresseSource + " vers " + adresseDestination);
 
         // accepeter la connexion si les plages d'adresses sont respectés
         boolean accepte = true;
 
         // N_CONNECT.resp : Réseau envoie une réponse à la demande de connexion (acceptée ou refusée)
         reseau.repondreConnexion(demande, accepte);
 
         if (accepte) {
             // N_CONNECT.conf : La couche transport reçoit la confirmation de connexion
             transport.confirmerConnexion(demande);
             gestionConnexion.ajouterConnexion(adresseSource);
             // Mise à jour de l'état de la connexion à "Établie" après confirmation
             gestionConnexion.mettreAJourEtat(adresseSource, "Établie");
             GestionFichier.ecrireDansFichier("S_ecr.txt", "Connexion acceptée.");
         } else {
             // Si la connexion est refusée, l'indiquer dans le fichier
             System.out.println("Connexion refusée.");
             GestionFichier.ecrireDansFichier("S_ecr.txt", "Connexion refusée.");
         }
     }
 
     // Méthode pour vérifier si l'adresse source est dans la plage valide
     private static boolean estAdresseSourceValide(int adresseSource) {
         return adresseSource >= 500 && adresseSource <= 1000; // Plage de 500 à 1000
     }
 
     // Méthode pour vérifier si l'adresse destination est dans la plage valide
     private static boolean estAdresseDestinationValide(int adresseDestination) {
         return adresseDestination >= 6000 && adresseDestination <= 7000; // Plage de 6000 à 7000
     }
 
     /*****************************************************************************************************************************************************/
     /*****************************************  PHASE 2:  TRANSFERT DE DONNÉES   *************************************************************************/
     
     private static void transfertDonnees() {
         // N_DATA.req : Transport demande au réseau de transmettre des données
         System.out.print("Entrez l'adresse source: ");
         int adresseSource = scanner.nextInt();
         System.out.print("Entrez l'adresse destination: ");
         int adresseDestination = scanner.nextInt();
         scanner.nextLine();  // Consommer la ligne
         System.out.print("Entrez les données à transférer: ");
         String donnees = scanner.nextLine();
 
         // Segmentation des données en paquets de 128 octets
         List<PaquetReseau> paquets = Segmentation.segmenterDonnees(adresseSource, adresseDestination, donnees);
 
         for (PaquetReseau paquet : paquets) {
             // N_DATA.req : Transport envoie un paquet de données à la couche réseau
             reseau.traiterTransfertDonnees(paquet);
 
             // \pour accepter tout les paquets
             String acquittement = "AcquittementPositif"; 
             if (acquittement.equals("AcquittementNegatif")) {
                 System.out.println("Erreur: Le paquet n'a pas été reçu correctement.");
             } else if (acquittement.equals("AucunAcquittement")) {
                 System.out.println("Aucun acquittement reçu pour ce paquet.");
             }
         }
     }
 
     /*****************************************************************************************************************************************************/
     /*****************************************  PHASE 3:  LIBERATION DE CONNEXION   **********************************************************************/
     
     private static void liberationConnexion() {
         // N_DISCONNECT.req : Transport demande au réseau de libérer la connexion
         System.out.print("Entrez l'adresse source: ");
         int adresseSource = scanner.nextInt();
         System.out.print("Entrez l'adresse destination: ");
         int adresseDestination = scanner.nextInt();
 
         // N_DISCONNECT.req : La couche transport demande la libération de la connexion
         transport.libererConnexion(adresseSource, adresseDestination);
 
         // N_DISCONNECT.ind : La couche réseau reçoit la demande de libération et libère la connexion
         reseau.traiterLibérationConnexion(new PaquetReseau(adresseSource, adresseDestination, "N_DISCONNECT", null));
 
         // Supprimer la connexion des connexions actives
         gestionConnexion.supprimerConnexion(adresseSource);
     }
     /*****************************************************************************************************************************************************/
     
     // Afficher les connexions en cours
     private static void afficherConnexionsEnCours() {
         gestionConnexion.afficherConnexionsEnCours();
     }
 
     // Afficher l'historique des actions en lisant le fichier S_ecr.txt
     private static void afficherHistorique() {
         String contenu = GestionFichier.lireDepuisFichier("S_ecr.txt");
         System.out.println("Historique des actions :\n" + contenu);
     }
 
     // Réinitialiser toutes les connexions en cours
     private static void reinitialiserConnexions() {
         gestionConnexion.reinitialiser();
         System.out.println("Toutes les connexions ont été réinitialisées.");
     }
 
     // Quitter le programme avec confirmation
     private static void quitterProgramme() {
         System.out.print("Êtes-vous sûr de vouloir quitter ? (oui/non): ");
         String confirmation = scanner.nextLine();
         if (confirmation.equalsIgnoreCase("oui")) {
             System.out.println("Sortie du programme.");
             System.exit(0);
         } else {
             System.out.println("Retour au menu.");
         }
     }
 }
 