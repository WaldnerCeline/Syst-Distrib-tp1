package tp2_SystDist;

import java.util.ArrayList;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class tp_2 extends LC0_Algorithm{
	
	int fatherDoor = -1;
	ArrayList<String> etatV;
	
	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
		etatV = new ArrayList<String>();
		for(int i = 0; i<vertex.getDegree(); i++) {
			etatV.add("N");
		}
		setLocalProperty("cmp", 1);
	}

	@Override
	public String getDescription() {
		return "Arbre recouvrant \n";
	}

	@Override
	protected void onStarCenter() {
		etatV.set(neighborDoor, getNeighborProperty("label").toString());
		if(getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("A")) {
			setLocalProperty("label", "A");
			setNeighborProperty("label", "M");
			fatherDoor = neighborDoor;
			setDoorState(new MarkedState(true));
		}else if(getLocalProperty("label").equals("A") && getNeighborProperty("label").equals("M") && countN() == 0 && neighborDoor == fatherDoor) {
			setLocalProperty("label", "F");
			setNeighborProperty("label", "A");
			setNeighborProperty("cmp", (int)getNeighborProperty("cmp") + (int)getLocalProperty("cmp"));
		}
		putProperty("Affichage", getLocalProperty("cmp"), SimulationConstants.PropertyStatus.DISPLAYED);
	}
	
	public int countN() {
		int N = 0;
		for(int i = 0; i<etatV.size(); i++) {
			if(etatV.get(i).equals("N")) {
				N++;
			}
		}
		return N;
	}

	@Override
	public Object clone() {
		return new tp_2();
	}

}
