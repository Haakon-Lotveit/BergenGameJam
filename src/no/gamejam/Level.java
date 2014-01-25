package no.gamejam;

public interface Level {
	/**
	 * A 2d array corresponding to the level, where level[0][0] is the lower left
	 * @return The array, shall never ever be a null-object.
	 */
	public TileMechanics[][] getTiles();
	
	public void addActor(Actor actor, int x, int y);
	public void removeActor(Actor actor);	
}
