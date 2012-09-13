package rlfa;

import java.util.HashMap;
import java.util.TreeSet;

public class FiniteAutomaton {

	private TreeSet<Integer> states;
	private TreeSet<String> alphabet;
	private HashMap<Integer, HashMap<String, Integer>> transitions;
	private Integer startState;
	private TreeSet<Integer> acceptingStates;
	
	public static void main(String[] args) {
		
		TreeSet<Integer> states = new TreeSet<Integer>();
		states.add(0);
		states.add(1);
		states.add(2);
		states.add(3);
		
		Integer startState = 0;
		TreeSet<Integer> acceptingStates = new TreeSet<Integer>();
		acceptingStates.add(0);
		acceptingStates.add(1);
		acceptingStates.add(2);
		
		TreeSet<String> alphabet = new TreeSet<String>();
		alphabet.add("a");
		alphabet.add("b");
		
		HashMap<Integer, HashMap<String, Integer>> transitions = new HashMap<Integer, HashMap<String, Integer>>();
		
		HashMap<String, Integer> transitions0 = new HashMap<String, Integer>();
		transitions0.put("a", 1);
		transitions0.put("b", 3);
		
		HashMap<String, Integer> transitions1 = new HashMap<String, Integer>();
		transitions1.put("a", 2);
		transitions1.put("b", 3);
		
		HashMap<String, Integer> transitions2 = new HashMap<String, Integer>();
		transitions2.put("a", 2);
		transitions2.put("b", 3);
		
		HashMap<String, Integer> transitions3 = new HashMap<String, Integer>();
		transitions3.put("a", 3);
		transitions3.put("b", 3);
		
		transitions.put(0, transitions0);
		transitions.put(1, transitions1);
		transitions.put(2, transitions2);
		transitions.put(3, transitions3);
		
		FiniteAutomaton fa = new FiniteAutomaton(states, alphabet, transitions, startState, acceptingStates);
		System.out.println(fa.checkString("a"));
		System.out.println(fa.complement().checkString("a"));
	}
	
	public FiniteAutomaton(
			TreeSet<Integer> pStates,
			TreeSet<String> pAlphabet,
			HashMap<Integer, HashMap<String, Integer>> pTransitions,
			Integer pStartState,
			TreeSet<Integer> pAcceptingStates) {
		
		states = pStates;
		alphabet = pAlphabet;
		transitions = pTransitions;
		startState = pStartState;
		acceptingStates = pAcceptingStates;
		
	}
	
	public boolean checkString(String string) {
		return checkString(string, startState);
	}
	
	private boolean checkString(String string, Integer currentState) {
		
		if (string.length() <= 0) {
			if (acceptingStates.contains(currentState)) {
				return true;
			} else {
				return false;
			}
		}
		
		Integer newState;
		try {
			newState = transitions.get(currentState).get(Character.toString(string.charAt(0)));
		} catch (NullPointerException e) {
			return false;
		}
		String newString = string.substring(1);
		
		return checkString(newString, newState);
		
	}
	
	public FiniteAutomaton complement() {
		
		TreeSet<Integer> nonAcceptingStates = new TreeSet<Integer>();
		
		for (Integer s : states) {
			
			if(!acceptingStates.contains(s)) {
				nonAcceptingStates.add(s);
			}
			
		}
		
		FiniteAutomaton newFA = new FiniteAutomaton(states, alphabet, transitions, startState, nonAcceptingStates);
		return newFA;
		
	}
	
}
