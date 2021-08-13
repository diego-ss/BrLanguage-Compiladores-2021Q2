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
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, OP=17, ATTR=18, 
		VIR=19, ACH=20, FCH=21, OPREL=22, BOOLEANO=23, ID=24, TEXTO=25, INTEIRO=26, 
		DECIMAL=27, VETOR_NUMEROS=28, WS=29, COMENTARIO_GERAL=30, COMENTARIO_LINHA=31;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_cmdpercorrer = 11, RULE_expr = 12, 
		RULE_termo = 13;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "cmdpercorrer", "expr", "termo"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'inteiro'", "'texto'", "'decimal'", 
		"'booleano'", "'vetorNumeros'", "'leia'", "'escreva'", "'se'", "'senao'", 
		"'enquanto'", "'percorrer'", "'('", "')'", "';'", null, "'='", "','", 
		"'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", 
		"BOOLEANO", "ID", "TEXTO", "INTEIRO", "DECIMAL", "VETOR_NUMEROS", "WS", 
		"COMENTARIO_GERAL", "COMENTARIO_LINHA"
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
		private String _exprPercorrerDeclaracao;
		private String _exprPercorrerComparacao;
		private String _exprPercorrerPasso;
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
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
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
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declaravar();
				}
				}
				setState(37); 
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
			setState(39);
			tipo();
			setState(40);
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
			                    
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(42);
				match(VIR);
				setState(43);
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
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(T__2);
				 _tipo = BrVariable.INTEIRO;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(T__3);
				 _tipo = BrVariable.TEXTO;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(T__4);
				 _tipo = BrVariable.DECIMAL; 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(T__5);
				 _tipo = BrVariable.BOOLEANO; 
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
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
			          
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				cmd();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
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
		public CmdpercorrerContext cmdpercorrer() {
			return getRuleContext(CmdpercorrerContext.class,0);
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
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				cmdleitura();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				cmdattrib();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				cmdselecao();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				cmdrepeticao();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 6);
				{
				setState(75);
				cmdpercorrer();
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
			setState(78);
			match(T__7);
			setState(79);
			match(AP);
			setState(80);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                        
			setState(82);
			match(FP);
			setState(83);
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
			setState(86);
			match(T__8);
			setState(87);
			match(AP);
			setState(88);
			match(ID);
			 verificaID(_input.LT(-1).getText());
				                  _writeID = _input.LT(-1).getText();
			                     
			setState(90);
			match(FP);
			setState(91);
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
			setState(94);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                  	BrVariable var = (BrVariable)symbolTable.get(_exprID);
			                  
			setState(96);
			match(ATTR);
			 _exprContent = ""; 
			setState(98);
			expr();
			 var.setValue(_exprContent);
			setState(100);
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
			setState(103);
			match(T__9);
			setState(104);
			match(AP);
			setState(105);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
			setState(107);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(109);
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
			setState(111);
			match(FP);
			setState(112);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(115); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(114);
				cmd();
				}
				}
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(119);
			match(FCH);

			                       listaTrue = stack.pop();	
			                    
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(121);
				match(T__10);
				setState(122);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(124);
					cmd();
					}
					}
					setState(127); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
				}
				setState(129);
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
		public List<TerminalNode> ID() { return getTokens(BrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BrLangParser.ID, i);
		}
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
			setState(134);
			match(T__11);
			setState(135);
			match(AP);
			setState(136);
			match(ID);
			 _exprLoop = _input.LT(-1).getText(); 
			setState(138);
			match(OPREL);
			 _exprLoop += _input.LT(-1).getText(); 
			setState(140);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEANO) | (1L << ID) | (1L << DECIMAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprLoop += _input.LT(-1).getText(); 
			setState(142);
			match(FP);
			setState(143);
			match(ACH);
			 
										curThread = new ArrayList<AbstractCommand>(); 
			                      		stack.push(curThread);
			                    	
			{
			setState(146); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(145);
				cmd();
				}
				}
				setState(148); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			}
			setState(150);
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

	public static class CmdpercorrerContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BrLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(BrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BrLangParser.ID, i);
		}
		public TerminalNode ATTR() { return getToken(BrLangParser.ATTR, 0); }
		public List<TerminalNode> SC() { return getTokens(BrLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(BrLangParser.SC, i);
		}
		public TerminalNode OPREL() { return getToken(BrLangParser.OPREL, 0); }
		public List<TerminalNode> DECIMAL() { return getTokens(BrLangParser.DECIMAL); }
		public TerminalNode DECIMAL(int i) {
			return getToken(BrLangParser.DECIMAL, i);
		}
		public TerminalNode FP() { return getToken(BrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(BrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(BrLangParser.FCH, 0); }
		public List<TerminalNode> INTEIRO() { return getTokens(BrLangParser.INTEIRO); }
		public TerminalNode INTEIRO(int i) {
			return getToken(BrLangParser.INTEIRO, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdpercorrerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdpercorrer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).enterCmdpercorrer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BrLangListener ) ((BrLangListener)listener).exitCmdpercorrer(this);
		}
	}

	public final CmdpercorrerContext cmdpercorrer() throws RecognitionException {
		CmdpercorrerContext _localctx = new CmdpercorrerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdpercorrer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__12);
			setState(154);
			match(AP);
			setState(155);
			match(ID);

										verificaID(_input.LT(-1).getText());
			                    		_exprID = _input.LT(-1).getText();
			                    		BrVariable var = (BrVariable)symbolTable.get(_exprID);
									
			setState(157);
			match(ATTR);
			setState(158);
			_la = _input.LA(1);
			if ( !(_la==INTEIRO || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 
										_exprPercorrerDeclaracao = _input.LT(-1).getText(); 
										var.setValue(_input.LT(-1).getText());
									
			setState(160);
			match(SC);
			setState(161);
			match(ID);
			 _exprPercorrerComparacao = _input.LT(-1).getText(); 
			setState(163);
			match(OPREL);
			 _exprPercorrerComparacao += _input.LT(-1).getText(); 
			setState(165);
			match(DECIMAL);
			 _exprPercorrerComparacao += _input.LT(-1).getText(); 
			setState(167);
			match(SC);
			setState(168);
			_la = _input.LA(1);
			if ( !(_la==INTEIRO || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprPercorrerPasso = _input.LT(-1).getText(); 
			setState(170);
			match(FP);
			setState(171);
			match(ACH);
			 
										curThread = new ArrayList<AbstractCommand>(); 
			                      		stack.push(curThread);
			                    	
			{
			setState(174); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(173);
				cmd();
				}
				}
				setState(176); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			}
			setState(178);
			match(FCH);

										listaLoop = stack.pop();
										CommandPercorrer cmd = new CommandPercorrer(_exprID, _exprPercorrerDeclaracao, _exprPercorrerComparacao, _exprPercorrerPasso, listaLoop);
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
		enterRule(_localctx, 24, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			termo();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(182);
				match(OP);
				 _exprContent += _input.LT(-1).getText();
				setState(184);
				termo();
				}
				}
				setState(189);
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
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				match(BOOLEANO);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				match(ID);
				 verificaID(_input.LT(-1).getText()); _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				match(TEXTO);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case INTEIRO:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				match(INTEIRO);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(198);
				match(DECIMAL);
				 _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case VETOR_NUMEROS:
				enterOuterAlt(_localctx, 6);
				{
				setState(200);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00cf\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6"+
		"\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5A\n\5\3\6\3\6\6\6"+
		"E\n\6\r\6\16\6F\3\7\3\7\3\7\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6"+
		"\13v\n\13\r\13\16\13w\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u0080\n\13\r"+
		"\13\16\13\u0081\3\13\3\13\3\13\5\13\u0087\n\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0095\n\f\r\f\16\f\u0096\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\6\r\u00b1\n\r\r\r\16\r\u00b2\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\7\16\u00bc\n\16\f\16\16\16\u00bf\13\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00cd\n\17\3\17\2\2\20\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\2\5\3\2\31\35\4\2\31\32\35\35\3\2\34\35\2\u00d7"+
		"\2\36\3\2\2\2\4%\3\2\2\2\6)\3\2\2\2\b@\3\2\2\2\nB\3\2\2\2\fN\3\2\2\2\16"+
		"P\3\2\2\2\20X\3\2\2\2\22`\3\2\2\2\24i\3\2\2\2\26\u0088\3\2\2\2\30\u009b"+
		"\3\2\2\2\32\u00b7\3\2\2\2\34\u00cc\3\2\2\2\36\37\7\3\2\2\37 \5\4\3\2 "+
		"!\5\n\6\2!\"\7\4\2\2\"#\b\2\1\2#\3\3\2\2\2$&\5\6\4\2%$\3\2\2\2&\'\3\2"+
		"\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2)*\5\b\5\2*+\7\32\2\2+\61\b\4\1\2"+
		",-\7\25\2\2-.\7\32\2\2.\60\b\4\1\2/,\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2"+
		"\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7\22\2\2\65\7\3\2\2\2"+
		"\66\67\7\5\2\2\67A\b\5\1\289\7\6\2\29A\b\5\1\2:;\7\7\2\2;A\b\5\1\2<=\7"+
		"\b\2\2=A\b\5\1\2>?\7\t\2\2?A\b\5\1\2@\66\3\2\2\2@8\3\2\2\2@:\3\2\2\2@"+
		"<\3\2\2\2@>\3\2\2\2A\t\3\2\2\2BD\b\6\1\2CE\5\f\7\2DC\3\2\2\2EF\3\2\2\2"+
		"FD\3\2\2\2FG\3\2\2\2G\13\3\2\2\2HO\5\16\b\2IO\5\20\t\2JO\5\22\n\2KO\5"+
		"\24\13\2LO\5\26\f\2MO\5\30\r\2NH\3\2\2\2NI\3\2\2\2NJ\3\2\2\2NK\3\2\2\2"+
		"NL\3\2\2\2NM\3\2\2\2O\r\3\2\2\2PQ\7\n\2\2QR\7\20\2\2RS\7\32\2\2ST\b\b"+
		"\1\2TU\7\21\2\2UV\7\22\2\2VW\b\b\1\2W\17\3\2\2\2XY\7\13\2\2YZ\7\20\2\2"+
		"Z[\7\32\2\2[\\\b\t\1\2\\]\7\21\2\2]^\7\22\2\2^_\b\t\1\2_\21\3\2\2\2`a"+
		"\7\32\2\2ab\b\n\1\2bc\7\24\2\2cd\b\n\1\2de\5\32\16\2ef\b\n\1\2fg\7\22"+
		"\2\2gh\b\n\1\2h\23\3\2\2\2ij\7\f\2\2jk\7\20\2\2kl\7\32\2\2lm\b\13\1\2"+
		"mn\7\30\2\2no\b\13\1\2op\t\2\2\2pq\b\13\1\2qr\7\21\2\2rs\7\26\2\2su\b"+
		"\13\1\2tv\5\f\7\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz"+
		"\7\27\2\2z\u0086\b\13\1\2{|\7\r\2\2|}\7\26\2\2}\177\b\13\1\2~\u0080\5"+
		"\f\7\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\7\27\2\2\u0084\u0085\b\13\1\2"+
		"\u0085\u0087\3\2\2\2\u0086{\3\2\2\2\u0086\u0087\3\2\2\2\u0087\25\3\2\2"+
		"\2\u0088\u0089\7\16\2\2\u0089\u008a\7\20\2\2\u008a\u008b\7\32\2\2\u008b"+
		"\u008c\b\f\1\2\u008c\u008d\7\30\2\2\u008d\u008e\b\f\1\2\u008e\u008f\t"+
		"\3\2\2\u008f\u0090\b\f\1\2\u0090\u0091\7\21\2\2\u0091\u0092\7\26\2\2\u0092"+
		"\u0094\b\f\1\2\u0093\u0095\5\f\7\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0099\7\27\2\2\u0099\u009a\b\f\1\2\u009a\27\3\2\2\2\u009b\u009c\7\17"+
		"\2\2\u009c\u009d\7\20\2\2\u009d\u009e\7\32\2\2\u009e\u009f\b\r\1\2\u009f"+
		"\u00a0\7\24\2\2\u00a0\u00a1\t\4\2\2\u00a1\u00a2\b\r\1\2\u00a2\u00a3\7"+
		"\22\2\2\u00a3\u00a4\7\32\2\2\u00a4\u00a5\b\r\1\2\u00a5\u00a6\7\30\2\2"+
		"\u00a6\u00a7\b\r\1\2\u00a7\u00a8\7\35\2\2\u00a8\u00a9\b\r\1\2\u00a9\u00aa"+
		"\7\22\2\2\u00aa\u00ab\t\4\2\2\u00ab\u00ac\b\r\1\2\u00ac\u00ad\7\21\2\2"+
		"\u00ad\u00ae\7\26\2\2\u00ae\u00b0\b\r\1\2\u00af\u00b1\5\f\7\2\u00b0\u00af"+
		"\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b5\7\27\2\2\u00b5\u00b6\b\r\1\2\u00b6\31\3\2\2"+
		"\2\u00b7\u00bd\5\34\17\2\u00b8\u00b9\7\23\2\2\u00b9\u00ba\b\16\1\2\u00ba"+
		"\u00bc\5\34\17\2\u00bb\u00b8\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3"+
		"\2\2\2\u00bd\u00be\3\2\2\2\u00be\33\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0"+
		"\u00c1\7\31\2\2\u00c1\u00cd\b\17\1\2\u00c2\u00c3\7\32\2\2\u00c3\u00cd"+
		"\b\17\1\2\u00c4\u00c5\7\33\2\2\u00c5\u00cd\b\17\1\2\u00c6\u00c7\7\34\2"+
		"\2\u00c7\u00cd\b\17\1\2\u00c8\u00c9\7\35\2\2\u00c9\u00cd\b\17\1\2\u00ca"+
		"\u00cb\7\36\2\2\u00cb\u00cd\b\17\1\2\u00cc\u00c0\3\2\2\2\u00cc\u00c2\3"+
		"\2\2\2\u00cc\u00c4\3\2\2\2\u00cc\u00c6\3\2\2\2\u00cc\u00c8\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cd\35\3\2\2\2\16\'\61@FNw\u0081\u0086\u0096\u00b2\u00bd"+
		"\u00cc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}