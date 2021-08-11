package br.com.diegoss.brlanguage.datastructures;

public class BrVariable extends BrSymbol {
	
	public static final int INTEIRO=0;
	public static final int TEXTO  =1;
	public static final int DECIMAL  =2;
	public static final int BOOLEANO  =3;
	public static final int VETOR_NUMEROS  =4;
	
	private int type;
	private String value;
	
	public BrVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BrVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	public String generateJavaCode() {
       String str;
       if (type == INTEIRO) {
    	   str = "int ";
       }
       else if (type == TEXTO){
    	   str = "string ";
       }
       else if (type == DECIMAL) {
    	   str = "double ";
       }
       else if (type == VETOR_NUMEROS) {
    	   str = "int[] ";
       }
       else {
    	   str = "bool ";
       }
       
       return str + " "+super.name+";";
	}
	
	

}
