package classesSeguradora;

public class Veiculo {	
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	
	//Constructor
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
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
	
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	
	//String mais curta para nao poluir saida
	public String toString(int i) {
		return "\n\tPlaca: " + this.placa;
	}
		
	public String toString() {
		return "####\nFicha de Automovel\n####\nPlaca: " + this.placa + "\nMarca: " + 
	    this.marca + "\nModelo: " + this.modelo + "\nAno de Fabricacao: " + this.anoFabricacao + "\n####\n";
	}
	
	public static Veiculo criarVeiculo() {
		//Criando Veiculo
		System.out.println("\tPlaca: ");
		String placa = Main.in.nextLine();
		System.out.println("\tMarca: ");
		String marca = Main.in.nextLine();
		System.out.println("\tModelo: ");
		String modelo = Main.in.nextLine();
		System.out.println("\tAno de Fabricacao: ");
		int anoFabricacao = Integer.parseInt(Main.in.nextLine());
		Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao); //instancia obj da classe Veiculo
		System.out.println(". . .\nVeiculo Registrado! Verificar Informacoes:\n" + veiculo.toString(0));
		return veiculo;
	}
}
