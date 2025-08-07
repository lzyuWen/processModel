package software.processmining.componentbehaviordiscovery;

import java.util.HashMap;
import java.util.HashSet;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClasses;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.widgets.helper.ProMUIHelper;
import org.processmining.framework.util.ui.widgets.helper.UserCancelledException;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.InductiveMiner.mining.MiningParameters;
import org.processmining.plugins.InductiveMiner.plugins.dialogs.IMMiningDialog;
import org.processmining.plugins.connectionfactories.logpetrinet.TransEvClassMapping;
import org.processmining.plugins.petrinet.replayresult.PNRepResult;

import Hierarchical.processdiscovery.ActivityNestingGraph;
import Hierarchical.processdiscovery.ActivityPair;
import Hierarchical.processdiscovery.ActivityRelationDetection;
import Hierarchical.processdiscovery.HEventLog;
import Hierarchical.processdiscovery.HEventLogConstruction;
import Hierarchical.processdiscovery.TransitiveNestingRelationReduction;
import nl.tue.alignment.TraceByTraceAlignment;

@Plugin(
	        name = "Alignment Plugin",
	        parameterLabels = {"Process Model"  , "Event Log"},
	        returnLabels = { "Log Alignment" },
	        returnTypes = { PNRepResult.class }
	)

  public class Aligment {	
@UITopiaVariant(
		        affiliation = "TU/e", 
		        author = "Cong liu", 
		        email = "c.liu.3@tue.nl;liucongchina@163.com"
		        )
@PluginVariant(
	            variantLabel = "Align Event Log and Process Model",
	            requiredParameterLabels = { 0, 1 }
	    )
		
	 public TraceByTraceAlignment EventLogProcessModelAlignment(UIPluginContext context, Petrinet hpn, XLog log) throws Exception, UserCancelledException{
	        
	        // Clone the top-level PN to the new flat PN. This function also returns a mapping from the old PN to the cloned PN.
	        // Create ReplayerParameters object and set parameters	              
	        Marking initmarking = null;    
	        Marking finalmarking = null;
	        HEventLog hlog = HierarchicalEventLogDiscovery(context,log);
	      	// ����ʹ��hierarchicalLog������н�һ���Ĳ���
	        XLog mainLog = hlog.getMainLog();//set the log name
	      //����һ������"concept:name"���Եķ�����
	        XEventAttributeClassifier eventClassifier = new XEventAttributeClassifier("concept:name");
	        XLogInfo logInfo = XLogInfoFactory.createLogInfo(mainLog, eventClassifier);
	        XEventClasses classes = logInfo.getEventClasses();
	        
	        
	     // ����һ������������¼����
	        XEventClass dummyEvClass = new XEventClass("dummy", 9999);// �����ʵ��������¼����
	        TransEvClassMapping mapping = constructMapping(hpn, mainLog, dummyEvClass, eventClassifier);
	        
	        
	          // XEventClassifier classifier = new XEventNameClassifier();// Replace this with your actual event classifier
	          // ��ȡ����־��ӳ��
	       // HashMap<XEventClass, HSoftwareEventLog> subLogMapping = hlog.getSubLogMapping();
	        // ��ȡ���е�XEventClass
	      //Set<XEventClass> eventClasses = subLogMapping.keySet();
	     // Create XEventClasses object based on your requirements
	     	
			// Return your PNRepResult object
			return doLogReplay(top_pn, initmarking, finalmarking, classes, mapping, log); // Replace this with your actual PNRepResult object
		
		
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
		public HEventLog HierarchicalEventLogDiscovery(UIPluginContext context, XLog lifecycleLog) throws ConnectionCannotBeObtained, UserCancelledException 
		{
			// the input nesting threshold
			double nestingRationThreshold = ProMUIHelper.queryForDouble(context, "Select Nesting Threshold", 0, 1,0.85);	
			//double nestingRationThreshold =0.85;
			
			//set the inductive miner parameters, the original log is used to set the classifier
			IMMiningDialog dialog = new IMMiningDialog(lifecycleLog);
			InteractionResult result = context.showWizard("Configure Parameters for Inductive Miner (used for all sub-processes)", true, true, dialog);
			if (result != InteractionResult.FINISHED) {
				return null;
			}
			// the mining parameters are set here 
			MiningParameters IMparameters = dialog.getMiningParameters(); //IMparameters.getClassifier()
			//create factory to create Xlog, Xtrace and Xevent.
			XFactory factory = new XFactoryNaiveImpl();
			XLogInfo Xloginfo = XLogInfoFactory.createLogInfo(lifecycleLog, IMparameters.getClassifier());
			
			//get the activity set of an event log;
			HashSet<String> activitySet =ActivityRelationDetection.getActivitySet(lifecycleLog);
			System.out.println("Activity Set: "+activitySet);
			
			//get all possible activity pairs
			HashSet<ActivityPair> activityPariSet = ActivityRelationDetection.getAllActivityPairs(activitySet);
			System.out.println("Activity Pair Set: "+activityPariSet);
			
			//get the frequency of directly follow relations 
			HashMap<ActivityPair, Integer> directlyFollowFrequency =ActivityRelationDetection.getFrequencyofDirectlyFollowRelation(lifecycleLog, activityPariSet);
			System.out.println("Directly Follow Frequency: "+directlyFollowFrequency);
			
			//get the frequency of overlap relations
			HashMap<ActivityPair, Integer> overlapFrequency =ActivityRelationDetection.getFrequencyofOverlapRelation(lifecycleLog, activityPariSet);
			System.out.println("Overlap Frequency: "+overlapFrequency);

			//get the frequency of contain relations
			HashMap<ActivityPair, Integer> containFrequency =ActivityRelationDetection.getFrequencyofContainRelation(lifecycleLog, activityPariSet);
			System.out.println("Contain Frequency: "+containFrequency);

			//get the set of nesting activity pairs that meet the input nesting ratio
			HashSet<ActivityPair> nestingActivityPariSet = new HashSet<>();
			for(ActivityPair ap : containFrequency.keySet())
			{
				//computing the nesting ratio
				double apNestingRatio =ActivityRelationDetection.nestingFrequencyRatio(ap, containFrequency, directlyFollowFrequency, overlapFrequency);
				System.out.println(ap+" Nesting Ratio: "+apNestingRatio);
				
				if(apNestingRatio>=nestingRationThreshold)
				{
					nestingActivityPariSet.add(ap);
				}
			}
			System.out.println("Nesting Activity Pairs Meeting Input Nesting Ratio: "+nestingActivityPariSet); 

			//get the nesting activity pairs and remove transitive reduction. 
			ActivityNestingGraph ang =TransitiveNestingRelationReduction.ActivityPrecedencyGraphConstruction(nestingActivityPariSet);
			
//			//get all nested activities
//			HashSet<String> allNestedActivities = new HashSet<>();
//			for(String n: TransitiveNestingRelationReduction.getAllNestedActivities(ang))
//			{
//				allNestedActivities.add(n);
//			}
//			System.out.println("Nested Activities: "+allNestedActivities); 

//			//get all root nesting activities
//			HashSet<String> rootActivities  =TransitiveNestingRelationReduction.getAllRootActivities(ang);
//			System.out.println("Root Activities: "+rootActivities); 

			//get the nested nodes of an nesting activity
			for(String node: ang.getAllVertexes())
			{
				System.out.println("Nested Activities of "+node+" are: "+TransitiveNestingRelationReduction.getNestedActivitiesOfAnActivity(ang, node)); 
			}
					
			//Hierarchical Log construction
			HEventLog hlog =HEventLogConstruction.constructHierarchicalLog(ang, activitySet, lifecycleLog, factory, Xloginfo);

			
			return hlog;
		}
		
		

	}
