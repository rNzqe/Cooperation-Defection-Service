package i5.las2peer.services.cdService.simulation.dynamic;

import i5.las2peer.services.cdService.simulation.Agent;
import i5.las2peer.services.cdService.simulation.Simulation;

public class WinStayLoseShift extends Dynamic {

	private static final long serialVersionUID = 1L;

	final static DynamicType TYPE = DynamicType.WS_LS;

	@Override
	public boolean getNewStrategy(Agent agent, Simulation simulation) {

		if (simulation.getRound() < 2)
			return agent.getStrategy();

		int round = simulation.getRound() - 1;
		boolean myStrategy = agent.getStrategy(round);

		double myPayoff = agent.getPayoff(round);
		double myLastPayoff = agent.getPayoff(round - 1);

		return getNewStrategy(myStrategy, myPayoff, myLastPayoff);
	}

	protected boolean getNewStrategy(boolean myStrategy, double myPayoff, double myLastPayoff) {

		if (myLastPayoff >= myPayoff) {
			if (myStrategy == true) {
				return false;
			}
			return true;
		}
		return myStrategy;
	}

	@Override
	public DynamicType getDynamicType() {

		return WinStayLoseShift.TYPE;
	}

}
