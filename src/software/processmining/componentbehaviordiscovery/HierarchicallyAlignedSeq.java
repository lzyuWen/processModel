package Hierarchical.processdiscovery.copy;

import java.util.HashMap;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.plugins.petrinet.replayresult.PNRepResult;

public class HierarchicallyAlignedSeq {
	// the main event log. 
		private PNRepResult mainSeq; 
		
		// the mapping from nested activities (name) to corresponding sub-log. Note that only complete events are included. 
		private HashMap<XEventClass, HierarchicallyAlignedSeq> subSeqMapping;
		
		//constructor
		public HierarchicallyAlignedSeq()
		{
			this.mainSeq = null;
			this.subSeqMapping = null;
		}

		public PNRepResult getMainLog() {
			return mainSeq;
		}

		public void setMainLog(PNRepResult mainSeq) {
			this.mainSeq = mainSeq;
		}

		public HashMap<XEventClass, HierarchicallyAlignedSeq> getSubLogMapping() {
			return subSeqMapping;
		}

		public void setSubLogMapping(HashMap<XEventClass, HierarchicallyAlignedSeq> subSeqMapping) {
			this.subSeqMapping = subSeqMapping;
		}
	}

