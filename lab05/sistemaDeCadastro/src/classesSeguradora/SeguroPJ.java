package classesSeguradora;
import java.time.LocalDate;

public class SeguroPJ extends Seguro{
	private Frota frota;
	private ClientePJ cliente;
	
	public SeguroPJ(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
		//Chama o Construtor da Superclasse
		super(id, dataInicio, dataFim, seguradora);
		this.frota = frota;
		this.cliente = cliente;
	}

	//Getters e Setters
	public Frota getFrota() {
		return frota;
	}
	
	public void setFrota(Frota frota) {
		this.frota = frota;
		calcularValor();
	}
	
	public ClientePJ getCliente() {
		return cliente;
	}
	
	public void setCliente(ClientePJ clientePJ) {
		this.cliente = clientePJ;
		calcularValor();
	}
	
	public double calcularValor() {
		return (10.0 * (10 + this.cliente.getQtdeFuncionarios()/10) * (1 + 1/(this.cliente.nVeiculosnaFrota() + 2)) * (1 + 1/(cliente.diferencaTempo(getDataInicio())+2)) * (2 + (this.nSinistrosporSeguro()/10) * (5 +(this.nSinistrosporCondutor()/10))));
		}
	}
