package classesSeguradora;

public class Validacao {
	private String cpf;
	private String cnpj;
	private final int op;

	//Construtor
	Validacao(String cpf, String cnpj, int op){
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.op = op;
	}
	
	//Getters e Setters
	public String getCPF() {
		return cpf;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCNPJ() {
		return cnpj;
	}
	
	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public int getOP() {
		return op;
	}
	
		public boolean verificarValidade(){
			boolean validade;
		if (this.op == 0)
			validade = validarCPF(this.cpf);
		else
			validade = validarCNPJ(this.cnpj);
		if (validade == false)
			System.out.println("Erro nas credenciais, tente novamente . . .");
		return validade;
		}
		
		public boolean validarCPF(String cpf) {
			//Remover Caracteres Nao Numericos
			String numeros = cpf.replaceAll("[^0-9]", "");
			//Verificar Se Contem 11 Digitos 
			if (numeros.length() != 11)
				return false;
			//Verificar Se Todos os Digitos Sao Iguais
			if (repetidos(numeros))
				return false;
			//Calculando Algoritmo de Verificacao
			if (algoritmoCPF(numeros))
				return true;
			return false;
		}
		
		public boolean repetidos(String numeros) {
			int l = numeros.length();
			for (int i = 1; i < l; i++)
				if (numeros.charAt(0) != numeros.charAt(i))
					return false;
			return true;
		}
		
		public boolean algoritmoCPF(String numeros) {
			//Verificando 10o Digito
			int soma = 0;
			int mult = 10;
			for (int i = 0; i < 9; i++) { //Subtrai 48 de acordo com tabela ASCII 
				soma += mult * (numeros.charAt(i) - 48); //multiplica 1os 9 digitos por multiplicador descendo de 10-2 
				mult--;
			}
			int resto = (soma % 11); //resto da soma por 11
			if (resto <= 2) {
				if (numeros.charAt(9) - 48 != 0) //se resto eh menor ou igual do que 2, 10o digito deve ser 0
					return false;
			}
			else if (numeros.charAt(9) - 48 != 11 - resto) //se resto eh maior do que 2, 10o digito deve ser 11 menos resto
				return false;
			
			//Verificando 11o Digito
			soma = 0;
			mult = 11;
			for (int i = 0; i < 10; i++) { //Mesmo processo, porem agora indo ate o 10o digito e multiplicador desce de 11-2
				soma += mult * (numeros.charAt(i) - 48);
				mult--;
			}
			resto = (soma % 11);
			if (resto <= 2) {
				if (numeros.charAt(10) - 48 != 0)
					return false;
			}
			else if (numeros.charAt(10) - 48 != 11 - resto)
				return false;
			return true;
			}
		
		public boolean validarCNPJ(String cnpj) {
			//Remover Caracteres Nao Numericos
			String numeros = cnpj.replaceAll("[^0-9]", "");
			//Verificar Se Contem 14 Digitos 
			if (numeros.length() != 14)
				return false;
			//Verificar Se Todos os Digitos Sao Iguais
			if (repetidos(numeros))
				return false;
			//Calculando Algoritmo de Verificacao
			if (algoritmoCNPJ(numeros))
				return true;
			return false;
		}
		
		public boolean algoritmoCNPJ(String numeros) {
			//Verificando 13o Digito
			int soma = 0;
			int[] mult13 = new int[] {5,4,3,2,9,8,7,6,5,4,3,2};
			for (int i = 0; i < 12; i++) //Subtrai 48 de acordo com tabela ASCII 
				soma += mult13[i] * (numeros.charAt(i) - 48); //multiplica 1os 12 digitos por multiplicador [5,4,3,2,9,8,7,6,5,4,3,2] 
			int resto = (soma % 11); //resto da soma por 11
			if (resto <= 2) {
				if (numeros.charAt(12) - 48 != 0) //se resto eh menor ou igual do que 2, 13o digito deve ser 0
					return false;
			}
			else if (numeros.charAt(12) - 48 != 11 - resto) //se resto eh maior do que 2, 13o digito deve ser 11 menos resto
				return false;
			
			//Verificando 14o Digito
			soma = 0;
			int[] mult14 = new int[] {6,5,4,3,2,9,8,7,6,5,4,3,2};
			for (int i = 0; i < 13; i++) //Mesmo processo, porem agora indo ate o 13o digito e multiplicador [6,5,4,3,2,9,8,7,6,5,4,3,2]
				soma += mult14[i] * (numeros.charAt(i) - 48);
			resto = (soma % 11);
			if (resto <= 2) {
				if (numeros.charAt(13) - 48 != 0)
					return false;
			}
			else if (numeros.charAt(13) - 48 != 11 - resto)
				return false;
			return true;
			}

}
