package chuck_a_luck;

import java.util.ArrayList;

public class Player extends Wallit{
	private ArrayList<Bet> bets;
	private final String playerName;
	public String log;
	Player(String name, float start){
		super(start);
		playerName = name;
		bets = new ArrayList<>();
		log = "";
	}
	public void setBalance(RoleType[] roleOutcomes) {
		float winnings = 0.0f;
		for(Bet bet: bets) {
			winnings += (bet.handleBet(roleOutcomes));
		}
		String prompt = (winnings>=0? "earned":"lost")+":"+(Math.sqrt(winnings*winnings));
		log = log +prompt+":"+bets+"\n";
		System.out.println(playerName+", you "+prompt);
		super.addBalance(winnings);
		bets.clear();
	}
	public boolean placeBet(float amount, RoleType betOutcome) {
		Bet newBet = new Bet(amount, betOutcome);
		if (getAvalable()-amount>=0) {
			this.bets.add(newBet);	
			return true;
		}
		System.out.println(playerName+", you don't have enough cash to place this bet. you want to \n"+newBet+". you have "+getAvalable()+" avalible to bet with");
		return false;
	}
	@Override
	public String toString() {
		return playerName+":["+super.toString()+", "+bets.toString()+"]";
	}
	private float getAvalable(){
		float betSum = 0f;
		for(Bet bet:bets) {
			betSum += bet.betValue;
		}
		return this.getBalance()-betSum;
	}

}
