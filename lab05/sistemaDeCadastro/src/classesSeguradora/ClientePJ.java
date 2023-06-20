package classesSeguradora;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
	final String cnpj;
	private LocalDate dataFundacao;
	private int qtdeFuncionarios;
	private List<Frota> listaFrota;
	
	//Constructor
	public ClientePJ (String cnpj, LocalDate dataFundacao, String nome, String endereco, String email, String telefone, int qtdeFuncionarios){
		//Chama o Construtor da Superclasse
		super(nome, endereco, email, telefone);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
		this.listaFrota = new ArrayList<Frota>();
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
	
	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}
	
	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	public List<Frota> getListaFrota(){
		return listaFrota;
	}
	
	public void removerFrota(Frota f) {
		this.listaFrota.remove(f);
	}
	
	public void adicionarFrota(Frota f) {
		this.listaFrota.add(f);
	}
	
	public void removerCarros(int f, int v) {
		this.listaFrota.get(f).getListaVeiculos().remove(this.listaFrota.get(f).getListaVeiculos().get(v));
	}
	
	public String listarFrotas(List<Frota> listaFrota, int i) {
		if (listaFrota.size() > i)
			return "\n*" + this.listaFrota.get(i).getCode() + listarCarros(this.listaFrota.get(i).getListaVeiculos(),0) + listarFrotas(listaFrota,i+1);
		else
			return "";
	}
	
	public String listarCarros(List<Veiculo> listaVeiculos, int i) {
		if (listaVeiculos.size() > i)
			return "\n*" + listaVeiculos.get(i).getPlaca() + listarCarros(listaVeiculos,i+1);
		else
			return "";
	}
	
	public int nVeiculosnaFrota() {
		int x = 0;
		for (int i = 0; i < listaFrota.size(); i++)
			x += listaFrota.get(i).nCarros();
		return x;
	}
	
	public int diferencaTempo(LocalDate data) {
		return (int)(java.time.temporal.ChronoUnit.YEARS.between(this.dataFundacao, data));
	}
	
	@Override
	public String toString() {
		return "####\nFicha de Pessoa Juridica\n####\nNome: " + super.getNome() + "\nEndereco: " + 
			    super.getEndereco() + "\nCodigo de Frotas: " + listarFrotas(listaFrota,0) + "\nCNPJ: " + 
			    this.cnpj + "\nData de Fundacao: " + this.dataFundacao + "\nNumero de Funcionarios: " + this.qtdeFuncionarios + "\n####\n";
	}
}