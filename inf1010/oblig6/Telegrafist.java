import krypto.*;

public class Telegrafist extends Thread {
    private final TKMonitor m;
    private final Kanal k;
    
    /**
     * Konstruktor Telegrafist
     * @param m - monitoren en melding skal sendes til
     * @param k - Kanalen telegrafisten lytter til
     */
    public Telegrafist(TKMonitor m, Kanal k){
	this.m = m;
	this.k = k;
    }

    @Override
    public void run() {
	int i = 0;
	String mld;
	while(true) {
	    mld = k.lytt();
	    if(mld != null){
		m.giMelding(new Melding(i, k.hentId(), mld));
	
	    } else {
		return;
	    }
	}
    }
}
