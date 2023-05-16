package classesSeguradora;
import java.time.LocalDate;

public class ClientePF extends Cliente {
	final String cpf;
	private String genero;
	private LocalDate dataLicenca;
	private String educacao;
	private LocalDate dataNascimento;
	private String classeEconomica;
	private int idade;
	
	//Constructor
	public ClientePF (String cpf, String genero, LocalDate dataLicenca, String educacao, LocalDate dataNascimento, 
			String classeEconomica, int idade, String nome, String endereco){
		//Chama o Construtor da Superclasse
		super(nome, endereco);
		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
		this.idade = idade;
	}

	// Getters e Setters
	public String getCPF() {
		return cpf;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public LocalDate getDataLicenca() {
		return dataLicenca;
	}
	
	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}
	
	public String getEducacao() {
		return educacao;
	}
	
	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getclasseEconomica() {
		return classeEconomica;
	}
	
	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "####\nFicha de Pessoa Fisica\n####\nNome: " + super.getNome() + "\nEndereco: " + 
			    super.getEndereco() + "\nLista de Veiculos: " + super.listarCarros(getListaVeiculos(),0) + "\nCPF: " + 
			    this.cpf + "\nGenero: " + this.genero + "\nData da Licenca: " + this.dataLicenca + "\nNivel de Educacao: " + 
			    this.educacao + "\nData de Nascimento: " + this.dataNascimento + "\nClasse Economica: " +
			    this.classeEconomica + "\nIdade: " + this.idade + "\n####\n";
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
	
	public double calculaScore() {
		CalcSeguro calcSeguro;
		if ((this.idade >= 18) & this.idade < 30)
			calcSeguro = CalcSeguro.FATOR_18_30;
		else if ((this.idade >= 30) & this.idade < 60)
			calcSeguro = CalcSeguro.FATOR_30_60;
		else if ((this.idade >= 60) & this.idade < 90)
			calcSeguro = CalcSeguro.FATOR_60_90;
		else
			calcSeguro = CalcSeguro.VALOR_BASE;
		switch(calcSeguro) {
		case FATOR_18_30:
		case FATOR_30_60:
		case FATOR_60_90:
			return 100.0 * calcSeguro.getValor() * this.getListaVeiculos().size();
		default:
			return 0.0;
		}
	}
}