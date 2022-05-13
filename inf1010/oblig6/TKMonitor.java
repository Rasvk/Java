import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
public class TKMonitor {
    private final Lock laas = new ReentrantLock();
    private final Condition notEmpty = laas.newCondition();
    private final Condition notFull = laas.newCondition();
    private ArrayList<Melding> m = new ArrayList<Melding>();
    private int count;
    private final int FULL = 10;
    public void giMelding(Melding mld) {
	laas.lock();
	try {
	    while(count == FULL)
		notFull.await();
	    
	    m.add(mld);
	    count++;
	    notEmpty.signal();
	} catch(InterruptedException e) {
	    e.printStackTrace();
	    System.exit(0);
	} finally {
	    laas.unlock();
	}
    }
       
    public Melding hentMelding() {
	laas.lock();
	Melding mld = null;
	try {
	    while(count == 0)
		notEmpty.await();

	    mld = m.remove(0);
	    count--;
	    notFull.signal();
	} catch(InterruptedException e) {
	    e.printStackTrace();
	    System.exit(0);
	} finally {
	    laas.unlock();
	    return mld;
	}
    }
    
}
