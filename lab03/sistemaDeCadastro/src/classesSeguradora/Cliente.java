package classesSeguradora;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;
	
	//Constructor
	public Cliente(String nome, String endereco, Veiculo veiculo) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = new ArrayList<Veiculo>();
		this.listaVeiculos.add(veiculo);
	}
	
	//Getters e Setters
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
	
	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	//String mais curta para nao poluir saida
	public String shortString() {
		return "\n\tNome: " + this.nome;
	}
	
	public String toString() {
		return "####\nFicha de Cliente\n####\nNome: " + this.nome + "\nEndereco: " + 
			    this.endereco + "\nLista de Veiculos: " + this.listarCarros(this.listaVeiculos,0) + "\n####\n";
	}
	
	public String listarCarros(List<Veiculo> listaVeiculos,int i) {
		if (listaVeiculos.size() > i)
			return "\n*" + this.listaVeiculos.get(i).getPlaca() + listarCarros(listaVeiculos,i+1);
		else
			return "";
	}
}
