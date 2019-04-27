public class GeneralInstrument {
	private byte defaultInst;
	private byte inst;
	
	public GeneralInstrument() {
		this.defaultInst = 0;
		this.reset();
	}
	
	public GeneralInstrument(int instrument) {
		if(instrument >= 0 && instrument < 128)
			this.defaultInst = (byte) instrument;
		else
			this.defaultInst = 0;
		this.reset();
	}
	
	public void reset() {
		this.inst = defaultInst;
	}
	
	public byte set(int newInst) {
		if(newInst <= 127 && newInst >= 0)
			this.inst = (byte) newInst;
		return inst;
	}
	
	public byte get() {
		return this.inst;
	}
	
	public byte inc(int value) {
		return this.set(this.get() + value);
	}
}
