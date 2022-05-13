import krypto.*;

public class Kryptograf extends Thread {
    private final TKMonitor m;
    private final TKMonitor o;
    
    public Kryptograf(TKMonitor m, TKMonitor o){
	this.m = m;
	this.o = o;
    }
    
    @Override
    public void run() {
	Melding mld;
	while(true) {
	    mld= m.hentMelding();
	    if(mld != null) {
		String tmp = Kryptografi.dekrypter(mld.getMelding());
		mld.setMelding(tmp);
		//o.giMelding(mld);
		System.out.println(tmp);
	    } else {
		return;
	    }
	   
	}
    }
}
