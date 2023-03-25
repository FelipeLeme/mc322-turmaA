package classesSeguradora;

public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	//Constructor
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}
	
	//Getters e Setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCPF() {
		return cpf;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
		System.out.println(soma + "/" + soma%11);
		resto = (soma % 11);
		if (resto <= 2) {
			if (numeros.charAt(10) - 48 != 0)
				return false;
		}
		else if (numeros.charAt(10) - 48 != 11 - resto)
			return false;
		return true;
	}
	
	public String toString() {
		return "####\nFicha de Cliente\n####\nNome: " + this.nome + "\nCPF: " + 
			    this.cpf + "\nData de Nascimento: " + this.dataNascimento + "\nIdade: " + 
					    this.idade + "\nEndereco: " + this.endereco + "\n####\n";
	}
}
