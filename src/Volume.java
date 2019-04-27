
public class Volume {
	private int defaultVol;
	private int vol;
	
	// Constructor com Overloading
	public Volume() {
		this.defaultVol = 4000;
		this.vol = defaultVol;
	}
	
	public Volume(int volume) {
		if(volume >= 0 && volume < 16383)
			this.defaultVol = volume;
		else
			this.defaultVol = 4000;
		this.reset();
	}	
	
	public void reset() {
		this.vol = this.defaultVol;
	}
	
	public int get() {
		return vol;
	}
	
	public int change(double gain) {
		if(vol*gain > 16383)
			vol = 16383;
		else if(vol*gain < 0)
			vol = 0;
		else
			vol =  (int) (vol*gain);
		
		return vol;
	}
}
