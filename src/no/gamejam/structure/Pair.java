package no.gamejam.structure;

public class Pair<E, F> {
	private final E fst;
	private final F snd;
	
	public Pair(E fst, F snd){
		this.fst = fst;
		this.snd = snd;
	}
	
	public E first(){
		return fst;
	}
	public F second(){
		return snd;
	}
}
