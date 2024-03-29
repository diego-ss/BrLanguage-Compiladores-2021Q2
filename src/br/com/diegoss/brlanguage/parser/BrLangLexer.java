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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BrLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, OP=17, ATTR=18, 
		VIR=19, ACH=20, FCH=21, OPREL=22, BOOLEANO=23, ID=24, TEXTO=25, INTEIRO=26, 
		DECIMAL=27, VETOR_NUMEROS=28, WS=29, COMENTARIO_GERAL=30, COMENTARIO_LINHA=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "AP", "FP", "SC", "OP", "ATTR", "VIR", 
		"ACH", "FCH", "OPREL", "BOOLEANO", "ID", "TEXTO", "INTEIRO", "DECIMAL", 
		"VETOR_NUMEROS", "WS", "COMENTARIO_GERAL", "COMENTARIO_LINHA"
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


	public BrLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BrLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u011d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00c2"+
		"\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00cd\n\30\3\31"+
		"\3\31\7\31\u00d1\n\31\f\31\16\31\u00d4\13\31\3\32\3\32\7\32\u00d8\n\32"+
		"\f\32\16\32\u00db\13\32\3\32\3\32\3\33\3\33\3\34\6\34\u00e2\n\34\r\34"+
		"\16\34\u00e3\3\34\3\34\6\34\u00e8\n\34\r\34\16\34\u00e9\5\34\u00ec\n\34"+
		"\3\35\3\35\3\35\5\35\u00f1\n\35\3\35\3\35\3\35\5\35\u00f6\n\35\7\35\u00f8"+
		"\n\35\f\35\16\35\u00fb\13\35\5\35\u00fd\n\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\7\37\u0109\n\37\f\37\16\37\u010c\13\37\3\37"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3 \7 \u0117\n \f \16 \u011a\13 \3 \3 \3"+
		"\u010a\2!\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!\3\2\t\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62"+
		";\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u012c\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\3A\3\2\2\2\5J\3\2\2\2\7S\3\2\2\2\t[\3\2\2\2\13a\3\2\2\2\ri\3\2\2\2\17"+
		"r\3\2\2\2\21\177\3\2\2\2\23\u0084\3\2\2\2\25\u008c\3\2\2\2\27\u008f\3"+
		"\2\2\2\31\u0095\3\2\2\2\33\u009e\3\2\2\2\35\u00a8\3\2\2\2\37\u00aa\3\2"+
		"\2\2!\u00ac\3\2\2\2#\u00ae\3\2\2\2%\u00b0\3\2\2\2\'\u00b2\3\2\2\2)\u00b4"+
		"\3\2\2\2+\u00b6\3\2\2\2-\u00c1\3\2\2\2/\u00cc\3\2\2\2\61\u00ce\3\2\2\2"+
		"\63\u00d5\3\2\2\2\65\u00de\3\2\2\2\67\u00e1\3\2\2\29\u00ed\3\2\2\2;\u0100"+
		"\3\2\2\2=\u0104\3\2\2\2?\u0112\3\2\2\2AB\7r\2\2BC\7t\2\2CD\7q\2\2DE\7"+
		"i\2\2EF\7t\2\2FG\7c\2\2GH\7o\2\2HI\7c\2\2I\4\3\2\2\2JK\7h\2\2KL\7k\2\2"+
		"LM\7o\2\2MN\7r\2\2NO\7t\2\2OP\7q\2\2PQ\7i\2\2QR\7=\2\2R\6\3\2\2\2ST\7"+
		"k\2\2TU\7p\2\2UV\7v\2\2VW\7g\2\2WX\7k\2\2XY\7t\2\2YZ\7q\2\2Z\b\3\2\2\2"+
		"[\\\7v\2\2\\]\7g\2\2]^\7z\2\2^_\7v\2\2_`\7q\2\2`\n\3\2\2\2ab\7f\2\2bc"+
		"\7g\2\2cd\7e\2\2de\7k\2\2ef\7o\2\2fg\7c\2\2gh\7n\2\2h\f\3\2\2\2ij\7d\2"+
		"\2jk\7q\2\2kl\7q\2\2lm\7n\2\2mn\7g\2\2no\7c\2\2op\7p\2\2pq\7q\2\2q\16"+
		"\3\2\2\2rs\7x\2\2st\7g\2\2tu\7v\2\2uv\7q\2\2vw\7t\2\2wx\7P\2\2xy\7w\2"+
		"\2yz\7o\2\2z{\7g\2\2{|\7t\2\2|}\7q\2\2}~\7u\2\2~\20\3\2\2\2\177\u0080"+
		"\7n\2\2\u0080\u0081\7g\2\2\u0081\u0082\7k\2\2\u0082\u0083\7c\2\2\u0083"+
		"\22\3\2\2\2\u0084\u0085\7g\2\2\u0085\u0086\7u\2\2\u0086\u0087\7e\2\2\u0087"+
		"\u0088\7t\2\2\u0088\u0089\7g\2\2\u0089\u008a\7x\2\2\u008a\u008b\7c\2\2"+
		"\u008b\24\3\2\2\2\u008c\u008d\7u\2\2\u008d\u008e\7g\2\2\u008e\26\3\2\2"+
		"\2\u008f\u0090\7u\2\2\u0090\u0091\7g\2\2\u0091\u0092\7p\2\2\u0092\u0093"+
		"\7c\2\2\u0093\u0094\7q\2\2\u0094\30\3\2\2\2\u0095\u0096\7g\2\2\u0096\u0097"+
		"\7p\2\2\u0097\u0098\7s\2\2\u0098\u0099\7w\2\2\u0099\u009a\7c\2\2\u009a"+
		"\u009b\7p\2\2\u009b\u009c\7v\2\2\u009c\u009d\7q\2\2\u009d\32\3\2\2\2\u009e"+
		"\u009f\7r\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7e\2\2"+
		"\u00a2\u00a3\7q\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6"+
		"\7g\2\2\u00a6\u00a7\7t\2\2\u00a7\34\3\2\2\2\u00a8\u00a9\7*\2\2\u00a9\36"+
		"\3\2\2\2\u00aa\u00ab\7+\2\2\u00ab \3\2\2\2\u00ac\u00ad\7=\2\2\u00ad\""+
		"\3\2\2\2\u00ae\u00af\t\2\2\2\u00af$\3\2\2\2\u00b0\u00b1\7?\2\2\u00b1&"+
		"\3\2\2\2\u00b2\u00b3\7.\2\2\u00b3(\3\2\2\2\u00b4\u00b5\7}\2\2\u00b5*\3"+
		"\2\2\2\u00b6\u00b7\7\177\2\2\u00b7,\3\2\2\2\u00b8\u00c2\t\3\2\2\u00b9"+
		"\u00ba\7@\2\2\u00ba\u00c2\7?\2\2\u00bb\u00bc\7>\2\2\u00bc\u00c2\7?\2\2"+
		"\u00bd\u00be\7?\2\2\u00be\u00c2\7?\2\2\u00bf\u00c0\7#\2\2\u00c0\u00c2"+
		"\7?\2\2\u00c1\u00b8\3\2\2\2\u00c1\u00b9\3\2\2\2\u00c1\u00bb\3\2\2\2\u00c1"+
		"\u00bd\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2.\3\2\2\2\u00c3\u00c4\7v\2\2\u00c4"+
		"\u00c5\7t\2\2\u00c5\u00c6\7w\2\2\u00c6\u00cd\7g\2\2\u00c7\u00c8\7h\2\2"+
		"\u00c8\u00c9\7c\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cd"+
		"\7g\2\2\u00cc\u00c3\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cd\60\3\2\2\2\u00ce"+
		"\u00d2\t\4\2\2\u00cf\u00d1\t\5\2\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2"+
		"\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\62\3\2\2\2\u00d4\u00d2"+
		"\3\2\2\2\u00d5\u00d9\7$\2\2\u00d6\u00d8\t\5\2\2\u00d7\u00d6\3\2\2\2\u00d8"+
		"\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2"+
		"\2\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\7$\2\2\u00dd\64\3\2\2\2\u00de\u00df"+
		"\t\6\2\2\u00df\66\3\2\2\2\u00e0\u00e2\t\6\2\2\u00e1\u00e0\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00eb\3\2"+
		"\2\2\u00e5\u00e7\7\60\2\2\u00e6\u00e8\t\6\2\2\u00e7\u00e6\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2"+
		"\2\2\u00eb\u00e5\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec8\3\2\2\2\u00ed\u00fc"+
		"\7}\2\2\u00ee\u00f1\5\67\34\2\u00ef\u00f1\5\61\31\2\u00f0\u00ee\3\2\2"+
		"\2\u00f0\u00ef\3\2\2\2\u00f1\u00f9\3\2\2\2\u00f2\u00f5\7.\2\2\u00f3\u00f6"+
		"\5\67\34\2\u00f4\u00f6\5\61\31\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2"+
		"\2\u00f6\u00f8\3\2\2\2\u00f7\u00f2\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7"+
		"\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00f0\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\7\177"+
		"\2\2\u00ff:\3\2\2\2\u0100\u0101\t\7\2\2\u0101\u0102\3\2\2\2\u0102\u0103"+
		"\b\36\2\2\u0103<\3\2\2\2\u0104\u0105\7\61\2\2\u0105\u0106\7,\2\2\u0106"+
		"\u010a\3\2\2\2\u0107\u0109\13\2\2\2\u0108\u0107\3\2\2\2\u0109\u010c\3"+
		"\2\2\2\u010a\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010d\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010d\u010e\7,\2\2\u010e\u010f\7\61\2\2\u010f\u0110\3\2"+
		"\2\2\u0110\u0111\b\37\2\2\u0111>\3\2\2\2\u0112\u0113\7\61\2\2\u0113\u0114"+
		"\7\61\2\2\u0114\u0118\3\2\2\2\u0115\u0117\n\b\2\2\u0116\u0115\3\2\2\2"+
		"\u0117\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b"+
		"\3\2\2\2\u011a\u0118\3\2\2\2\u011b\u011c\b \2\2\u011c@\3\2\2\2\22\2\u00c1"+
		"\u00cc\u00d0\u00d2\u00d7\u00d9\u00e3\u00e9\u00eb\u00f0\u00f5\u00f9\u00fc"+
		"\u010a\u0118\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}