package br.com.diegoss.brlanguage.ast;

public class CommandAtribuicao extends AbstractCommand{

	private String id;
	private String expr;
	
	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	@Override
	public String generateCSharpCode() {
		if(expr.contains("{")) {
			return id + " = new int[]" + expr +";\n";
		}
		return id + " = "+expr+";\n";
	}
	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}
	
	

}
