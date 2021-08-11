package br.com.diegoss.brlanguage.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand{
	private String expression;
	private ArrayList<AbstractCommand> listaLoop;

	public CommandRepeticao(String expression, ArrayList<AbstractCommand> ll) {
		this.expression = expression;
		this.listaLoop = ll;
	}
	@Override
	public String generateCSharpCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while (" + this.expression + ") {\n");
		for (AbstractCommand cmd: listaLoop) {
			str.append(cmd.generateCSharpCode());
		}
		str.append("\n}\n");
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + expression + ", listaLoop=" + listaLoop + "]";
		}
	
}
