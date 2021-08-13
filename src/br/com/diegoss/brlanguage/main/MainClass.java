package br.com.diegoss.brlanguage.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.com.diegoss.brlanguage.exceptions.BrSemanticException;
import br.com.diegoss.brlanguage.parser.BrLangLexer;
import br.com.diegoss.brlanguage.parser.BrLangParser;


public class MainClass {

	/* esta é a classe que é responsável por criar a interação com o usuário
	 * instanciando nosso parser e apontando para o arquivo fonte
	 * 
	 * Arquivo fonte: extensao .brl
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BrLangLexer lexer;
			BrLangParser parser;
			
			// leio o arquivo "input.brl" e isso é entrada para o Analisador Lexico
			lexer = new BrLangLexer(CharStreams.fromFileName("input.brl"));
			
			// crio um "fluxo de tokens" para passar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// crio meu parser a partir desse tokenStream
			parser = new BrLangParser(tokenStream);	
			parser.prog();
					
		    parser.exibeComandos();
			parser.exibeVariaveis();
			parser.exibeVariaveisNaoUtilizadas();
			parser.verificarTipos();
			parser.generateCode();
			
			System.out.println("Compilation Successful");
			
		}
		catch(BrSemanticException ex) {
			System.err.println("Erro Semântico: "+ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("Erro Genérico: "+ex.getMessage());
		}
	}

}
