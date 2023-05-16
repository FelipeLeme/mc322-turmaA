package classesSeguradora;

public class Sinistro {
	final int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	//Constructor
	public Sinistro(int id, String data, String endereco, Seguradora seguradora, Cliente cliente, Veiculo veiculo) {
		this.id = id;
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	//Getters e Setters
	public int getId() {
		return id;
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
	
	public Seguradora getSeguradora() {
		return seguradora;
	}
	
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String toString() {
		return "####\nFicha de Sinistro\n####\nID: " + this.id + "\nData: " + 
			    this.data + "\nEndereco: " + this.endereco + "\nSeguradora: " + this.seguradora.toString(0) 
			    + "\nVeiculo: " + this.veiculo.toString(0) + "\nCliente: " + this.cliente.toString(0) 
			    + "\n####\n";
	}
}
