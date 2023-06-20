package classesSeguradora;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ClientePF extends Cliente {
	final String cpf;
	private String genero;
	private LocalDate dataLicenca;
	private String educacao;
	private LocalDate dataNascimento;
	private String classeEconomica;
	private int idade;
	private List<Veiculo> listaVeiculos;
	
	//Constructor
	public ClientePF (String cpf, String genero, LocalDate dataLicenca, String educacao, LocalDate dataNascimento, 
			String classeEconomica, int idade, String nome, String endereco, String email, String telefone){
		//Chama o Construtor da Superclasse
		super(nome, endereco, email, telefone);
		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
		this.idade = idade;
		this.listaVeiculos = new ArrayList<Veiculo>();
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
	
	public List<Veiculo> getListaVeiculos(){
		return listaVeiculos;
	}

	public void removerCarros(int v) {
		this.listaVeiculos.remove(listaVeiculos.get(v));
	}
	
	public void adicionarCarros(Veiculo v) {
		this.listaVeiculos.add(v);
	}
	
	public String listarCarros(List<Veiculo> listaVeiculos,int i) {
		if (listaVeiculos.size() > i)
			return "\n*" + this.listaVeiculos.get(i).getPlaca() + listarCarros(listaVeiculos,i+1);
		else
			return "";
	}
	
	@Override
	public String toString() {
		return "####\nFicha de Pessoa Fisica\n####\nNome: " + super.getNome() + "\nEndereco: " + 
			    super.getEndereco() + "\nEmail: " + super.getEmail() + "\nTelefone: " + 
				super.getTelefone() + "\nLista de Veiculos: " + listarCarros(listaVeiculos,0) + "\nCPF: " + 
			    this.cpf + "\nGenero: " +this.genero + "\nData da Licenca: " + this.dataLicenca + "\nNivel de Educacao: " + 
			    this.educacao + "\nData de Nascimento: " + this.dataNascimento + "\nClasse Economica: " +
			    this.classeEconomica + "\nIdade: " + this.idade + "\n####\n";
	}
}