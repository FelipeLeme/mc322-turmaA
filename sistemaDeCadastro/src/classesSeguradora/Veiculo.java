package classesSeguradora;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	
	//Constructor
	public Veiculo(String placa, String marca, String modelo) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	//Getters e Setters
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String toString() {
		return "####\nFicha de Automovel\n####\nPlaca: " + this.placa + "\nMarca: " + 
	    this.marca + "\nModelo: " + this.modelo + "\n####\n";
	}
}
