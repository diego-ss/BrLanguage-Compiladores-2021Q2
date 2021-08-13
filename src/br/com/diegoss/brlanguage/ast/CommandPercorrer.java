package br.com.diegoss.brlanguage.ast;

import java.util.ArrayList;

public class CommandPercorrer extends AbstractCommand{
	private String exprId;
	private String exprDeclaracao;
	private String exprComparacao;
	private String exprPasso;
	private ArrayList<AbstractCommand> listaLoop;

	public CommandPercorrer(String exprId, String exprDeclaracao, String exprComparacao, String exprPasso, ArrayList<AbstractCommand> ll) {
		this.exprDeclaracao = exprDeclaracao;
		this.exprComparacao = exprComparacao;
		this.exprPasso = exprPasso;
		this.listaLoop = ll;
		this.exprId = exprId;
	}
	@Override
	public String generateCSharpCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("for (" + exprId + " = " + this.exprDeclaracao + "; " + this.exprComparacao + " ; " + this.exprId + " = " +this.exprId + " + " + this.exprPasso + ") {\n");
		for (AbstractCommand cmd: listaLoop) {
			str.append(cmd.generateCSharpCode());
		}
		str.append("\n}\n");
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandPercorrer [condition=for(double " + exprId + " = " + this.exprDeclaracao + "; " + this.exprComparacao + " ; " + this.exprId + " = " +this.exprId + " + " + this.exprPasso + "), listaLoop=" + listaLoop + "]";
		}
}
