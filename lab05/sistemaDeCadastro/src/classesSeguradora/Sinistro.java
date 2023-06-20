package classesSeguradora;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sinistro {
	static List<Integer> todosids = new ArrayList<Integer>();
	final int id;
	private LocalDate data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	//Constructor
	public Sinistro(int id, LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
		this.id = id;
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
		adicionarIDs(id);
	}
	
	//Getters e Setters
	public int getId() {
		return id;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}	
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Seguro getSeguro() {
		return seguro;
	}
	
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	public Condutor getCondutor() {
		return condutor;
	}
	
	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	
	public String toString() {
		return "####\nFicha de Sinistro\n####\nID: " + this.id + "\nData: " + 
			    this.data + "\nEndereco: " + this.endereco + "\nCondutor: " + this.condutor.toString(0) + "\nSeguro: " + this.seguro.toString(0) 
			    + "\n####\n";
	}
	
	public void adicionarIDs(int id) {
		Sinistro.todosids.add(id);
	}
}
