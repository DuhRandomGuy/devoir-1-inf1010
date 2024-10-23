package Gestion;

public class Connection {
        public enum ConnectionState {
            EN_ATTENTE,  // En attente de confirmation d'établissement
            ETABLIE,     // Connexion établie
            LIBEREE      // Connexion libérée ou refusée
        }

        private int connectionId;             // Identifiant unique de la connexion
        private int sourceAddress;            // Adresse de la station source
        private int destinationAddress;       // Adresse de la station destination
        private ConnectionState state;        // État de la connexion
        private boolean established;          // Indique si la connexion est établie
        private int networkConnectionNumber;  // Numéro de connexion attribué par le réseau

        // Constructeur
        public Connection(int connectionId) {
            this.connectionId = connectionId;
            this.state = ConnectionState.EN_ATTENTE; // Par défaut, la connexion est en attente
            this.established = false;                // La connexion n'est pas encore établie
        }

        // Getters et setters
        public int getConnectionId() {
            return connectionId;
        }

        public int getSourceAddress() {
            return sourceAddress;
        }

        public void setSourceAddress(int sourceAddress) {
            this.sourceAddress = sourceAddress;
        }

        public int getDestinationAddress() {
            return destinationAddress;
        }

        public void setDestinationAddress(int destinationAddress) {
            this.destinationAddress = destinationAddress;
        }

        public ConnectionState getState() {
            return state;
        }

        public void setState(ConnectionState state) {
            this.state = state;
        }

        public boolean isEstablished() {
            return established;
        }

        public void setEstablished(boolean established) {
            this.established = established;
            if (established) {
                this.state = ConnectionState.ETABLIE; // Si établi, changer l'état
            }
        }

        public int getNetworkConnectionNumber() {
            return networkConnectionNumber;
        }

        public void setNetworkConnectionNumber(int networkConnectionNumber) {
            this.networkConnectionNumber = networkConnectionNumber;
        }

        // Méthode pour libérer la connexion
        public void release() {
            this.state = ConnectionState.LIBEREE;
            this.established = false;
        }

        // Affichage des détails de la connexion
        @Override
        public String toString() {
            return "Connection ID: " + connectionId + ", Source: " + sourceAddress +
                    ", Destination: " + destinationAddress + ", State: " + state;
        }
    }


