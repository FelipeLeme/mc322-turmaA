package classesSeguradora;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	
	//Constructor
	public Sinistro(int id, String data, String endereco) {
		this.id = id;
		this.data = data;
		this.endereco = endereco;
	}
	
	//Getters e Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}	
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		return "####\nFicha de Sinistro\n####\nID: " + this.id + "\nData: " + 
			    this.data + "\nEndereco: " + this.endereco + "\n####\n";
	}
}
