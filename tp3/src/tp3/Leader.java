package tp3;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;


public class Leader extends LC0_Algorithm {

	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
		setLocalProperty("nbVoisin", vertex.getDegree());;
	}

	@Override
	public String getDescription() {
		return "Leader algorithm using LC0.\n" + "Rule: N(1)---N(x) ---> F(0)---N(x-1)  x>1 \n" + "Rule: N(1)---N(1) ---> E(0)---F(0) \n";
	}

	@Override
	protected void onStarCenter() {
		if(getLocalProperty("label").equals("N") && (int)(getLocalProperty("nbVoisin")) ==1 && getNeighborProperty("label").equals("N") && (int)(getNeighborProperty("nbVoisin")) > 1) {
			setLocalProperty("label", "F");
			setLocalProperty("nbVoisin", 0);
			setNeighborProperty("nbVoisin", (int)(getNeighborProperty("nbVoisin")) - 1);
			setDoorState(new MarkedState(true), neighborDoor);
		}else if(getLocalProperty("label").equals("N") && (int)(getLocalProperty("nbVoisin")) ==1 && getNeighborProperty("label").equals("N") && (int)(getNeighborProperty("nbVoisin")) == 1) {
			setLocalProperty("label", "E");
			setLocalProperty("nbVoisin", 0);
			setNeighborProperty("nbVoisin", 0);
			setNeighborProperty("label", "F");
			setDoorState(new MarkedState(true), neighborDoor);
		}
		putProperty("Affichage", getLocalProperty("nbVoisin"), SimulationConstants.PropertyStatus.DISPLAYED);
	}

	@Override
	public Object clone() {
		return new Leader();
	}

}