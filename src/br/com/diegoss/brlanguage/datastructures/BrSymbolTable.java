package br.com.diegoss.brlanguage.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BrSymbolTable {
	
	private HashMap<String, BrSymbol> map;
	
	public BrSymbolTable() {
		map = new HashMap<String, BrSymbol>();
		
	}
	
	public void add(BrSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String symbolName) {
		return map.get(symbolName) != null;
	}
	
	public BrSymbol get(String symbolName) {
		return map.get(symbolName);
	}
	
	public ArrayList<BrSymbol> getAll(){
		ArrayList<BrSymbol> lista = new ArrayList<BrSymbol>();
		for (BrSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}

	
	
}
