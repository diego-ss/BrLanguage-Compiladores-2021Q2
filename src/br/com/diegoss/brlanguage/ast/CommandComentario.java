package br.com.diegoss.brlanguage.ast;

public class CommandComentario extends AbstractCommand {
	private String expr;
	
	public CommandComentario(String expr) {
		this.expr = expr;
	}
	@Override
	public String generateCSharpCode() {
		// TODO Auto-generated method stub
		return "//"+expr+";\n";
	}
	@Override
	public String toString() {
		return "CommandCOmentario [expr=" + expr + "]";
	}
}
