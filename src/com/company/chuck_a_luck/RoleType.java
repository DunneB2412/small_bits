package com.company.chuck_a_luck;

public enum RoleType{
	TRIPLE("triple", 30, 1),			//All 3 dice show same number (but not 1s or 6s).	30:1
	FIELD("field",1.1f, 1),			//Total of 3 dice < 8 or total is > 12.	1:1
	HIGH("high",1.2f, 1),			//Total of 3 dice > 10 (but not a Triple).	1:1
	LOW("low",1.2f, 1),			//Total of 3 dice < 11 (but not a Triple).	1:1
	NO_RETURN("none",0,0);			//none of the above 0:0
	
	private final float r;
	final float i;
	private final String name;
	RoleType(String name,float r, float i) {
		this.name = name;
		this.r=r;
		this.i=i;
	}
	
	public static RoleType[] getroleType(Dice... dices) {
		if(dices.length!=3) {
			return new RoleType[] {NO_RETURN};
		}
		int sum = dices[0].lastRole()+dices[1].lastRole()+dices[2].lastRole();
		if(dices[0].lastRole()!=1&dices[0].lastRole()!=6
				&&dices[0].lastRole()==dices[1].lastRole()
				&&dices[0].lastRole()==dices[2].lastRole()) {
			if(sum<8||sum>12) {
				return new RoleType[] {TRIPLE, FIELD};
			}
			return new RoleType[] {TRIPLE};
		}
		if(sum<8||sum>12) {
			return new RoleType[] {FIELD};
		}
		
		if(sum>10) {
			return new RoleType[] {HIGH};
		}
		if(sum<11) {
			return new RoleType[] {LOW};
		}
		return new RoleType[] {NO_RETURN};
	}

	
	public float getReturn(float betValue) {
		float out = - betValue*this.i;
		out += betValue*this.r;
		return out;
	}
	@Override
	public String toString() {
		return this.name;
	}
}
