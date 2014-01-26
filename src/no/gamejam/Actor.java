package no.gamejam;

import ghp.tilegame.main.levels.Paintable;

public interface Actor extends Fighter, Paintable{
	/**
	 * Every actor needs a chance to act.
	 * Therefore this method.
	 * Later gameBoard will be changed to reflect the actual current level, but for now,
	 * we'll stick to it being any ole' object.
	 * @param gameBoard
	 */
	public void tick(Object gameBoard);
	/**
	 * Gets the x-position from origo, which is top left.
	 * @return the tile where the actor is currently stationed.
	 */
	public int getX();
	/**
	 * Gets the y-position from origo, which is top left.
	 * @return the tile where the actor is currently stationed.
	 */
	public int getY();

	/**
	 * Checks to see if this Actor allows a to move through it.
	 * There can still be other things that blocks movement, so even if this method returns true, that's no guarantee that you should be allowed.
	 * @return true if it blocks him and does NOT allow it, otherwise true
	 */
	public boolean blocksMovement();
	
	public void act();
	
	public char facesDirection();
}
