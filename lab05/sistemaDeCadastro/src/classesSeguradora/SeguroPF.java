package classesSeguradora;
import java.time.LocalDate;

	public class SeguroPF extends Seguro{
	private Veiculo veiculo;
	private ClientePF cliente;
	
	public SeguroPF(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
		//Chama o Construtor da Superclasse
		super(id, dataInicio, dataFim, seguradora);
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	//Getters e Setters
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public ClientePF getCliente() {
		return cliente;
	}
	
	public void setCliente(ClientePF clientePF) {
		this.cliente = clientePF;
		calcularValor();
	}
	
	public double calcularValor() {
		CalcSeguro calcSeguro;
		if ((this.cliente.getIdade() >= 18) & this.cliente.getIdade() < 30)
			calcSeguro = CalcSeguro.FATOR_18_30;
		else if ((this.cliente.getIdade() >= 30) & this.cliente.getIdade() < 60)
			calcSeguro = CalcSeguro.FATOR_30_60;
		else if ((this.cliente.getIdade() >= 60) & this.cliente.getIdade() < 90)
			calcSeguro = CalcSeguro.FATOR_60_90;
		else
			calcSeguro = CalcSeguro.VALOR_BASE;
		switch(calcSeguro) {
		case FATOR_18_30:
		case FATOR_30_60:
		case FATOR_60_90:
			return 10.0 * calcSeguro.getValor() * (1+1/(this.cliente.getListaVeiculos().size() + 2)) * (2 + (this.nSinistrosporSeguro()/10) * (5 +(this.nSinistrosporCondutor()/10))); //fazer metodo em seguradora p identificar e enviar cliente correto
		default:
			return 0.0;
		}
	}
}
