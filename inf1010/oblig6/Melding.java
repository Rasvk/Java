public class Melding {
    private int sekvensnr;
    private int kanalId;
    private String melding;

    public Melding(int sekvensnr, int kanalId, String melding){
	this.sekvensnr = sekvensnr;
	this.kanalId = kanalId;
	this.melding = melding;
    }
    
    public String getMelding(){
	return melding;
    }
    
    public int getSekvensnr(){
	return sekvensnr;
    }
    
    public int getKanalId() {
	return kanalId;
    }
    
    public void setMelding(String mld){
	melding = mld;
    }
}
