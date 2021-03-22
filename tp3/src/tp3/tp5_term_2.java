package tp3;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class tp5_term_2 extends LC2_Algorithm{

	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
		setLocalProperty("voisinN", vertex.getDegree());
		putProperty("Affichage", getLocalProperty("voisinN"), SimulationConstants.PropertyStatus.DISPLAYED);
	}

	@Override
	public String getDescription() {
		return "Etoile ouverte Leader terminaison \n";
	}

	@Override
	protected void onStarCenter() {
		if(getLocalProperty("label").equals("N") && checkN() > 0 ) {
			for(int i =0; i<getActiveDoors().size(); i++) {
				if(getNeighborProperty(i, "label").equals("N") && (int)(getNeighborProperty(i, "voisinN")) == 1) {
					setNeighborProperty(i, "label", "F");
				}
			}
		}
		else if(getLocalProperty("label").equals("N") && checkN() == 0) {
			setLocalProperty("label", "E");
			//globalTermination();
		}else if(getLocalProperty("label").equals("F") && checkN() == 0) {
			localTermination();
		}
		putProperty("Affichage", getLocalProperty("voisinN"), SimulationConstants.PropertyStatus.DISPLAYED);
	}
	
	public int checkN() {
		int cmpt =0;
		for(int i=0; i< getActiveDoors().size(); i++) {
			if(getNeighborProperty(i, "label").equals("N")) {
				cmpt++;
			}
		}
		setLocalProperty("voisinN", cmpt);
		return cmpt;
	}

	@Override
	public Object clone() {
		return new tp5_term_2();
	}
	
}
