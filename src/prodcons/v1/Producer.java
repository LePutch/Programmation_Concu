package prodcons.v1;

import java.util.Random;

public class Producer extends Thread{
    ProdConsBuffer buffer;
    int minProd, maxProd;
    int prodTime;
    int nbMsg;


    /**
     * Constructeur producer
     * @param prodTime = temps de production d'un message
     * @param minProd  = nombre minimum de messages produits
     * @param maxProd  = nombre maximum de messages produits
     * @param buffer      = buffer de production - consommation
     */
    public Producer(ProdConsBuffer buffer, int minProd, int maxProd, int prodTime){
        this.buffer = buffer;
        this.minProd = minProd;
        this.maxProd = maxProd;
        this.prodTime = prodTime;
        Random rand = new Random();
        nbMsg = rand.nextInt(maxProd - minProd) + minProd;
    }

    /**
     * Methode run du producer
     */
    public void run() {
        try {
            for (int i = 0; i < nbMsg; i++) {
                Message m = new Message("Message " + (i+1) + "/" + nbMsg + " du producer " + this.threadId());
                buffer.put(m);
                Random rand = new Random();
                sleep(rand.nextInt(50) + prodTime - 10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Le thread " + this.threadId() + " s'est terminé"+"\n");
    }
}
