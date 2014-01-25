package no.gamejam;

public interface Actor {
	/**
	 * Every actor needs a chance to act.
	 * Therefore this method.
	 * Later gameBoard will be changed to reflect the actual current level, but for now,
	 * we'll stick to it being any ole' object.
	 * @param gameBoard
	 */
	public void act(Object gameBoard); 
}
