grammar BrLang;

@header{
	import br.com.diegoss.brlanguage.datastructures.BrSymbol;
	import br.com.diegoss.brlanguage.datastructures.BrVariable;
	import br.com.diegoss.brlanguage.datastructures.BrSymbolTable;
	import br.com.diegoss.brlanguage.exceptions.BrSemanticException;
	import br.com.diegoss.brlanguage.ast.BrProgram;
	import br.com.diegoss.brlanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
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
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	 
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
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
              (  VIR 
              	 ID {
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
              )* 
               SC
           ;
           
tipo       : 'inteiro' { _tipo = BrVariable.INTEIRO;  }
           | 'texto'  { _tipo = BrVariable.TEXTO;  }
           | 'decimal' { _tipo = BrVariable.DECIMAL; }
           | 'booleano' { _tipo = BrVariable.BOOLEANO; }
           | 'vetorNumeros' { _tipo = BrVariable.VETOR_NUMEROS; }
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		

cmd		:  cmdleitura  
 		|  cmdescrita 
 		|  cmdattrib
 		|  cmdselecao
 		|  cmdrepeticao 
 		|  cmdpercorrer
		;
		
cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        } 
                     FP 
                     SC 
                     
              {
              	BrVariable var = (BrVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdescrita	: 'escreva' 
                 AP 
                 ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
                     } 
                 FP 
                 SC
               {
               	  CommandEscrita cmd = new CommandEscrita(_writeID);
               	  stack.peek().add(cmd);
               }
			;
			
cmdattrib	:  ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                  	BrVariable var = (BrVariable)symbolTable.get(_exprID);
                  } 
               ATTR { _exprContent = ""; } 
               expr { var.setValue(_exprContent);}
               SC
               {
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 stack.peek().add(cmd);
               	 verificarTipos();
               }
			;
			
			
cmdselecao  :  'se' AP
                    ID    { _exprDecision = _input.LT(-1).getText(); }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | INTEIRO | DECIMAL | TEXTO | BOOLEANO) {_exprDecision += _input.LT(-1).getText(); }
                    FP 
                    ACH 
                    { curThread = new ArrayList<AbstractCommand>(); 
                      stack.push(curThread);
                    }
                    (cmd)+ 
                    
                    FCH 
                    {
                       listaTrue = stack.pop();	
                    } 
                   ('senao' 
                   	 ACH
                   	 {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 } 
                   	(cmd+) 
                   	FCH
                   	{
                   		listaFalse = stack.pop();
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;
            
            
cmdrepeticao : 'enquanto' AP
						ID { _exprLoop = _input.LT(-1).getText(); }
						OPREL { _exprLoop += _input.LT(-1).getText(); }
						( DECIMAL | BOOLEANO | ID) { _exprLoop += _input.LT(-1).getText(); }
						FP
						ACH 
						{ 
							curThread = new ArrayList<AbstractCommand>(); 
                      		stack.push(curThread);
                    	}
						(cmd+)
						FCH 
						{
							listaLoop = stack.pop();
							CommandRepeticao cmd = new CommandRepeticao(_exprLoop, listaLoop);
							stack.peek().add(cmd);
						}
                    ;
			
cmdpercorrer: 'percorrer' AP
						ID {
							verificaID(_input.LT(-1).getText());
                    		_exprID = _input.LT(-1).getText();
                    		BrVariable var = (BrVariable)symbolTable.get(_exprID);
						}
						ATTR 
						( DECIMAL | INTEIRO ) { 
							_exprPercorrerDeclaracao = _input.LT(-1).getText(); 
							var.setValue(_input.LT(-1).getText());
						}
						SC 
						ID { _exprPercorrerComparacao = _input.LT(-1).getText(); }
						OPREL { _exprPercorrerComparacao += _input.LT(-1).getText(); }
						DECIMAL { _exprPercorrerComparacao += _input.LT(-1).getText(); }
						SC
						( DECIMAL | INTEIRO ) { _exprPercorrerPasso = _input.LT(-1).getText(); }
						FP
						ACH 
						{ 
							curThread = new ArrayList<AbstractCommand>(); 
                      		stack.push(curThread);
                    	}
						(cmd+)
						FCH 
						{
							listaLoop = stack.pop();
							CommandPercorrer cmd = new CommandPercorrer(_exprID, _exprPercorrerDeclaracao, _exprPercorrerComparacao, _exprPercorrerPasso, listaLoop);
							stack.peek().add(cmd);
						}
			;

			
expr		:  termo ( 
	             OP  { _exprContent += _input.LT(-1).getText();}
	            termo
	            )*
			;
			
termo		: 
			 BOOLEANO { _exprContent += _input.LT(-1).getText(); }
			| ID { verificaID(_input.LT(-1).getText()); _exprContent += _input.LT(-1).getText(); } 
            | TEXTO { _exprContent += _input.LT(-1).getText(); }     		
            | INTEIRO { _exprContent += _input.LT(-1).getText(); }   
            | DECIMAL { _exprContent += _input.LT(-1).getText(); }   
            | VETOR_NUMEROS { _exprContent += _input.LT(-1).getText(); }      		
			;
			
AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR : '='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;
	 
	 
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;

BOOLEANO : 'true' | 'false'	
;

ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
TEXTO : '"' ([a-z] | [A-Z] | [0-9])* '"'
;

INTEIRO	: [0-9]
		;
		
DECIMAL	: [0-9]+ ('.' [0-9]+)?
;

VETOR_NUMEROS : '{' ( (DECIMAL | ID) ( ',' (DECIMAL | ID))* )? '}'
;			

WS	: (' ' | '\t' | '\n' | '\r') -> skip;


COMENTARIO_GERAL: '/*' .*? '*/' -> skip
		;

COMENTARIO_LINHA: '//' ~[\r\n]* -> skip
		;