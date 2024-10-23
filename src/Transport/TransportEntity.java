package Transport;

import Gestion.Connection;
import Reseau.NetworkEntity;
import Reseau.PaquetReseau;

import java.io.*;
import java.util.*;

public class TransportEntity {
    private File sLecFile;
    private File sEcrFile;
    public NetworkEntity networkEntity;
    private Map<Integer, Connection> tableConnections;
    private Random random;

    public TransportEntity(String s_lec,String s_ecr,NetworkEntity networkEntity){
        this.sEcrFile = new File(s_lec);
        this.sEcrFile = new File(s_ecr);
        this.networkEntity = networkEntity;
        this.tableConnections = new HashMap<>();

    }

    public void start(){
        // Lecture du fichier s_lec ligne par ligne ( chaque demande )
        try(BufferedReader reader = new BufferedReader(new FileReader(sLecFile))){
            String line;
            while ((line = reader.readLine()) != null){
                handleRequest(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleRequest(String request) {
        // Traitement d'une demande de connexion provenant du fichier S_lec
        // Exemple : "N_CONNECT.req 123" (où 123 est l'identifiant de connexion)
        String[] parts = request.split(" ");
        String primitif = parts[0];
        int connectionId = Integer.parseInt(parts[1]);

        if(primitif.equals("N_CONNECT.req")){
            // Gestion de la demande
            Connection connexion = new Connection(connectionId) {
            };
            tableConnections.put(connectionId, connexion);
// Tirage aléatoire pour simuler la réponse de la connexion
            int sourceAddress = random.nextInt(255);
            int destinationAddress = random.nextInt(255);
            // Demande de connexion au niveau de la couche reseau
            networkEntity.req(sourceAddress,destinationAddress);
        }
    }

    // Méthode pour recevoir les réponses du réseau
    public void receiveNetworkResponse(int connectionId, String reponse){
        // Traiter la réponse réseau et mettre à jour l'état des connexions
        Connection conn = tableConnections.get(connectionId);
        if(reponse.equals(("N_CONNECT.conf"))){
            conn.setEstablished(true);
            // Écrire dans S_ecr pour indiquer que la connexion est établie
            writeToFile(sEcrFile, "Connexion établie pour la demande " + connectionId);
        } else if (reponse.equals("N_DISCONNECT.ind")) {
            conn.setEstablished(false);
            // Libérer les ressources et écrire dans S_ecr
            tableConnections.remove(connectionId);
            writeToFile(sEcrFile, "Connexion refusée pour la demande " + connectionId);
        }

    }


    private void writeToFile(File fichier, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
