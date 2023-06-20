package classesSeguradora;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public abstract class Seguro {
	static List<Integer> todosids = new ArrayList<Integer>();
	final int id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Seguradora seguradora;
	private List<Sinistro> listaSinistros;
	private List<Condutor> listaCondutores;
	private double valorMensal;

	public Seguro(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		listaSinistros = new ArrayList<Sinistro>();
		listaCondutores = new ArrayList<Condutor>();
		adicionarIDs(id);
	}

	public int getID() {
		return id;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public LocalDate getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	public Seguradora getSeguradora() {
		return seguradora;
	}
	
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
	public List<Condutor> getListaCondutores(){
		return listaCondutores;
	}
	
	public List<Sinistro> getListaSinistros(){
		return listaSinistros;
	}
	
	public double getValorMensal() {
		return valorMensal;
	}
	
	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}
	
	//Tamanho da lista de Sinistros eh o numero de sinistros associado ao condutor
	public int nSinistrosporSeguro() {
		return listaSinistros.size();
	}
	
	//Soma o numero de sinistros de cada condutor participante do seguro
	public int nSinistrosporCondutor() {
		int x = 0;
		for (int i = 0; i < listaCondutores.size(); i++)
			x += listaCondutores.get(i).nSinistros();
		return x;
	}
	
	public abstract double calcularValor();
	public abstract Cliente getCliente();
	
	//String mais curta para nao poluir saida
	public String toString(int i) {
		return "\n\tID: " + this.id;
	}
	
	public static void adicionarIDs(int id) {
		Seguro.todosids.add(id);
	}
	
	public void gerarSinistro(List<Condutor> cr) {
		System.out.println("\tData (dd-MM-yyyy): ");
		LocalDate data = Main.entradaDatas();
		System.out.println("Endereco: ");
		String endereco = Main.in.nextLine();
		Random random = new Random();
		Integer id;
		do {
		id = random.nextInt(9999);
		} while (procurarID(id, listaSinistros) == true);
		System.out.println("Escolher Condutor:");
		for (int i = 0; i < cr.size(); i++)
			System.out.println(i + ". " + cr.get(i).getNome());
		Condutor condutor = cr.get(Integer.parseInt(Main.in.nextLine()));
		Sinistro sinistro = new Sinistro(id, data, endereco, condutor, this);
		condutor.adicionarSinistros(sinistro);
		this.adicionarSinistros(sinistro);
		System.out.println("Sinistro registrado, ID:" + sinistro.getId());
	}
	
	//Metodo procurarID, garante que o id do seguro sera unico
	public static boolean procurarID(int id, List<Sinistro>si) {
		if (si.isEmpty())
			return false;
		else { 
			for (int i = 0; i < Sinistro.todosids.size(); i++)
				if (Sinistro.todosids.get(i) == id)
					return true;
			return false;
		}
	}
	
	public void adicionarSinistros(Sinistro sinistro) {
		this.listaSinistros.add(sinistro);
		calcularValor();
	}
	
	public void autorizarCondutor(Condutor condutor) {
		this.listaCondutores.add(condutor);
		calcularValor();
	}
	
	public void desautorizarCondutor(Condutor condutor) {
		this.listaCondutores.remove(condutor);
		calcularValor();
	}
}



