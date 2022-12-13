package prodcons.v2;

import java.util.Random;

public class Consumer extends Thread {
    int consTime;
    ProdConsBuffer buffer;
    Terminaison terminaison;

    /**
     * Constructeur consumer
     * @param consTime = temps de consommation d'un message
     * @param buffer      = buffer de production - consommation
     */
    public Consumer(ProdConsBuffer buffer, int consTime, Terminaison terminaison) {
        this.buffer = buffer;
        this.consTime = consTime;
        this.terminaison = terminaison;
    }

    /**
     * Methode run du consumer
     */
    public void run() {
        try {
            while (true) {
                Message m = buffer.get();
                Random rand = new Random();
                System.out.println("DEBUT du consumer n° " + this.threadId() + " qui consomme: " + m.getMsg().toString());
                terminaison.priseMessageEnCours();
                sleep(rand.nextInt(50) + consTime - 10);
                System.out.println("FIN du consumer n° " + this.threadId() + " qui a consommé: " + m.getMsg().toString());
                terminaison.priseMessageTermine();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
