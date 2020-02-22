package com.company;

import java.util.ArrayList;
import java.util.List;

public class PrimeList {
    private IntOI[] sequence;
    public PrimeList(int limit){
        if(limit<2){
            throw new IllegalArgumentException("Limit must be greater or equal to 2");
        }
        sequence = createSequence(limit);
        System.out.println(this);
        for (int index = 0; index<(Math.sqrt(sequence.length)); index++){
            IntOI intOI = sequence[index];
            if (intOI.isActive()) {
                sive(intOI.value); //don't get why an atribute should be passed to a private method
                System.out.println(this);
            }
        }
    }
    private int[] sive(int n) {
    	if(n<2){
    		throw new IllegalArgumentException("Limit must be greater or equal to 2");
    	}
    	return crossOutHigherMultiples(n);
    	
	}
    private int[] crossOutHigherMultiples(int n) {
    	List<Integer> out = new ArrayList<>();  
    	for(IntOI intOI: sequence){
    		if(intOI.value%n==0&&intOI.value!=n){
    			intOI.crossOut(); 
    			out.add( intOI.value);
    		}
    	}
    	return  out.stream().mapToInt(i -> i).toArray();
	}



    private IntOI[] createSequence(int limit) {
        if(limit<2){
            throw new IllegalArgumentException("Limit must be greater or equal to 2");
        }
        IntOI[] out = new IntOI[limit-1];
        for(int i = 2; i<=limit; i++) {
            out[i-2]=new IntOI(i);
        }
        return out;
    }

    @Override
    public String toString() {
        if(this.sequence==null){
            return super.toString();
        }
        StringBuilder out = new StringBuilder("PrimeList(elements):{");
        for(IntOI intOI:sequence){
            out.append(intOI);
            out.append(", ");
        }
        out.replace(out.length()-2, out.length(),"");
        out.append("}");
        return out.toString();
    }

    public String nonCrossedOutSubseqToString(){
        int count = 0;
        if(this.sequence==null){
            return "get YEEEEEEEEEEEEEEEEEEEEETed, you asked for this";
        }
        StringBuilder out = new StringBuilder("PrimeList(primes):{");
        int insertionIndex = out.length()-2;
        for(IntOI intOI:sequence){
            if(intOI.isActive()) {
                out.append(intOI);
                out.append(", ");
                count++;
            }
        }
        out.replace(out.length()-2, out.length(),"");
        out.append("}");
        out.insert(insertionIndex,"["+count+"]");
        return out.toString();
    }
}
