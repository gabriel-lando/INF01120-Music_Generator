import java.io.File;
import java.io.IOException;

import javax.swing.SwingWorker;

import org.jfugue.Instrument;
import org.jfugue.Pattern;
import org.jfugue.Player;
import org.jfugue.Tempo;

public class Controller {
	// Attributes
	private Pattern MusicString;
	private Player player;
	private FileSystem fs;
	private Window view;
	private ProgressBar updateProgressBar;
	
	public Volume volume = new Volume();
	public Oitava oitava = new Oitava();
	public GeneralInstrument instrument = new GeneralInstrument();
	
	
	
	// Constructor
	public Controller(Window window) {
		this.player = new Player();
		this.fs = new FileSystem();
		this.view = window;
	}
	
	// Methods
	public void stop() {
		if(player.isPlaying() || player.isPaused())
			player.stop();
		view.setPlayPauseBtn(true);	  
	}
	
	// Botão Gerar Músicas
	public void gerarMusica() {
		volume.reset();
		oitava.reset();
		instrument.set(view.getInstrument());
		Translator tradutor = new Translator();
		MusicString = new Pattern();
		MusicString.addElement(new Tempo(view.getTempo()));
		MusicString.addElement(new Instrument(instrument.get()));
		MusicString.add("X[Volume]=" + volume.get());
		MusicString.add(tradutor.generateMusicString(view.getCodedInput(), this));
		view.alertMsg(false, "Música Gerada!");
		view.enablePlayer();
		stop();
	}
	
	// Botão Abrir Arquivo
	public void abrirArquivo(File file) {
		view.setCodedInput("");
		view.setCodedInput(fs.readFile(file));
	}
	
	// Botão Salvar MIDI
	public void salvarArquivo(File file) {
		try {
            player.saveMidi(MusicString, file);
            view.alertMsg(false, "Arquivo salvo com sucesso!");
        } catch (IOException ex) {
        	view.alertMsg(true, ex.getMessage());
        }
	}
	
	// Botão Play-Pause
	public SwingWorker<Void, Void> playPause () {
		if(view.getPlayPauseBtn())
			view.setPlayPauseBtn(true);	  
		else
			view.setPlayPauseBtn(false);	  
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		   @Override
		   protected Void doInBackground() throws Exception {
			  if(player.isPlaying()) {
				  updateProgressBar.interrupt();
				  player.pause();
			  }  
			  else if(player.isPaused()) {
				  updateProgressBar = new ProgressBar (player, MusicString, view);
				  updateProgressBar.start();
				  player.resume();
			  } 
			  else {
				  updateProgressBar = new ProgressBar (player, MusicString, view);
				  updateProgressBar.start();
				  player.play(MusicString);
				  while(true) {
					  if(player.isFinished()) {
						  updateProgressBar.interrupt();
						  view.setPlayPauseBtn(true);
						  break;
					  };
				  };
			  };
			  return null;
		   };
		};
		
		view.alertMsg(true, "");
		
		return worker;
	}
}
