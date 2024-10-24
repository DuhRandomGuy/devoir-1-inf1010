package Reseau;

public enum TypePaquet {
        N_CONNECT_REQ(0b00001011, "Demande de connexion"),
        N_CONNECT_RESP(0b00001111, "Réponse à la demande de connexion"),
        N_DATA_REQ(0b00010000, "Requête de transfert de données"),
        N_DISCONNECT(0b00010011, "Demande de déconnexion");

        private final int code;  // La valeur binaire du type de paquet
        private final String description;  // Description du type de paquet

        // Constructeur
        TypePaquet(int code, String description) {
                this.code = code;
                this.description = description;
        }

        // Méthode pour obtenir le code binaire du paquet
        public int getCode() {
                return code;
        }

        // Méthode pour obtenir la description
        public String getDescription() {
                return description;
        }

        @Override
        public String toString() {
                return description + " (code: " + Integer.toBinaryString(code) + ")";
        }
}
