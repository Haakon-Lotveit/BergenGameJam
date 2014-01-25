package ghp.tilegame.main.levels;

public class UndefinedGlyphException extends Exception {
	private static final long serialVersionUID = 458143076327774279L;

	public UndefinedGlyphException(){
		super();
	}
	
	public UndefinedGlyphException(String message){
		super(message);
	}
}
