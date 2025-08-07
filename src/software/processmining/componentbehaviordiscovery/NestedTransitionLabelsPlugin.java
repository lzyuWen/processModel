package software.processmining.componentbehaviordiscovery;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetImpl;

/*
 * this plugin aims to transform a hierarchical petri net to a flat one in a recursive way. 
 */


@Plugin(
		name = "nestedTransitionLabels Plugin", 
		parameterLabels = { "Hierarchical Petri Net" }, 
		returnLabels = { "Nested Transition Labels" }, returnTypes = { Set.class }
		)

public class NestedTransitionLabelsPlugin {
	@UITopiaVariant(
	        affiliation = "TU/e", 
	        author = "Cong liu", 
	        email = "c.liu.3@tue.nl;liucongchina@163.com"
	        )
    @PluginVariant(
    		variantLabel = "Extract nested transition labels from Hierarchical Petri Net", 
    		requiredParameterLabels = { 0 }
    		)
    public Set<String> nestedTransitionLabels(UIPluginContext context, HierarchicalPetriNet hpn) {
		
		 
		//define the converted flat petri net as a global variable
		final Petrinet pn =new PetrinetImpl("Cloned flat PN");
		
		//clone the top-level pn to the new flat pn. this function also returns a mapping from the old pn tp the cloned pn. 
		ConvertPetriNet2PNwithLifeCycle.clonePetriNet(hpn.getPn(), pn);

		//HashMap<XEventClass, HierarchicalPetriNet> XEventClass2hpn =hpn.getXEventClass2hpn();
		HashMap<String, XEventClass> eventClassName2EventClass = new HashMap<>();
		//get all nested transition in the current level. 
		HashSet<String> nestedTransitionLabels = new HashSet<>();
		for(XEventClass xe: hpn.getXEventClass2hpn().keySet())
		{
			nestedTransitionLabels.add(xe.toString());
			eventClassName2EventClass.put(xe.toString(), xe);
		}
		
		
		HashSet<Transition> transitionSet = new HashSet<>();
		for(Transition t :pn.getTransitions())
		{
			if(!t.isInvisible()) // not invisible transition
			{
				transitionSet.add(t);
			}
		}
		for(Transition t: transitionSet)
		{
			//for normal transitions that are not nested transitions. 
			if(nestedTransitionLabels.contains(t.getLabel())) 
			{
				//recursively add sub-nested pn
				convertHPNtoPNRecursively(pn, t, hpn.getXEventClass2hpn().get(eventClassName2EventClass.get(t.toString())));
			}
			
		}
        return nestedTransitionLabels;
    }	
	
	
	public static void convertHPNtoPNRecursively(final Petrinet pn, Transition nestedTransition, HierarchicalPetriNet hpn)
	{
		Petrinet toplevel_pn =new PetrinetImpl("top-level pn");
		
		//clone the top-level pn to the new flat pn. 
		ConvertPetriNet2PNwithLifeCycle.clonePetriNet(hpn.getPn(), toplevel_pn);
		
		HashMap<String, XEventClass> eventClassName2EventClass = new HashMap<>();
		//get all nested transition in the toplevel_pn. 
		HashSet<String> nestedTransitionLabels = new HashSet<>();
		for(XEventClass xe: hpn.getXEventClass2hpn().keySet())
		{
			nestedTransitionLabels.add(xe.toString());
			eventClassName2EventClass.put(xe.toString(), xe);
		}
		
		HashSet<Transition> transitionSet = new HashSet<>();
		for(Transition t :pn.getTransitions())
		{
			if(!t.isInvisible()) // not invisible transition
			{
				transitionSet.add(t);
			}
		}
		
		for(Transition t: transitionSet)
		{
			//for normal transitions that are not nested transitions. 
			if(nestedTransitionLabels.contains(t.getLabel())) 
			{
				convertHPNtoPNRecursively(toplevel_pn, t, hpn.getXEventClass2hpn().get(eventClassName2EventClass.get(t.toString())));
			}
			
		}
	}

}
	  