package no.gamejam;

public class Pair<E,F> {
	private E first;
	public E getFirst() {
		return first;
	}

	public F getSecond() {
		return second;
	}

	private F second;
	
	public Pair(E first, F second){
		this.first = first;
		this.second = second;
	}
}
