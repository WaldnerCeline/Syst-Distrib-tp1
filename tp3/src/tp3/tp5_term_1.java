package tp3;

import java.util.ArrayList;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class tp5_term_1 extends LC2_Algorithm{

	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
	}

	@Override
	public String getDescription() {
		return "Etoile ouverte Arbre recouvrant terminaison \n";
	}

	@Override
	protected void onStarCenter() {
		if(getLocalProperty("label").equals("N")) {
			int i = 0;
			boolean QuiteLoop = false;
			while(QuiteLoop == false && i<getActiveDoors().size()) {
				if(getNeighborProperty(i,"label").equals("A")) {
					setLocalProperty("label", "A");
					setDoorState(new MarkedState(true), i);
					QuiteLoop = true;
				}
				i++;
			}
		}else if(getLocalProperty("label").equals("A") && countA() != getActiveDoors().size()) {
			for(int i=0; i< getActiveDoors().size(); i++) {
				if(getNeighborProperty(i, "label").equals("N")) {
					setNeighborProperty(i, "label", "A");
					setDoorState(new MarkedState(true), i);
				}
			}
		}else if(getLocalProperty("label").equals("A") && countA() == getActiveDoors().size()) {
			localTermination();
		}
	}

	public int countA() {
		int cmpt = 0;
		for(int i=0; i< getActiveDoors().size(); i++) {
			if(getNeighborProperty(i, "label").equals("A")) {
				cmpt++;
			}	
		}
		return cmpt;
	}
	@Override
	public Object clone() {
		return new tp5_term_1();
	}

	
}