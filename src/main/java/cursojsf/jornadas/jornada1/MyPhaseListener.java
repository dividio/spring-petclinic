package cursojsf.jornadas.jornada1;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MyPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println("Después de la fase:" + arg0.getPhaseId());
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		System.out.println("Antes de la fase:" + arg0.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
