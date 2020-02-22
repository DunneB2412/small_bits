package chuck_a_luck;

import java.util.Random;

public class Dice{
	protected static final Random RANDOM = new Random();
	private final int faces;
	private int role;
	
	public Dice(int faces){
		this.faces = faces;
		role();
	}
	public int role() {
		role = RANDOM.nextInt(faces);
		return role;
	}
	public int lastRole() {
		return role;
	}
	
}
