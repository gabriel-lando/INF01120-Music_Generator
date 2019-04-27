public class Oitava {
	private byte defaultOct;
	private byte oct;
	
	public Oitava() {
		this.defaultOct = 0;
		this.reset();
	}
	
	public Oitava(int oitava) {
		if(oitava >= 0 && oitava < 10)
			this.defaultOct = (byte) oitava;
		else
			this.defaultOct = 0;
		this.reset();
	}
	
	public void reset() {
		this.oct = this.defaultOct;
	}
	
	public byte get() {
		return this.oct;
	}
	
	public void inc() {
		if(++(this.oct) >= 10)
			this.reset();
	}
}