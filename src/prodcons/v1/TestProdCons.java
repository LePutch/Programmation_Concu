package prodcons.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class TestProdCons {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.loadFromXML(TestProdCons.class.getClassLoader().getResourceAsStream("prodcons/v1/options.xml"));
        int nProd = Integer.parseInt(props.getProperty("nProd"));
        int nCons = Integer.parseInt(props.getProperty("nCons"));
        int bufSz = Integer.parseInt(props.getProperty("bufSz"));
        int consTime = Integer.parseInt(props.getProperty("consTime"));
        int prodTime = Integer.parseInt(props.getProperty("prodTime"));
        int minProd = Integer.parseInt(props.getProperty("minProd"));
        int maxProd = Integer.parseInt(props.getProperty("maxProd"));

        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();
        ProdConsBuffer buffer = new ProdConsBuffer(bufSz);

        // Create producers
        for (int i = 0; i < nProd; i++) {
            producers.add(new Producer(buffer, minProd, maxProd, prodTime));
        }
        // Create consumers
        for (int i = 0; i < nCons; i++) {
            consumers.add(new Consumer(buffer, consTime));
        }
        // Start threads de manière aléatoire
        while(nProd > 0 || nCons > 0) {
            if (nProd > 0 && Math.random() < 0.5) {
                producers.get(0).start();
                producers.remove(0);
                nProd--;
            }
            if (nCons > 0 && Math.random() < 0.5) {
                consumers.get(0).start();
                consumers.remove(0);
                nCons--;
            }
        }
    }
}
