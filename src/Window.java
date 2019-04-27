import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.SystemColor;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class Window {

	private JFrame frmMusicGenerator;
	private static Window window;
	private static Controller ctrl;
	private Reference ref;

	/** FUNÇÃO MAIN **/
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Window();
					ctrl = new Controller(window);
					window.frmMusicGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Elementos Gráficos
	private JButton btnReferencia = new JButton("Refer\u00EAncia");
	private JButton btnGerarMusica = new JButton("Gerar M\u00FAsica");
	private JButton btnAbrirArquivo = new JButton("Abrir Arquivo");
	private JButton btnPlayPause = new JButton("\u25BA");
	private JButton btnStop = new JButton("\u23F9");
	private JButton btnSalvarMidi = new JButton("Salvar MIDI");
	private JLabel lblSelecionarInstrumento = new JLabel("Instrumento Inicial:");
	private JLabel lblNewLabel = new JLabel("Digite a m\u00FAsica codificada:");
	private JLabel lblBpm = new JLabel("BPM:");
	private JComboBox<ComboItem> comboBox = new JComboBox<ComboItem>();
	private JTextArea txtMsg = new JTextArea();
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JFileChooser fileChooserOpen = new JFileChooser();
	private JFileChooser fileChooserSave = new JFileChooser();
	private JSpinner spinner = new JSpinner();
	private JProgressBar progressBar = new JProgressBar();
	
	
	public Window() {
		initialize();
	}
	
	/** GETTERS **/
	public String getCodedInput() {
		return textArea.getText();
	}
	
	public int getTempo() {
		return (int) spinner.getValue();
	}
	
	public int getInstrument() {
		return Integer.parseInt(((ComboItem) comboBox.getSelectedItem()).getValue());
	}
	
	public Boolean getPlayPauseBtn() {
		String status = btnPlayPause.getText(); // Símbolo de Play
		if (status == "\u25BA")
			return false; // Retorna TRUE se PLAY
		else
			return true; // Retorna FALSE se PAUSE
	}
	
	/** SETTERS **/
	public void setCodedInput(String content) {
		textArea.setText(content);
	}
	
	public void setPlayPauseBtn(Boolean play) {
		if(play)
			btnPlayPause.setText("\u25BA"); // Símbolo de Play
		else
			btnPlayPause.setText("\u23F8"); // Símbolo de Pause
	}
	
	public void setProgressBar(int progress) {
		progressBar.setValue(progress);
	}
	
	/** MÉTODOS **/
	// Exibe mensagem na tela
	public void alertMsg(Boolean error, String content) {
		if(error)
			txtMsg.setForeground(Color.RED);
		else
			txtMsg.setForeground(new Color(0, 128, 0));
		txtMsg.setText(content);
	}
	
	// Desabilita a interface player
	public void disablePlayer() {
		btnPlayPause.setEnabled(false);
		btnStop.setEnabled(false);
		btnSalvarMidi.setEnabled(false);
	}
	
	// Habilita a interface player
	public void enablePlayer() {
		btnPlayPause.setEnabled(true);
		btnStop.setEnabled(true);
		btnSalvarMidi.setEnabled(true);
	}		

	// Inicializa os elementos gráficos
	private void initialize() {
		// Configura a Janela Principal
		frmMusicGenerator = new JFrame();
		frmMusicGenerator.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmMusicGenerator.setTitle("Music Generator");
		frmMusicGenerator.setResizable(false);
		frmMusicGenerator.setBounds(100, 100, 750, 520);
		frmMusicGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusicGenerator.getContentPane().setLayout(null);
		
		// Configura o FileChooser
		fileChooserOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooserOpen.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto", "txt", "text"));
		fileChooserSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooserSave.setFileFilter(new FileNameExtensionFilter("Arquivo MIDI", "midi"));
		fileChooserSave.setSelectedFile(new File("musica_gerada.midi"));
		
		// btnReferencia
		btnReferencia.setBounds(615, 13, 117, 23);
		btnReferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame RefFrame= new JFrame();
				ref = new Reference();
				RefFrame.setSize(750,600);
				RefFrame.setResizable(false);
				RefFrame.getContentPane().add(ref);
				RefFrame.setVisible(true);
			}
		});
		
		// btnPlayPause
		btnPlayPause.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnPlayPause.setEnabled(false);
		btnPlayPause.setBounds(10, 430, 51, 50);
		btnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.playPause().execute();
			}
		});
		
		// btnStop
		btnStop.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnStop.setEnabled(false);
		btnStop.setBounds(69, 430, 51, 50);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.stop();
			}
		});
		
		// comboBox
		comboBox.addItem(new ComboItem("Piano", "0"));
		comboBox.addItem(new ComboItem("Violão", "24"));
		comboBox.addItem(new ComboItem("Guitarra", "30"));
		comboBox.addItem(new ComboItem("Violino", "40"));
		comboBox.addItem(new ComboItem("Trompete", "56"));
		comboBox.addItem(new ComboItem("Flauta", "73"));
		comboBox.setBounds(535, 443, 196, 22);
		
		// lblSelecionarInstrumento
		lblSelecionarInstrumento.setBounds(538, 423, 193, 16);
		
		// lblNewLabel
		lblNewLabel.setBounds(10, 24, 190, 16);
		
		// textArea
		textArea.setTabSize(4);
		textArea.setBounds(10, 45, 722, 284);
		
		// scrollPane
		scrollPane.setBounds(10, 45, 722, 284);
		
		// btnGerarMusica
		btnGerarMusica.setBounds(10, 350, 110, 25);
		btnGerarMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.gerarMusica();
			}
		});
		
		// btnAbrirArquivo
		btnAbrirArquivo.setBounds(622, 350, 110, 25);
		btnAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = fileChooserOpen.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					ctrl.abrirArquivo(fileChooserOpen.getSelectedFile());
				}
			}
		});
		btnSalvarMidi.setEnabled(false);
		
		// btnSalvarMidi
		btnSalvarMidi.setBounds(501, 350, 110, 25);
		btnSalvarMidi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				int returnValue = fileChooserSave.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File saveFile = fileChooserSave.getSelectedFile();
					if(!saveFile.toString().contains("."))
						saveFile = new File(saveFile.toString() + ".midi");
					ctrl.salvarArquivo(saveFile);
				}
			}
		});
		
		// lblBpm
		lblBpm.setBounds(470, 423, 31, 16);
		txtMsg.setWrapStyleWord(true);
		txtMsg.setLineWrap(true);
		txtMsg.setBackground(SystemColor.control);
		txtMsg.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtMsg.setEditable(false);
		txtMsg.setBounds(137, 352, 350, 70);
		txtMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// spinner
		spinner.setModel(new SpinnerNumberModel(120, 1, 999, 1));
		spinner.setBounds(470, 443, 50, 22);
		
		// Adiciona os elementos gráficos à janela principal
		frmMusicGenerator.getContentPane().add(btnSalvarMidi);
		frmMusicGenerator.getContentPane().add(lblBpm);
		frmMusicGenerator.getContentPane().add(spinner);
		frmMusicGenerator.getContentPane().add(btnReferencia);
		frmMusicGenerator.getContentPane().add(btnPlayPause);
		frmMusicGenerator.getContentPane().add(btnStop);
		frmMusicGenerator.getContentPane().add(comboBox);
		frmMusicGenerator.getContentPane().add(lblSelecionarInstrumento);
		frmMusicGenerator.getContentPane().add(lblNewLabel);
		frmMusicGenerator.getContentPane().add(scrollPane);
		//frmMusicGenerator.getContentPane().add(textArea);
		frmMusicGenerator.getContentPane().add(btnGerarMusica);
		frmMusicGenerator.getContentPane().add(btnAbrirArquivo);
		frmMusicGenerator.getContentPane().add(txtMsg);
		progressBar.setForeground(new Color(0, 120, 215));
		
		// progressBar
		progressBar.setBounds(139, 447, 310, 16);
		frmMusicGenerator.getContentPane().add(progressBar);
		
	}
}
