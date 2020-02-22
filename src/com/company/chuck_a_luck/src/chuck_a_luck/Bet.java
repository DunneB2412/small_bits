package chuck_a_luck;

public class Bet implements IHandleBet{
	public final float betValue;
	private final RoleType betOutcome;
	Bet(float bet, RoleType betOutcome){
		betValue = bet;
		this.betOutcome = betOutcome;
	}
	@Override
	public float handleBet(RoleType[] roleOutcomes) {
		for(RoleType roleOutcome: roleOutcomes) {
			if(roleOutcome==betOutcome) {
				return roleOutcome.getReturn(betValue);
			}
		}
		return betValue*-betOutcome.i;
	}
	@Override
	public String toString() {
		return "bet:[ value:<"+betValue+">, that dice will role ("+betOutcome+")]";
	}

}
