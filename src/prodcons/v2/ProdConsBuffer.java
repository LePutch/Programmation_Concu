package prodcons.v2;

public class ProdConsBuffer implements IProdConsBuffer {

    private Message[] buffer;
    private int in, out;
    private int sizeBuffer;
    private int nbMsg;

    public ProdConsBuffer(int sizeBuffer) {
        this.sizeBuffer = sizeBuffer;
        buffer = new Message[sizeBuffer];
        in = 0;
        out = 0;
        nbMsg = 0;
    }


    /**
     * Put the message m in the buffer
     **/
    @Override
    public synchronized void put(Message m) throws InterruptedException{
        //On fait la garde pour savoir si le buffer est plein
        while(!(nmsg() > 0)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //On ajoute le message dans le buffer
        buffer[in] = m;
        in = (in + 1) % sizeBuffer;
        nbMsg++;
        notifyAll();
    }

    /**
     * Retrieve a message from the buffer,
     * following a FIFO order (if M1 was put before M2, M1
     * is retrieved before M2)
     **/
    @Override
    public synchronized Message get() throws InterruptedException{
        while(!(totmsg() > 0)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message m = buffer[out];
        out = (out + 1) % sizeBuffer;
        nbMsg--;
        notifyAll();
        return m;
    }

    /**
     * Returns the number of messages currently available in
     * the buffer
     **/
    @Override
    public int nmsg(){
        return sizeBuffer - nbMsg;
    }

    /**
     * Returns the total number of messages that have
     * been put in the buffer since its creation
     **/
    @Override
    public int totmsg(){
        return nbMsg;
    }
}