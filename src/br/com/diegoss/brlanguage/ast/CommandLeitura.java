package br.com.diegoss.brlanguage.ast;

import br.com.diegoss.brlanguage.datastructures.BrVariable;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private BrVariable var;
	
	public CommandLeitura (String id, BrVariable var) {
		this.id = id;
		this.var = var;
	}
	@Override
	public String generateCSharpCode() {
		String str = "";
		
		if(var.getType() == BrVariable.INTEIRO) {
			str = "int.Parse(Console.ReadLine());";
		} else if (var.getType() == BrVariable.DECIMAL) {
			str = "double.Parse(Console.ReadLine());";
		} else if (var.getType() == BrVariable.BOOLEANO){
			str = "bool.Parse(Console.ReadLine());";
		} else {
			str = "Console.ReadLine();";
		}
		
		// TODO Auto-generated method stub
		return id + " = " + str + "\n";
	}
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}
