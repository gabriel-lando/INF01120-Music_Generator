

import java.util.ArrayList;

public class Translator {
	
	// Função converte a string codificada para uma string do jFugue válida
	public String generateMusicString(String input, Controller ctrl) {
		ArrayList<String> tokens = new ArrayList<String>();
		
		for(int i=0; i<input.length(); i++) {
			
			switch(input.charAt(i)) {
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
					tokens.add(Character.toString(input.charAt(i)) + ctrl.oitava.get());
					break;
				case ' ':
					tokens.add("X[Volume]=" + ctrl.volume.change(2));
					break;
				case 'i':
				case 'o':
				case 'u':
				case 'I':
				case 'O':
				case 'U':
					tokens.add("X[Volume]=" + ctrl.volume.change(1.1));
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					tokens.add("I" + ctrl.instrument.inc(input.charAt(i) - '0'));
					break;
				case '!':
					tokens.add("I" + ctrl.instrument.set(7));
					break;
				case '\n':
					tokens.add("I" + ctrl.instrument.set(15));
					break;
				case ';':
					tokens.add("I" + ctrl.instrument.set(76));
					break;
				case ',':
					tokens.add("I" + ctrl.instrument.set(20));
					break;
				case '?':
				case '.':
					ctrl.oitava.inc();
					break;
				default:
					if(i > 0) 
						if(input.charAt(i-1) >= 'A' && input.charAt(i-1) <= 'G')
							tokens.add(Character.toString(input.charAt(i-1)) + ctrl.oitava.get());
						else
							tokens.add("R");
					else
						tokens.add("R");
						
			}
		}
		return String.join(" ", tokens);
	}
}
