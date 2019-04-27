import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class Reference extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	

	/**
	 * Create the panel.
	 */
	public Reference() {
		setLayout(null);
		
		JLabel lblReferncia = new JLabel("Refer\u00EAncia");
		lblReferncia.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReferncia.setBounds(29, 13, 178, 40);
		add(lblReferncia);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 49, 755, 9);
		add(separator);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setBorder(null);
		table.setRowSelectionAllowed(false);
		table.setToolTipText("");
		table.setBackground(SystemColor.control);
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Texto", "A\u00E7\u00E3o"},
				{"A", "L\u00E1"},
				{"B", "S\u00ED"},
				{"C", "D\u00F3"},
				{"D", "R\u00E9"},
				{"E", "Mi"},
				{"F", "F\u00E1"},
				{"G", "Sol"},
				{"a,b,c,d,e,f,g ", "Se caractere anterior era NOTA (A a G), repete nota; Caso contr\u00E1rio, sil\u00EAncio ou pausa."},
				{"Espa\u00E7o", "Aumenta volume para o DOBRO do volume."},
				{"\"!\"", "Trocar instrumento para o instrumento General MIDI #7 (Harpsichord)."},
				{"i, o, u, I, O, U", "Aumenta volume em 10%"},
				{"Outras cons.", "Se caractere anterior era NOTA (A a G), repete nota; Caso contr\u00E1rio, sil\u00EAncio ou pausa."},
				{"D\u00EDgitos", "Trocar instrumento para o instrumento General MIDI (instrumento ATUAL + valor do d\u00EDgito)."},
				{"\"?\" e \".\"", "Aumenta uma oitava. Se nao puder, volta a oitava default."},
				{"Nova linha", "Trocar instrumento para o instrumento General MIDI #15 (Tubular Bells)."},
				{"\";\"", "Trocar instrumento para o instrumento General MIDI #76 (Pan Flute)."},
				{"\",\"", "Trocar instrumento para o instrumento General MIDI #20 (Church Organ)."},
				{"Outros", "Se caractere anterior era NOTA (A a G), repete nota; Caso contr\u00E1rio, sil\u00EAncio ou pausa."},
			},
			new String[] {
				"Texto", "A\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(596);
		table.getColumnModel().getColumn(1).setMinWidth(20);
		table.setBounds(10, 69, 710, 480);
		table.setRowHeight(25);
		add(table);

	}
}
