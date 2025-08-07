package Hierarchical.processdiscovery;


import java.util.List;

import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XLifecycleExtension;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.deckfour.xes.model.impl.XTraceImpl;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginLevel;
import org.processmining.framework.plugin.annotations.PluginVariant;

public class creteHPN2 {
	
		@Plugin(name = "AAAAAcreate HPN2", level = PluginLevel.Regular, returnLabels = { "LOG" }, returnTypes = {
				XLog.class },  userAccessible = true, help = "---", parameterLabels = {"0"})
		@UITopiaVariant(affiliation = "", author = "wangkang", email = "1159501573@qq.com")
		@PluginVariant(variantLabel = "Mine a PetriNet, dialog", requiredParameterLabels = { 0 })
		public XLog mineCAM(UIPluginContext context,XLog xlog) {

			// log属性
			XAttributeMap xLogAttributeMap = new XAttributeMapImpl();
			xLogAttributeMap.put("lifecycle:model",
					new XAttributeLiteralImpl("lifecycle:model", "standard", XLifecycleExtension.instance()));
			xLogAttributeMap.put("creator", new XAttributeLiteralImpl("creator", "wk"));
			xLogAttributeMap.put("library", new XAttributeLiteralImpl("library", "OpenXes v1.9"));
			XLog xLog = new XLogImpl(xLogAttributeMap);

			// trace全局属性
			List<XAttribute> globalTraceAttributes = xLog.getGlobalTraceAttributes();
            XAttribute traceNameAttribute = new XAttributeLiteralImpl("concept:name", "name", XConceptExtension.instance());
            globalTraceAttributes.add(traceNameAttribute);
         // event全局属性
            List globalEventAttributes = xLog.getGlobalEventAttributes();
            XAttribute eventNameAttribute = new XAttributeLiteralImpl("concept:name", "name", XConceptExtension.instance());
            globalEventAttributes.add(eventNameAttribute);
            XAttribute eventTransitionAttribute = new XAttributeLiteralImpl("lifecycle:transition", "transition", XLifecycleExtension.instance());
            globalEventAttributes.add(eventTransitionAttribute);
            
         // 分类器
            List classifiers = xLog.getClassifiers();
            XEventClassifier activityClassifier = new XEventAttributeClassifier("concept:name", "name");
            XEventClassifier activityClassifier2 = new XEventAttributeClassifier("lifecycle:transition", "transition");
            classifiers.add(activityClassifier);
            classifiers.add(activityClassifier2);
         for(int i = 0;i<50;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e4 = ModifyLog.makeEventSimpleness("d", "complete");
             XEvent e5 = ModifyLog.makeEventSimpleness("b", "complete");
             XEvent e6 = ModifyLog.makeEventSimpleness("a", "complete");
             XEvent e7 = ModifyLog.makeEventSimpleness("c", "start");
             XEvent e8 = ModifyLog.makeEventSimpleness("c", "complete");
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
         for(int i = 50;i<100;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);          
             XEvent e1 = ModifyLog.makeEventSimpleness("c", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e4 = ModifyLog.makeEventSimpleness("c", "complete");
             XEvent e5 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e6 = ModifyLog.makeEventSimpleness("d", "complete");
             XEvent e7 = ModifyLog.makeEventSimpleness("b", "complete");
             XEvent e8 = ModifyLog.makeEventSimpleness("a", "complete");             
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
         for(int i = 100;i<150;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("c", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e4 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e5 = ModifyLog.makeEventSimpleness("d", "complete");
             XEvent e6 = ModifyLog.makeEventSimpleness("b", "complete");
             XEvent e7 = ModifyLog.makeEventSimpleness("a", "complete");
             XEvent e8 = ModifyLog.makeEventSimpleness("c", "complete");         
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
         for(int i = 150;i<200;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e4 = ModifyLog.makeEventSimpleness("c", "start");
             XEvent e5 = ModifyLog.makeEventSimpleness("d", "complete");           
             XEvent e6 = ModifyLog.makeEventSimpleness("b", "complete");
             XEvent e7 = ModifyLog.makeEventSimpleness("a", "complete");
             XEvent e8 = ModifyLog.makeEventSimpleness("c", "complete");         
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
         for(int i = 200;i<250;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e4 = ModifyLog.makeEventSimpleness("d", "complete");
             XEvent e5 = ModifyLog.makeEventSimpleness("c", "start");                       
             XEvent e6 = ModifyLog.makeEventSimpleness("b", "complete");
             XEvent e7 = ModifyLog.makeEventSimpleness("a", "complete");
             XEvent e8 = ModifyLog.makeEventSimpleness("c", "complete");         
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
         for(int i = 250;i<300;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e4 = ModifyLog.makeEventSimpleness("d", "complete");
             XEvent e5 = ModifyLog.makeEventSimpleness("b", "complete");
             XEvent e6 = ModifyLog.makeEventSimpleness("c", "start");                                    
             XEvent e7 = ModifyLog.makeEventSimpleness("a", "complete");
             XEvent e8 = ModifyLog.makeEventSimpleness("c", "complete");         
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
          
         for(int i = 300;i<350;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("c", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e4 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e5 = ModifyLog.makeEventSimpleness("c", "complete"); 
             XEvent e6 = ModifyLog.makeEventSimpleness("d", "complete");
             XEvent e7 = ModifyLog.makeEventSimpleness("b", "complete");                                              
             XEvent e8 = ModifyLog.makeEventSimpleness("a", "complete");                    
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
         
         for(int i = 350;i<400;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("c", "start");            
             XEvent e4 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e5 = ModifyLog.makeEventSimpleness("d", "complete");         
             XEvent e6 = ModifyLog.makeEventSimpleness("b", "complete"); 
             XEvent e7 = ModifyLog.makeEventSimpleness("c", "complete"); 
             XEvent e8 = ModifyLog.makeEventSimpleness("a", "complete");                    
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xLog.add(xTrace);
         }
        
         for(int i = 400;i<500;i++) {
        	 XAttributeMap xTraceAttributeMap = new XAttributeMapImpl();
             xTraceAttributeMap.put("concept:name", new XAttributeLiteralImpl("concept:name", i+"", XConceptExtension.instance()));
             XTrace xTrace = new XTraceImpl(xTraceAttributeMap);      
             XEvent e1 = ModifyLog.makeEventSimpleness("a", "start");
             XEvent e2 = ModifyLog.makeEventSimpleness("b", "start");
             XEvent e3 = ModifyLog.makeEventSimpleness("c", "start");            
             XEvent e4 = ModifyLog.makeEventSimpleness("d", "start");
             XEvent e5 = ModifyLog.makeEventSimpleness("c", "complete"); 
             XEvent e6 = ModifyLog.makeEventSimpleness("d", "complete");         
             XEvent e7 = ModifyLog.makeEventSimpleness("b", "complete");      
             XEvent e8 = ModifyLog.makeEventSimpleness("a", "complete");    
             XEvent e9 = ModifyLog.makeEventSimpleness("c", "complete");
             xTrace.add(e1);
             xTrace.add(e2);
             xTrace.add(e3);
             xTrace.add(e4);
             xTrace.add(e5);
             xTrace.add(e6);
             xTrace.add(e7);
             xTrace.add(e8);
             xTrace.add(e9);
             xLog.add(xTrace);
         }
         
			return xLog;
		}
	}

