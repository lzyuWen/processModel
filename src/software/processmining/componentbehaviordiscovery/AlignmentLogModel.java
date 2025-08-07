package software.processmining.componentbehaviordiscovery;

import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClasses;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.widgets.helper.UserCancelledException;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.connectionfactories.logpetrinet.TransEvClassMapping;
import org.processmining.plugins.petrinet.replayresult.PNRepResult;

import nl.tue.alignment.TraceByTraceAlignment;



@Plugin(
        name = "AAlignment Plugin",
        parameterLabels = {"Process Model"  , "Event Log"},
        returnLabels = { "Log Alignment" },
        returnTypes = { PNRepResult.class }
)
public class AlignmentLogModel {
	@UITopiaVariant(
	        affiliation = "TU/e", 
	        author = "Cong liu", 
	        email = "c.liu.3@tue.nl;liucongchina@163.com"
	        )
    @PluginVariant(
            variantLabel = "Align Event Log and Process Model",
            requiredParameterLabels = { 0, 1 }
    )
	
 public TraceByTraceAlignment EventLogProcessModelAlignment(UIPluginContext context, Petrinet pn, XLog log, Marking initialMarking, Marking finalMarking) throws Exception, UserCancelledException{
        
		
        // Clone the top-level PN to the new flat PN. This function also returns a mapping from the old PN to the cloned PN.
        // Create ReplayerParameters object and set parameters

        // 创建一个 PetrinetGraph 对象，表示要构造最终标记的 Petri 网
        PetrinetGraph net = pn; // 根据实际情况创建 Petri 网
		//the main part
		//for top-level, we need to add all events whose caller class does not belongs to the current component.

        XEventAttributeClassifier eventClassifier = new XEventAttributeClassifier("concept:name");
        XLogInfo logInfo = XLogInfoFactory.createLogInfo(log, eventClassifier);
        XEventClasses classes = logInfo.getEventClasses();
        
        
     // 创建一个常规的虚拟事件类别
        XEventClass dummyEvClass = new XEventClass("dummy", 9999);// 创建适当的虚拟事件类别
        TransEvClassMapping mapping = constructMapping(pn, log, dummyEvClass, eventClassifier);
        
        
          // XEventClassifier classifier = new XEventNameClassifier();// Replace this with your actual event classifier
          // 获取子日志的映射
       // HashMap<XEventClass, HSoftwareEventLog> subLogMapping = hlog.getSubLogMapping();
        // 提取所有的XEventClass
      //Set<XEventClass> eventClasses = subLogMapping.keySet();
     // Create XEventClasses object based on your requirements
     	
		// Return your PNRepResult object
		return doLogReplay(pn, initialMarking, finalMarking, classes, mapping, log); // Replace this with your actual PNRepResult object
	
	
	}       
 
	public TraceByTraceAlignment doLogReplay(Petrinet net, Marking initialMarking, Marking finalMarking,
			XEventClasses eventClasses, TransEvClassMapping mapping, XLog log) {
		// Create a TraceByTraceAlignment object
		TraceByTraceAlignment traceByTraceAlignment = new TraceByTraceAlignment(net, initialMarking, finalMarking,
				eventClasses, mapping);
		return 	traceByTraceAlignment;
	}

	private TransEvClassMapping constructMapping(PetrinetGraph net, XLog log, XEventClass dummyEvClass,
			XEventClassifier eventClassifier) {
		TransEvClassMapping mapping = new TransEvClassMapping(eventClassifier, dummyEvClass);

		XLogInfo summary = XLogInfoFactory.createLogInfo(log, eventClassifier);

		for (Transition t : net.getTransitions()) {
			for (XEventClass evClass : summary.getEventClasses().getClasses()) {
				String id = evClass.getId();

				// map transitions and event classes based on label
				if (t.getLabel().equals(id)) {
					mapping.put(t, evClass);
					break;
				}
			}

			//			if (!mapped && !t.isInvisible()) {
			//				mapping.put(t, dummyEvClass);
			//			}

		}

		return mapping;
	}


}