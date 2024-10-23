package Gestion;

public interface N_CONNECT {

     void req(int sourceAddr,int destAddr);
     void ind(int sourceAddr,int destAddr);
     void rep(int repAddr);
     void conf(int repAddr);

}
