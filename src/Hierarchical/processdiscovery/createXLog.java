package Hierarchical.processdiscovery;

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.plugins.simplelogoperations.XLogFunctions;

public class createXLog {
	@Plugin( 
			name = "AAAB", 
			parameterLabels = { "log" }, 
			returnLabels = { "log" },
			returnTypes = { XLog.class },
			userAccessible = true)     ///
	@UITopiaVariant(affiliation ="" , author = "  ", email = "  ")
	@PluginVariant(variantLabel = "createXLog", requiredParameterLabels = { 0 })
	public XLog removeEdgePoints(PluginContext context, XLog log) {
		//XAttributeMap at = log.getAttributes();
		
		//创建一个新的日志
		XAttributeMap attMap = new XAttributeMapImpl();
		XLogFunctions.putLiteral(attMap, "concept:name", "12345678");
		System.out.println("XLogFunctions.putLiteral(attMap, \"concept:name\", \"12345678\");");
		XLogImpl a = new XLogImpl(attMap);
		
		
		return a;
		
	}

}
