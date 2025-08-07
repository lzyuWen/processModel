package software.processmining.componentbehaviordiscovery;

import java.util.HashSet;
import java.util.Set;

import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClasses;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.KeepInProMCache;
import org.processmining.models.connections.petrinets.behavioral.FinalMarkingConnection;
import org.processmining.models.connections.petrinets.behavioral.InitialMarkingConnection;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetImpl;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.astar.petrinet.PetrinetReplayerSSD;
import org.processmining.plugins.connectionfactories.logpetrinet.TransEvClassMapping;
import org.processmining.plugins.petrinet.finalmarkingprovider.FinalMarkingFactory;
import org.processmining.plugins.petrinet.initmarkingprovider.InitMarkingFactory;
import org.processmining.plugins.petrinet.replayer.algorithms.IPNReplayParameter;
import org.processmining.plugins.petrinet.replayer.annotations.PNReplayAlgorithm;
import org.processmining.plugins.petrinet.replayresult.PNRepResult;

import nl.tue.alignment.ReplayerParameters;
import nl.tue.astar.AStarException;
@KeepInProMCache
@PNReplayAlgorithm(isBasic = true)
public class HierarchicallyAlignedSeqConstruction extends PetrinetReplayerSSD {
	public PNRepResult HierarchicalBehaviorDiscovery(final PluginContext context, HierarchicalPetriNet hpn, final XLog hlog) throws AStarException {
		
		 // Define the converted flat petri net as a global variable
        final Petrinet pn = new PetrinetImpl("Cloned flat PN");

        // Clone the top-level PN to the new flat PN. This function also returns a mapping from the old PN to the cloned PN.
        ConvertPetriNet2PNwithLifeCycle.clonePetriNet(hpn.getPn(), pn);

        // Create ReplayerParameters object and set parameters
        ReplayerParameters parameters = new ReplayerParameters.Default();
     // 创建 Petri 网对象

        // 创建一个 PetrinetGraph 对象，表示要构造最终标记的 Petri 网
        PetrinetGraph net = pn; // 根据实际情况创建 Petri 网
		//the main part
		//for top-level, we need to add all events whose caller class does not belongs to the current component.
		// for the nestingDepth=0, we get the main (or top-level) log.
		// 获取XFactory对象
		XFactory factory = XFactoryRegistry.instance().currentDefault();
		// 创建类集合
		Set<String> classSet = new HashSet<>();
		classSet.add("ClassA");
		classSet.add("ClassB");
		// 添加更多的类别
		// 创建原始事件日志
		XLog originalLog = log; // 根据实际情况提供原始事件日志
		// 创建XLogInfo对象
		XLogInfo Xloginfo = XLogInfoFactory.createLogInfo(log);// 根据实际情况提供XLogInfo对象
		// 提供组件名称
		String component = "ComponentA";
		
		HSoftwareEventLog hlog = constructHierarchicalLog(3, factory, classSet, originalLog, Xloginfo, component);
		
		final XLog mainLog = hlog.getTopLevelLog();//set the log name
		
        InitMarkingFactory initmarkingFactory = new InitMarkingFactory();
        Object[] initmarking = initmarkingFactory.constructInitMarking(context, net);
        // 从结果数组中获取连接对象和最终标记
        // 从结果数组中获取连接对象和初始标记
        InitialMarkingConnection initconnection = (InitialMarkingConnection) initmarking[0];
        Marking initialMarking = (Marking) initmarking[1];
        // 调用 constructFinalMarking() 方法获取最终标记

        // 从结果数组中获取连接对象和初始标记
        FinalMarkingFactory finalmarkingFactory = new FinalMarkingFactory();
        Object[] finalmarking = finalmarkingFactory.constructFinalMarking(context, net);

        // 从结果数组中获取连接对象和最终标记
        FinalMarkingConnection finalconnection = (FinalMarkingConnection) finalmarking[0];
        Marking finalMarking = (Marking) finalmarking[1];

        
      //创建一个基于"concept:name"属性的分类器
        XEventAttributeClassifier eventClassifier = new XEventAttributeClassifier("concept:name");
        XLogInfo logInfo = XLogInfoFactory.createLogInfo(mainLog, eventClassifier);
        XEventClasses classes = logInfo.getEventClasses();
        
        
     // 创建一个常规的虚拟事件类别
        XEventClass dummyEvClass = new XEventClass("dummy", 9999);// 创建适当的虚拟事件类别
        TransEvClassMapping mapping = constructMapping(net, mainLog, dummyEvClass, eventClassifier);
        
        
          // XEventClassifier classifier = new XEventNameClassifier();// Replace this with your actual event classifier
          // 获取子日志的映射
       // HashMap<XEventClass, HSoftwareEventLog> subLogMapping = hlog.getSubLogMapping();
        // 提取所有的XEventClass
      //Set<XEventClass> eventClasses = subLogMapping.keySet();
     // Create XEventClasses object based on your requirements
     	
		
		TransEvClassMapping mapping = null;
		final IPNReplayParameter parameters = null;
		//XLog xlog = hlog.getMainLog();
		XLog xlog = hlog;
		Petrinet pn = new PetrinetImpl("Cloned flat PN");
		pn = hpn.getPn();
		PNRepResult re ;
		re = super.replayLog(context, pn, xlog, mapping, parameters); 
		
		

		return re;
	}

	public void HierarchicalBehaviorDiscoveryRecusively(UIPluginContext context, HierarchicalPetriNet hpn,
			XLog hlog) {

		XLog xlog;
		//xlog = hlog.getMainLog();
		xlog = hlog;
		Petrinet pn = new PetrinetImpl("Cloned flat PN");
		pn = hpn.getPn();
		/*
		 * //get all nested activities // HashSet<String> allNestedActivities =
		 * new HashSet<>(); // for(String n:
		 * TransitiveNestingRelationReduction.getAllNestedActivities(ang)) // {
		 * allNestedActivities.add(n); // } //
		 * System.out.println("Nested Activities: "+allNestedActivities);
		 * 
		 * //get all root nesting activities // HashSet<String> rootActivities
		 * =TransitiveNestingRelationReduction.getAllRootActivities(ang); //
		 * System.out.println("Root Activities: "+rootActivities);
		 */
		//		get the nested nodes of an nesting activity

		//Hierarchical pn discovery 

		PNRepResult re = null;
		
	}

}
