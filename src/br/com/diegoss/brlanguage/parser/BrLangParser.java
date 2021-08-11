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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BrLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, OPREL=21, BOOLEANO=22, ID=23, TEXTO=24, INTEIRO=25, DECIMAL=26, 
		VETOR_NUMEROS=27, WS=28, COMENTARIO_GERAL=29, COMENTARIO_LINHA=30;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'inteiro'", "'texto'", "'decimal'", 
		"'booleano'", "'vetorNumeros'", "'leia'", "'escreva'", "'se'", "'senao'", 
		"'enquanto'", "'('", "')'", "';'", null, "'='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "BOOLEANO", 
		"ID", "TEXTO", "INTEIRO", "DECIMAL", "VETOR_NUMEROS", "WS", "COMENTARIO_GERAL", 
		"COMENTARIO_LINHA"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "BrLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private int _tipo;
		private String _varName;
		private String _varValue;
		private BrSymbolTable symbolTable = new BrSymbolTable();
		private BrSymbol symbol;
		private BrProgram program = new BrProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private String _exprLoop;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> listaLoop;
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new BrSemanticException("Símbolo "+id+" não declarado.");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void exibeVariaveisNaoUtilizadas(){
			for (BrSymbol s: symbolTable.getAll()){
				if(((BrVariable)s).getValue() == null){
					System.out.println("A variável " + s.getName() + " foi declarada mas não foi atribuída.");
				}		
			}
		}
		
		public void exibeVariaveis(){
			for (BrSymbol s: symbolTable.getAll()){
				System.out.println(s);
			}
		}
		
		public void verificarTipos(){
			for (BrSymbol s: symbolTable.getAll()){
				BrVariable var = (BrVariable)s;
				String value = var.getValue();
				
			 			 switch (var.getType()) {
					case BrVariable.BOOLEANO:
						//verificando se o valor é nulo ou não representa um booleano
						if(value != null && (value.contains("\"") || (!value.contains("true") && !value.contains("false")))){
							throw new BrSemanticException("A variável " + s.getName() + " só aceita valores booleanos"); }
						break;
					case BrVariable.TEXTO:
					//verificando se o valor é nulo ou não representa um texto
						if(value != null && value.chars().filter(ch->ch == '\"').count() != 2){
							var.setValue(null);
							throw new BrSemanticException("A variável " + s.getName() + " só aceita valores de texto"); 
						}
							
					break;

					case BrVariable.INTEIRO:
						if(value != null) {
							//quebrando expressão
							String[] splitValue = value.split("[+\\-\\/\\*\\ ]");
							//percorrendo lista de termos da expressão quebrada
							for(String str: splitValue) {
								try {
									//verificando se é um inteiro literal ou uma variável
									int x = Integer.valueOf(str.trim());
								} catch (Exception ex) {
									BrVariable find = (BrVariable)symbolTable.get(str);
									
									if(find == null) {
										throw new BrSemanticException("A variável " + s.getName() + " só aceita valores inteiros"); 
									}
									else {
										if(find.getType() != BrVariable.INTEIRO) {
											throw new BrSemanticException("A variável " + s.getName() + " só aceita valores inteiros"); 
										}
									}
								}
							}
						}			
					break;
					
					case BrVariable.DECIMAL:
						if(value != null) {
							String[] splitValue = value.split("[+\\-\\/\\*\\ ]");
							
							for(String str: splitValue) {
								try {
									double x = Double.valueOf(str.trim());
								} catch (Exception ex) {
									BrVariable find = (BrVariable)symbolTable.get(str);
									
									if(find == null) {
										throw new BrSemanticException("A variável " + s.getName() + " só aceita valores reais"); 
									}
									else {
										if(find.getType() != BrVariable.DECIMAL && find.getType() != BrVariable.INTEIRO) {
											throw new BrSemanticException("A variável " + s.getName() + " só aceita valores reais"); 
										}
									}
								}
							}
						}
					break;
					
					case BrVariable.VETOR_NUMEROS:
						if(value != null) {
							if(!value.contains("{") || !value.contains("}")) {
								throw new BrSemanticException("Vetor mal definido para a variável " + s.getName() + "."); 
							}
							
							String newValue = value.replaceAll("\\{","");
							newValue = newValue.replaceAll("\\}","");
							
							String[] splitValue = newValue.split(",");
							
							for(String str: splitValue) {
								try{
									double x = Double.valueOf(str.trim());
								} catch (Exception ex) {
									BrVariable find = (BrVariable)symbolTable.get(str);
									
									if(find == null) {
										throw new BrSemanticException("A variável '" + s.getName() + "' só aceita uma lista de números"); 
									}
									else {
										if(find.getType() != BrVariable.DECIMAL && find.getType() != BrVariable.INTEIRO) {
											throw new BrSemanticException("A variável " + s.getName() + " só aceita uma lista de números"); 
										}
									}
								}
							}										
						}
					break;
					
				default:
					throw new BrSemanticException("Declaração inválida!");
				}
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}

	public BrLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__0);
			setState(27);
			decl();
			setState(28);
			bloco();
			setState(29);
			match(T__1);
			  program.setVarTable(symbolTable);
			           	  program.setComandos(stack.pop());
			           	 
			           
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				declaravar();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(BrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BrLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(BrLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(BrLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(BrLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			tipo();
			setState(38);
			match(ID);

				                  _varName = _input.LT(-1).getText();
				                  _varValue = null;
				                  symbol = new BrVariable(_varName, _tipo, _varValue);
				                  if (!symbolTable.exists(_varName)){
				                     symbolTable.add(symbol);	
				                  }
				                  else{
				                  	 throw new BrSemanticException("Símbolo "+_varName+" já declarado.");
				                  }
			                    
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(40);
				match(VIR);
				setState(41);
				match(ID);

					                  _varName = _input.LT(-1).getText();
					                  _varValue = null;
					                  symbol = new BrVariable(_varName, _tipo, _varValue);
					                  if (!symbolTable.exists(_varName)){
					                     symbolTable.add(symbol);	
					                  }
					                  else{
					                  	 throw new BrSemanticException("Símbolo "+_varName+" já declarado.");
					                  }
				                    
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(T__2);
				 _tipo = BrVariable.INTEIRO;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(T__3);
				 _tipo = BrVariable.TEXTO;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				match(T__4);
				 _tipo = BrVariable.DECIMAL; 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				match(T__5);
				 _tipo = BrVariable.BOOLEANO; 
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(58);
				match(T__6);
				 _tipo = BrVariable.VETOR_NUMEROS; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);  
			          
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				cmd();
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				cmdleitura();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				cmdattrib();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				cmdselecao();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(72);
				cmdrepeticao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BrLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(BrLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(BrLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(BrLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__7);
			setState(76);
			match(AP);
			setState(77);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                        
			setState(79);
			match(FP);
			setState(80);
			match(SC);

			              	BrVariable var = (BrVariable)symbolTable.get(_readID);
			              	CommandLeitura cmd = new CommandLeitura(_readID, var);
			              	stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BrLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(BrLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(BrLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(BrLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__8);
			setState(84);
			match(AP);
			setState(85);
			match(ID);
			 verificaID(_input.LT(-1).getText());
				                  _writeID = _input.LT(-1).getText();
			                     
			setState(87);
			match(FP);
			setState(88);
			match(SC);

			               	  CommandEscrita cmd = new CommandEscrita(_writeID);
			               	  stack.peek().add(cmd);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BrLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(BrLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(BrLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                  	BrVariable var = (BrVariable)symbolTable.get(_exprID);
			                  
			setState(93);
			match(ATTR);
			 _exprContent = ""; 
			setState(95);
			expr();
			 var.setValue(_exprContent);
			setState(97);
			match(SC);

			               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			               	 stack.peek().add(cmd);
			               	 verificarTipos();
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BrLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(BrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BrLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(BrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(BrLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(BrLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(BrLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(BrLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(BrLangParser.FCH, i);
		}
		public TerminalNode INTEIRO() { return getToken(BrLangParser.INTEIRO, 0); }
		public TerminalNode DECIMAL() { return getToken(BrLangParser.DECIMAL, 0); }
		public TerminalNode TEXTO() { return getToken(BrLangParser.TEXTO, 0); }
		public TerminalNode BOOLEANO() { return getToken(BrLangParser.BOOLEANO, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__9);
			setState(101);
			match(AP);
			setState(102);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
			setState(104);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(106);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEANO) | (1L << ID) | (1L << TEXTO) | (1L << INTEIRO) | (1L << DECIMAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			_exprDecision += _input.LT(-1).getText(); 
			setState(108);
			match(FP);
			setState(109);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(111);
				cmd();
				}
				}
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << ID))) != 0) );
			setState(116);
			match(FCH);

			                       listaTrue = stack.pop();	
			                    
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(118);
				match(T__10);
				setState(119);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(121);
					cmd();
					}
					}
					setState(124); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << ID))) != 0) );
				}
				setState(126);
				match(FCH);

				                   		listaFalse = stack.pop();
				                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
				                   		stack.peek().add(cmd);
				                   	
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BrLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(BrLangParser.ID, 0); }
		public TerminalNode OPREL() { return getToken(BrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(BrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(BrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(BrLangParser.FCH, 0); }
		public TerminalNode DECIMAL() { return getToken(BrLangParser.DECIMAL, 0); }
		public TerminalNode BOOLEANO() { return getToken(BrLangParser.BOOLEANO, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterCmdrepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitCmdrepeticao(this);
		}
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__11);
			setState(132);
			match(AP);
			setState(133);
			match(ID);
			 _exprLoop = _input.LT(-1).getText(); 
			setState(135);
			match(OPREL);
			 _exprLoop += _input.LT(-1).getText(); 
			setState(137);
			_la = _input.LA(1);
			if ( !(_la==BOOLEANO || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprLoop += _input.LT(-1).getText(); 
			setState(139);
			match(FP);
			setState(140);
			match(ACH);
			 
										curThread = new ArrayList<AbstractCommand>(); 
			                      		stack.push(curThread);
			                    	
			{
			setState(143); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(142);
				cmd();
				}
				}
				setState(145); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << ID))) != 0) );
			}
			setState(147);
			match(FCH);

										listaLoop = stack.pop();
										CommandRepeticao cmd = new CommandRepeticao(_exprLoop, listaLoop);
										stack.peek().add(cmd);
									
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(BrLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(BrLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			termo();
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(151);
				match(OP);
				 _exprContent += _input.LT(-1).getText();
				setState(153);
				termo();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode BOOLEANO() { return getToken(BrLangParser.BOOLEANO, 0); }
		public TerminalNode ID() { return getToken(BrLangParser.ID, 0); }
		public TerminalNode TEXTO() { return getToken(BrLangParser.TEXTO, 0); }
		public TerminalNode INTEIRO() { return getToken(BrLangParser.INTEIRO, 0); }
		public TerminalNode DECIMAL() { return getToken(BrLangParser.DECIMAL, 0); }
		public TerminalNode VETOR_NUMEROS() { return getToken(BrLangParser.VETOR_NUMEROS, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_termo);
		try {
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				match(BOOLEANO);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(ID);
				 verificaID(_input.LT(-1).getText()); _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(163);
				match(TEXTO);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case INTEIRO:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
				match(INTEIRO);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				match(DECIMAL);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case VETOR_NUMEROS:
				enterOuterAlt(_localctx, 6);
				{
				setState(169);
				match(VETOR_NUMEROS);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00b0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3$\n\3\r\3"+
		"\16\3%\3\4\3\4\3\4\3\4\3\4\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5?\n\5\3\6\3\6\6\6C\n\6\r\6\16\6"+
		"D\3\7\3\7\3\7\3\7\3\7\5\7L\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13s\n\13\r\13\16"+
		"\13t\3\13\3\13\3\13\3\13\3\13\3\13\6\13}\n\13\r\13\16\13~\3\13\3\13\3"+
		"\13\5\13\u0084\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6"+
		"\f\u0092\n\f\r\f\16\f\u0093\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u009d\n\r"+
		"\f\r\16\r\u00a0\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\5\16\u00ae\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\2\4\3\2\30\34\4\2\30\30\34\34\2\u00b7\2\34\3\2\2\2\4#\3\2\2\2\6\'\3\2"+
		"\2\2\b>\3\2\2\2\n@\3\2\2\2\fK\3\2\2\2\16M\3\2\2\2\20U\3\2\2\2\22]\3\2"+
		"\2\2\24f\3\2\2\2\26\u0085\3\2\2\2\30\u0098\3\2\2\2\32\u00ad\3\2\2\2\34"+
		"\35\7\3\2\2\35\36\5\4\3\2\36\37\5\n\6\2\37 \7\4\2\2 !\b\2\1\2!\3\3\2\2"+
		"\2\"$\5\6\4\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\5\3\2\2\2\'(\5"+
		"\b\5\2()\7\31\2\2)/\b\4\1\2*+\7\24\2\2+,\7\31\2\2,.\b\4\1\2-*\3\2\2\2"+
		".\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\63\7\21"+
		"\2\2\63\7\3\2\2\2\64\65\7\5\2\2\65?\b\5\1\2\66\67\7\6\2\2\67?\b\5\1\2"+
		"89\7\7\2\29?\b\5\1\2:;\7\b\2\2;?\b\5\1\2<=\7\t\2\2=?\b\5\1\2>\64\3\2\2"+
		"\2>\66\3\2\2\2>8\3\2\2\2>:\3\2\2\2><\3\2\2\2?\t\3\2\2\2@B\b\6\1\2AC\5"+
		"\f\7\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\13\3\2\2\2FL\5\16\b\2"+
		"GL\5\20\t\2HL\5\22\n\2IL\5\24\13\2JL\5\26\f\2KF\3\2\2\2KG\3\2\2\2KH\3"+
		"\2\2\2KI\3\2\2\2KJ\3\2\2\2L\r\3\2\2\2MN\7\n\2\2NO\7\17\2\2OP\7\31\2\2"+
		"PQ\b\b\1\2QR\7\20\2\2RS\7\21\2\2ST\b\b\1\2T\17\3\2\2\2UV\7\13\2\2VW\7"+
		"\17\2\2WX\7\31\2\2XY\b\t\1\2YZ\7\20\2\2Z[\7\21\2\2[\\\b\t\1\2\\\21\3\2"+
		"\2\2]^\7\31\2\2^_\b\n\1\2_`\7\23\2\2`a\b\n\1\2ab\5\30\r\2bc\b\n\1\2cd"+
		"\7\21\2\2de\b\n\1\2e\23\3\2\2\2fg\7\f\2\2gh\7\17\2\2hi\7\31\2\2ij\b\13"+
		"\1\2jk\7\27\2\2kl\b\13\1\2lm\t\2\2\2mn\b\13\1\2no\7\20\2\2op\7\25\2\2"+
		"pr\b\13\1\2qs\5\f\7\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uv\3\2\2"+
		"\2vw\7\26\2\2w\u0083\b\13\1\2xy\7\r\2\2yz\7\25\2\2z|\b\13\1\2{}\5\f\7"+
		"\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0081\7\26\2\2\u0081\u0082\b\13\1\2\u0082\u0084\3\2\2\2\u0083x\3\2\2"+
		"\2\u0083\u0084\3\2\2\2\u0084\25\3\2\2\2\u0085\u0086\7\16\2\2\u0086\u0087"+
		"\7\17\2\2\u0087\u0088\7\31\2\2\u0088\u0089\b\f\1\2\u0089\u008a\7\27\2"+
		"\2\u008a\u008b\b\f\1\2\u008b\u008c\t\3\2\2\u008c\u008d\b\f\1\2\u008d\u008e"+
		"\7\20\2\2\u008e\u008f\7\25\2\2\u008f\u0091\b\f\1\2\u0090\u0092\5\f\7\2"+
		"\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\26\2\2\u0096\u0097\b\f\1\2"+
		"\u0097\27\3\2\2\2\u0098\u009e\5\32\16\2\u0099\u009a\7\22\2\2\u009a\u009b"+
		"\b\r\1\2\u009b\u009d\5\32\16\2\u009c\u0099\3\2\2\2\u009d\u00a0\3\2\2\2"+
		"\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\31\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a1\u00a2\7\30\2\2\u00a2\u00ae\b\16\1\2\u00a3\u00a4\7\31\2"+
		"\2\u00a4\u00ae\b\16\1\2\u00a5\u00a6\7\32\2\2\u00a6\u00ae\b\16\1\2\u00a7"+
		"\u00a8\7\33\2\2\u00a8\u00ae\b\16\1\2\u00a9\u00aa\7\34\2\2\u00aa\u00ae"+
		"\b\16\1\2\u00ab\u00ac\7\35\2\2\u00ac\u00ae\b\16\1\2\u00ad\u00a1\3\2\2"+
		"\2\u00ad\u00a3\3\2\2\2\u00ad\u00a5\3\2\2\2\u00ad\u00a7\3\2\2\2\u00ad\u00a9"+
		"\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\33\3\2\2\2\r%/>DKt~\u0083\u0093\u009e"+
		"\u00ad";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}