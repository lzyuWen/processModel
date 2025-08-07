package Hierarchical.processdiscovery;
import java.util.HashMap;
import java.util.HashSet;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;

import software.processmining.componentbehaviordiscovery.HierarchicalPetriNet;

@Plugin(
		name = "Hierarchical Business Process Model Discovery4",// plugin name
		
		returnLabels = {"Business Process Model"}, //return labels
		returnTypes = {Petrinet.class},//return class, a cross-organization process model
		
		//input parameter labels, corresponding with the second parameter of main function
		parameterLabels = {" "},
		
		userAccessible = true,
		help = "This plugin aims to discover hierarchical process models from lifecycle event logs." 
		)
public class HierarchicalBusinessProcessDiscoveryPlugin4 {
	@UITopiaVariant(
	        affiliation = "TU/e", 
	        author = "Cong liu", 
	        email = "c.liu.3@tue.nl;liucongchina@gmail.com"
	        )
	@PluginVariant(
			variantLabel = "Business Process Model Discovery, default",
			// the number of required parameters, 0 means the first input parameter 
			requiredParameterLabels = {0})
	public Petrinet HierarchicalBehaviorDiscovery(UIPluginContext context,HierarchicalPetriNet hpn) 
	{
		Petrinet pn = hpn.getPn();
		HashMap<String, XEventClass> eventClassName2EventClass = new HashMap<>();
		
		//get all nested transition in the current level. 
		HashSet<String> nestedTransitionLabels = new HashSet<>();
		for(XEventClass xe: hpn.getXEventClass2hpn().keySet())
		{
			nestedTransitionLabels.add(xe.toString());
			eventClassName2EventClass.put(xe.toString(), xe);
		}
		
		//get all transition that are not invisible. 
		HashSet<Transition> transitionSet = new HashSet<>();
		for(Transition t :pn.getTransitions())
		{
			if(!t.isInvisible()) // not invisible transition
			{
				transitionSet.add(t);
			}
		}
		Transition t = transitionSet.iterator().next();
		//for normal transitions that are not nested transitions. 	
		HierarchicalPetriNet hpn1 = hpn.getXEventClass2hpn().get(eventClassName2EventClass.get(t.toString()));	
		
		return hpn1.getPn();
	}
		
}