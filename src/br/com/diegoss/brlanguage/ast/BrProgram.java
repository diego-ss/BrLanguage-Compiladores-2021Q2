package br.com.diegoss.brlanguage.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import br.com.diegoss.brlanguage.datastructures.BrSymbol;
import br.com.diegoss.brlanguage.datastructures.BrSymbolTable;


public class BrProgram {
	private BrSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;

	public void generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("using System;\n");
		str.append("namespace AprendendoProgramacao { \n");
		str.append("class Program{ \n");
		str.append("	static void Main(string[] args){\n ");
		str.append("      \n");
		for (BrSymbol symbol: varTable.getAll()) {
			str.append(symbol.generateJavaCode()+"\n");
		}
		for (AbstractCommand command: comandos) {
			str.append(command.generateCSharpCode()+"\n");
		}
		str.append("\n		}");
		str.append("\n	}");
		str.append("\n}");
		
		try {
			FileWriter fr = new FileWriter(new File("Program.cs"));
			fr.write(str.toString());
			fr.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	public BrSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(BrSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
