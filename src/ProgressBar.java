import org.jfugue.Pattern;
import org.jfugue.Player;

public class ProgressBar extends Thread {
	private Player player;
	private Pattern MusicString;
	private Window view;
	
	public ProgressBar(Player player, Pattern MusicString, Window view) {
		this.player = player;
		this.MusicString = MusicString;
		this.view = view;
	}
	
	public void run(){
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true) {
			if (Thread.interrupted()) {
				break;
		    }
			long pos = this.player.getSequencePosition();
			long length = this.player.getSequenceLength(this.player.getSequence(this.MusicString));
			this.view.setProgressBar((int)(pos*100 / length));
		}
	} 
}
