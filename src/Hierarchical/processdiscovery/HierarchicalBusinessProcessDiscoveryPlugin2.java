package Hierarchical.processdiscovery;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;

import software.processmining.componentbehaviordiscovery.HierarchicalPetriNet;

@Plugin(
		name = "Hierarchical Business Process Model Discovery2",// plugin name
		
		returnLabels = {"Business Process Model"}, //return labels
		returnTypes = {Petrinet.class},//return class, a cross-organization process model
		
		//input parameter labels, corresponding with the second parameter of main function
		parameterLabels = {" "},
		
		userAccessible = true,
		help = "This plugin aims to discover hierarchical process models from lifecycle event logs." 
		)
public class HierarchicalBusinessProcessDiscoveryPlugin2 {
	@UITopiaVariant(
	        affiliation = "TU/e", 
	        author = "Cong liu", 
	        email = "c.liu.3@tue.nl;liucongchina@gmail.com"
	        )
	@PluginVariant(
			variantLabel = "Business Process Model Discovery, default",
			// the number of required parameters, 0 means the first input parameter 
			requiredParameterLabels = {0})
	public Petrinet HierarchicalBehaviorDiscovery(UIPluginContext context ,HierarchicalPetriNet hpn) 
	{
		
		return hpn.getPn();
		
	}
	
}

