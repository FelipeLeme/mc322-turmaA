package classesSeguradora;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;
	private double valorSeguro;
	
	//Constructor
	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = new ArrayList<Veiculo>();
		this.valorSeguro = 0;
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
	
	public double getvalorSeguro() {
		return valorSeguro;
	}
	
	public void setvalorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}
	
	//String mais curta para nao poluir saida
	public String toString(int i) {
		return "\n\tNome: " + this.nome;
	}
	
	public String toString() {
		return "####\nFicha de Cliente\n####\nNome: " + this.nome + "\nEndereco: " + 
			    this.endereco + "\nLista de Veiculos: " + this.listarCarros(this.listaVeiculos,0) + "\n####\n";
	}
	
	public void adicionarCarros(Veiculo v) {
		this.listaVeiculos.add(v);
		setvalorSeguro(calculaScore());
	}
	
	public void removerCarros(Veiculo v) {
		this.listaVeiculos.remove(v);
		setvalorSeguro(calculaScore());
	}
	
	public String listarCarros(List<Veiculo> listaVeiculos,int i) {
		if (listaVeiculos.size() > i)
			return "\n*" + this.listaVeiculos.get(i).getPlaca() + listarCarros(listaVeiculos,i+1);
		else
			return "";
	}
	
	public double calculaScore() {
		return 0.0;
	}
}
