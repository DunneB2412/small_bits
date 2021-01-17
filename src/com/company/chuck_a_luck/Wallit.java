package com.company.chuck_a_luck;

public class Wallit {
	private float balance;
	Wallit(float start){
		balance = start;
	}
	protected float getBalance() {
		return balance;
	}
	protected void addBalance(float balance) {
		this.balance += balance;
	}
	@Override
	public String toString() {
		return "Wallit:["+balance+"]";
	}
	

}
