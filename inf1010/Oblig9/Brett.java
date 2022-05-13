public class Brett{
    Rute[][] brettArray;
    int n;
    int raderPerBoks, kolonnerPerBoks;

    Brett(int rader, int kolonner, int n){
	this.n = n;
	brettArray = new Rute[n][n];
	raderPerBoks = rader;
	kolonnerPerBoks = kolonner;
    }
    
    public Rute[][] getArray(){
	return brettArray;
    }

    public Rute getRute(int x, int y){
	return brettArray[x][y];
    }

    public void settInnRute(int rad, int kolonne, Rute r){
	brettArray[rad][kolonne] = r;
    }

    public void printArray(){
	for(int i = 0; i < n; i++){
	    for(int j = 0; j < n; j++){
		System.out.print(brettArray[i][j].getTall() + " ");
	    }
	    System.out.println();
	}
    }
}