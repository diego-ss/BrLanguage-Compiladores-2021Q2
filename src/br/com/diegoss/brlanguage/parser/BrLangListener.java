// Generated from BrLang.g4 by ANTLR 4.7.1
package br.com.diegoss.brlanguage.parser;

	import br.com.diegoss.brlanguage.datastructures.BrSymbol;
	import br.com.diegoss.brlanguage.datastructures.BrVariable;
	import br.com.diegoss.brlanguage.datastructures.BrSymbolTable;
	import br.com.diegoss.brlanguage.exceptions.BrSemanticException;
	import br.com.diegoss.brlanguage.ast.BrProgram;
	import br.com.diegoss.brlanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BrLangParser}.
 */
public interface BrLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BrLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(BrLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(BrLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(BrLangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(BrLangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(BrLangParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(BrLangParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(BrLangParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(BrLangParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(BrLangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(BrLangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(BrLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(BrLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(BrLangParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(BrLangParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(BrLangParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(BrLangParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdattrib(BrLangParser.CmdattribContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdattrib(BrLangParser.CmdattribContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void enterCmdselecao(BrLangParser.CmdselecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void exitCmdselecao(BrLangParser.CmdselecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void enterCmdrepeticao(BrLangParser.CmdrepeticaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void exitCmdrepeticao(BrLangParser.CmdrepeticaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#cmdpercorrer}.
	 * @param ctx the parse tree
	 */
	void enterCmdpercorrer(BrLangParser.CmdpercorrerContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#cmdpercorrer}.
	 * @param ctx the parse tree
	 */
	void exitCmdpercorrer(BrLangParser.CmdpercorrerContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(BrLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(BrLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BrLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(BrLangParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BrLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(BrLangParser.TermoContext ctx);
}