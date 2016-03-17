package backward_induction_release;

import java.util.LinkedList;

public class Node {
	//Akármilyen információ
	private String name;	
	
	private double outcome[] = new double[4];
	
	@Override
	public String toString(){
		String label = name;
		label += " ";
		
		for (int i = 0;i<4;i++){
			label+=" ";
			label+=Integer.toString((int)Math.round(outcome[i]));
		}
		
		return label;
	}
	
	Node(){
		
	}
	
	Node(String asd){
		name = asd;
	}
	
	
	public double[] getOutcome(){
		return outcome;
	}
	
	public void setOutcome(double[] o){
		outcome = o;
	}
	
	
}
