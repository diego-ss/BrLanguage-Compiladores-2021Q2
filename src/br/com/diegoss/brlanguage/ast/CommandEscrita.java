package br.com.diegoss.brlanguage.ast;

public class CommandEscrita extends AbstractCommand {

	private String id;
	
	public CommandEscrita(String id) {
		this.id = id;
	}
	@Override
	public String generateCSharpCode() {
		// TODO Auto-generated method stub
		return "Console.WriteLine("+id+");\n";
	}
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}
	

}
