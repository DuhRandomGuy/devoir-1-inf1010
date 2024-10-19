package Gestion;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class GestionFichier {

    // Méthode pour écrire des données dans un fichier
    public static void ecrireDansFichier(String nomFichier, String donnees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier, true))) { // true pour ajouter à la fin
            writer.write(donnees);
            writer.newLine();
            System.out.println("Données écrites dans " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier " + nomFichier + ": " + e.getMessage());
        }
    }

    // Méthode pour lire des données d'un fichier
    public static String lireDepuisFichier(String nomFichier) {
        StringBuilder contenu = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                contenu.append(ligne).append("\n");
            }
            System.out.println("Données lues depuis " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + nomFichier + ": " + e.getMessage());
        }
        return contenu.toString();
    }
    
}

