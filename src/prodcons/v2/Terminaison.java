package prodcons.v2;

public class Terminaison {

    public  int nombreProd;
    int nombrePriseMessage;

    public Terminaison(int nombreProd){
        this.nombreProd = nombreProd;
        this.nombrePriseMessage = 0;
    }

    public synchronized void priseMessageEnCours(){
        nombrePriseMessage++;
    }

    public synchronized void priseMessageTermine(){
        nombrePriseMessage--;
        if(nombrePriseMessage == 0 && this.nombreProd == 0){
            System.exit(0);
        }
    }

    public synchronized void prodTermine(){
        nombreProd--;
    }
}
