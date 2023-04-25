package classesSeguradora;
import java.time.LocalDate;

public class ClientePJ extends Cliente {
	final String cnpj;
	private LocalDate dataFundacao;
	
	//Constructor
	public ClientePJ (String cnpj, LocalDate dataFundacao, String nome, String endereco, 
			Veiculo veiculo){
		//Chama o Construtor da Superclasse
		super(nome, endereco, veiculo);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
	}
	
	// Getters e Setters
	public String getCNPJ() {
		return cnpj;
	}
	
	public LocalDate getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	@Override
	public String toString() {
		return "####\nFicha de Pessoa Juridica\n####\nNome: " + super.getNome() + "\nEndereco: " + 
			    super.getEndereco() + "\nLista de Veiculos: " + super.listarCarros(super.getListaVeiculos(),0) + "\nCNPJ: " + 
			    this.cnpj + "\nData de Fundacao: " + this.dataFundacao + "\n####\n";
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
	
	public boolean repetidos(String numeros) {
		int l = numeros.length();
		for (int i = 1; i < l; i++)
			if (numeros.charAt(0) != numeros.charAt(i))
				return false;
		return true;
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