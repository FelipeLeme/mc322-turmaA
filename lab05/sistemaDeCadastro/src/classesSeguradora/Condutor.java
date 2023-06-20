package classesSeguradora;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Condutor {	
	final String cpf;
	private String nome;
	private String endereco;
	private String email;
	private String telefone;
	private LocalDate dataNascimento;
	private List<Sinistro> listaSinistros;
	
	//Constructor
	public Condutor(String cpf, String nome, String endereco, String email, String telefone, LocalDate dataNascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		listaSinistros = new ArrayList<Sinistro>();
	}
	
	// Getters e Setters
	public String getCPF() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	
	//Tamanho da lista de Sinistros eh o numero de sinistros associado ao condutor
	public int nSinistros() {
		return listaSinistros.size();
	}
	
	public void adicionarSinistro(Sinistro s) {
		listaSinistros.add(s);	
	}
	
	public String listarSinistros(List<Sinistro> listaSinistros,int i) {
		if (listaSinistros.size() > i)
			return "\n*" + this.listaSinistros.get(i).getId() + listarSinistros(listaSinistros,i+1);
		else
			return "";
	}
	
	//String mais curta para nao poluir saida
		public String toString(int i) {
			return "\n\tNome: " + this.nome + "\n\tCPF: " + this.cpf;
		}
	
	public String toString() {
		return "####\nFicha de Condutor\n####\nNome: " + this.nome + "\nEndereco: " + 
			    this.endereco + "\nEmail: " + this.email + "\nTelefone: " + this.telefone + "\nData de Nascimento: " + 
				this.dataNascimento + listarSinistros(this.listaSinistros, 0) + "\n####\n";
	}
	
	public void adicionarSinistros(Sinistro sinistro) {
		this.listaSinistros.add(sinistro);
	}
	
	public static Condutor criarCondutor() {
		Validacao validacao; //Usado para validar CPF ou CNPJ
		System.out.println("\nFavor registrar Condutor . . .");
		validacao = new Validacao("","",0);
		do {
		System.out.println("CPF: ");
		String cpf = Main.in.nextLine();
		validacao.setCPF(cpf);
		} while (validacao.verificarValidade() == false);
		String nome;
		do {
			System.out.println("Nome: ");
			nome = Main.in.nextLine().replaceAll("[^A-Za-z]+", "");
			if (nome == "")
				System.out.println("Erro, o nome deve conter ao menos uma letra");
		} while (nome == ""); //Para assegurar que o nome é composto por ao menos uma letra
		System.out.println("Endereco: ");
		String endereco = Main.in.nextLine();
		System.out.println("Email: ");
		String email = Main.in.nextLine();
		System.out.println("Telefone: ");
		String telefone = Main.in.nextLine();
		System.out.println("Data de Nascimento (dd-MM-yyyy): ");
		LocalDate dataNascimento = Main.entradaDatas();
		Condutor condutor = new Condutor(validacao.getCPF(), nome, endereco, email, telefone, dataNascimento);
		System.out.println(". . .\nCondutor Registrado! Verificar Informacoes:\n" + condutor.toString(0));
		return condutor;
	}
}
