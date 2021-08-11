package br.com.diegoss.brlanguage.datastructures;

public abstract class BrSymbol {
	
	protected String name;
	
	public abstract String generateJavaCode();
	public BrSymbol(String name) {
		this.name = name;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "BrSymbol [name=" + name + "]";
	}
	
	
	

}
