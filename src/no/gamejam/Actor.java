package no.gamejam;

public interface Actor extends Fighter {
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
	 * Checks if taking a step in the given direction will result in a collision with the given actor
	 * @param a The actor you compare with
	 * @param direction the direction you're going, one of u,d,l,r.
	 * @return true if you collide with the actor, false otherwise
	 */
	public boolean collidesWith(Actor a);
	
	public char facesDirection();
}
