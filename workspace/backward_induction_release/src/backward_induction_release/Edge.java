package backward_induction_release;
import java.lang.String;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;


public class Edge extends DefaultWeightedEdge {
	
	public String information="asd";
	
	Edge(String i){
		super();
		information=i;
	}
	/*Edge(){
		
	}
	*/

	@Override
	public String toString(){
		return information;
	}
	
}
