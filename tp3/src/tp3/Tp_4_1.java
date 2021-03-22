package tp3;

import java.util.ArrayList;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class Tp_4_1 extends LC1_Algorithm{

	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
	}

	@Override
	public String getDescription() {
		return "Etoile ouverte Arbre recouvrant \n";
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
		}
	}

	@Override
	public Object clone() {
		return new Tp_4_1();
	}
}
