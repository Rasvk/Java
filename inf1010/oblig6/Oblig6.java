import krypto.*;

public class Oblig6 {

    public static void main(String[] args) { 
	int antallTelegrafister = Integer.parseInt(args[0]);
	Operasjonssentral ops = new Operasjonssentral(antallTelegrafister);
	TKMonitor mon1 = new TKMonitor();
	TKMonitor mon2 = new TKMonitor();
	Kanal[] kanaler = ops.hentKanalArray();
	Telegrafist[] telegrafister = new Telegrafist[antallTelegrafister];
	Kryptograf[] kryptografer = new Kryptograf[antallTelegrafister*3];
	for(int i = 0; i < telegrafister.length; i++) {
	    telegrafister[i] = new Telegrafist(mon1, kanaler[i]);
	}
    
	for(int i = 0; i < kryptografer.length; i++) {
	    kryptografer[i] = new Kryptograf(mon1, mon2);
	}
    
	for(int i = 0; i < antallTelegrafister; i++) {
	    telegrafister[i].start();
	}
    
	for(int i = 0; i < kryptografer.length; i++){
	    kryptografer[i].start();
	}

    }

}
