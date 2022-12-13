package prodcons.v1;

import java.util.Random;

public class Consumer extends Thread {
    int consTime;
    ProdConsBuffer buffer;

    /**
     * Constructeur consumer
     * @param consTime = temps de consommation d'un message
     * @param buffer      = buffer de production - consommation
     */
    public Consumer(ProdConsBuffer buffer, int consTime) {
        this.buffer = buffer;
        this.consTime = consTime;
    }

    /**
     * Methode run du consumer
     */
    public void run() {
        try {
            while (true) {
                Message m = buffer.get();
                Random rand = new Random();
                sleep(rand.nextInt(50) + consTime - 10);
                System.out.println("Le consumer n° " + this.threadId() + " a consommé: " + m.getMsg().toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
