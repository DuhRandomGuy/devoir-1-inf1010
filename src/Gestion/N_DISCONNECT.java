package Gestion;

public interface N_DISCONNECT {
    void req(int repAddr);
    void ind(int repAddr, String raison);
}
