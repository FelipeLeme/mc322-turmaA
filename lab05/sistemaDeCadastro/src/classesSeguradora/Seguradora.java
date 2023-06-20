package classesSeguradora;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
	
	private final String cnpj;
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Seguro> listaSeguros;
	private List<Cliente> listaClientes;
	
	//Constructor
	public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSeguros = new ArrayList<Seguro>();
		this.listaClientes = new ArrayList<Cliente>();
	}
	
	//Getters e Setters
	public String getCNPJ() {
		return cnpj;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	//String mais curta para nao poluir saida
	public String toString(int i) {
		return "\n\tCNPJ: " + this.cnpj;
	}
	
	public String toString() {
		return "####\nServicos Seguradora " + this.nome + "\n####\nTelefone: " + 
	    this.telefone + "\nEmail: " + this.email + "\nEndereco: " + this.endereco + 
	    "\n\n*Seguros: " + this.listarSeguros(this.listaSeguros,0) + 
	    "\n*Clientes: " + this.listarClientes(this.listaClientes,0);
	}
	
	public void gerarSeguro(List<Cliente> cl) {
		Seguro seo;
		System.out.println("\tData de Inicio (dd-MM-yyyy): ");
		LocalDate dataInicio = Main.entradaDatas();
		System.out.println("\tData de Fim (dd-MM-yyyy): ");
		LocalDate dataFim = Main.entradaDatas();
		Random random = new Random();
		Integer id;
		do {
		id = random.nextInt(9999);
		} while (procurarID(id, listaSeguros) == true);
		System.out.println("Digitar 0 se Trata-se de SeguroPF, ou 1 se Trata-se de SeguroPJ");
		int pfpj = Integer.parseInt(Main.in.nextLine());
		int skip = 0;
		if (pfpj == 0) {
			List<ClientePF> clientespf = new ArrayList<ClientePF>();
			System.out.println("Escolher ClientePF:");
			for (int i = 0; i < cl.size(); i++) {
				if (cl.get(i) instanceof ClientePF) {
					System.out.println(i - skip + ". " + cl.get(i).getNome());
					clientespf.add((ClientePF) cl.get(i));
				}
				else
					skip++; //pula clientes pj
			}
			ClientePF clientepf = (ClientePF) clientespf.get(Integer.parseInt(Main.in.nextLine()));
			System.out.println("Escolher um de seus veiculos:");
			for (int i = 0; i < clientepf.getListaVeiculos().size(); i++)
				System.out.println(i + ". " + clientepf.getListaVeiculos().get(i).getPlaca());
			Veiculo veiculo = clientepf.getListaVeiculos().get(Integer.parseInt(Main.in.nextLine()));
			seo = new SeguroPF(id, dataInicio, dataFim, this, veiculo, clientepf);
			seo.setValorMensal(seo.calcularValor());
		}
		else {
			List<ClientePJ> clientespj = new ArrayList<ClientePJ>();
			System.out.println("Escolher ClientePJ:");
			for (int i = 0; i < cl.size(); i++) {
				if (cl.get(i) instanceof ClientePJ) {
					System.out.println(i - skip + ". " + cl.get(i).getNome());
					clientespj.add((ClientePJ) cl.get(i));
				}
				else
					skip++; //pula clientes pf
			}
			ClientePJ clientepj = (ClientePJ) clientespj.get(Integer.parseInt(Main.in.nextLine()));
			System.out.println("Escolher um de suas frotas:");
			for (int i = 0; i < clientepj.getListaFrota().size(); i++)
				System.out.println(i + ". " + clientepj.getListaFrota().get(i).getCode());
			Frota frota = clientepj.getListaFrota().get(Integer.parseInt(Main.in.nextLine()));
			seo = new SeguroPJ(id, dataInicio, dataFim, this, frota, clientepj);
			seo.setValorMensal(seo.calcularValor());
		}
		this.cadastrarSeguro(seo);
		System.out.println("Seguro registrado, ID:" + seo.getID());
	}

//Metodo procurarID, garante que o id do seguro sera unico
public static boolean procurarID(int id, List<Seguro>so) {
	if (so.isEmpty())
		return false;
	else { 
		for (int i = 0; i < Seguro.todosids.size(); i++)
			if (Seguro.todosids.get(i) == id)
				return true;
		return false;
	}
}
	
	public String listarSeguros(List<Seguro> listaSeguros, int i) {
		if (listaSeguros.size() > i)
			return "\n" + this.listaSeguros.get(i).toString(0) + "\t" + listarSeguros(listaSeguros,i+1);
		else
			return "";
	}
	
	public String listarSeguros(String nome, int i) {
		if (listaSeguros.size() > i)
			if (listaSeguros.get(i).getCliente().getNome() == nome)
				return "\n" + this.listaSeguros.get(i).getID() + "\t" + listarSeguros(listaSeguros,i+1);
			else
				return listarSeguros(listaSeguros,i+1);
		return "";
	}
	
	public String listarSinistros(List<Seguro> listaSeguros, int i) {
		if (listaSeguros.size() > i)
			return "\n" + listarSinistrosInt(listaSeguros.get(i).getListaSinistros(),0) + "\t" + listarSinistros(listaSeguros,i+1);
		else
			return"";
	}
	
	public String listarSinistrosInt(List<Sinistro> listaSinistros, int i) {
		if (listaSinistros.size() > i)
			return "\n" + listaSinistros.get(i).toString() + "\t" + listarSinistrosInt(listaSinistros,i+1);
		else
			return"";
	}
	
	
	public String listarClientes(List<Cliente> listaClientes, int i) {
		if (listaClientes.size() > i)
			return "\n" + this.listaClientes.get(i).toString() + "\t" + listarClientes(listaClientes,i+1);
		else
			return "";
	}
	
	public String listarVeiculosExt(List<Cliente> listaClientes, int i) {
		if (listaClientes.size() > i)
			if (listaClientes.get(i) instanceof ClientePF)
				return listarVeiculosInt(((ClientePF) listaClientes.get(i)).getListaVeiculos(),0) + listarVeiculosExt(listaClientes, i+1);
			else
				return listarVeiculosFrota((((ClientePJ) listaClientes.get(i)).getListaFrota()),0) + listarVeiculosExt(listaClientes, i+1);
		else
			return "";
	}
	
	public String listarVeiculosFrota(List<Frota> listaFrota, int i) {
		if (listaFrota.size() > i)
			return "\n" + listarVeiculosInt(listaFrota.get(i).getListaVeiculos(),0) + "\t" + listarVeiculosFrota(listaFrota,i+1);
		else
			return"";
	}
	
	public String listarVeiculosInt(List<Veiculo> listaVeiculos, int i) {
		if (listaVeiculos.size() > i)
			return "\n" + listaVeiculos.get(i).toString() + "\t" + listarVeiculosInt(listaVeiculos,i+1);
		else
			return"";
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		Validacao validacao;
		if (cliente instanceof ClientePF) {
			ClientePF clientepf = (ClientePF) cliente;
			validacao = new Validacao(clientepf.getCPF(),"",0);
			if (validacao.verificarValidade()) {
				this.listaClientes.add(clientepf);
				return true;
			}
			else
				return false;
		}
		else {
			ClientePJ clientepj = (ClientePJ) cliente;
			validacao = new Validacao("",clientepj.getCNPJ(),1);
			if (validacao.verificarValidade()) {
				this.listaClientes.add(clientepj);
				return true;
			}
			else
				return false;
		}
	}
	
	public boolean removerCliente(String nome) {
		boolean success = false;
		for (int i = 0; i < listaClientes.size(); i++) {
			if (nome == listaClientes.get(i).getNome()) {
				listaClientes.remove(i);
				for (int j = 0; j < listaSeguros.size(); j++) {
					if (nome == listaSeguros.get(i).getCliente().getNome()) {
						listaSeguros.remove(i);
						break;
					}
				}
				return !success;
			}
		}
		return success;
	}
	
	public boolean cadastrarSeguro(Seguro seo) {
		this.listaSeguros.add(seo);
		return true;
	}
	
	public boolean removerSeguro(int id) {
		boolean success = false;
		for (int i = 0; i < listaSeguros.size(); i++) {
			if (id == listaSeguros.get(i).getID()) {
				listaSeguros.remove(i);
				return !success;
			}
		}
		return success;
	}
	
	public boolean visualizarSeguro(String nome) {
		boolean success = false;
		for (int i = 0; i < listaSeguros.size(); i++)
			if (nome == listaSeguros.get(i).getCliente().getNome())
				return !success;
		return success;
	}
	
	public void calcularReceita() {
		int x = 0;
		for (int i = 0; i < listaSeguros.size(); i++)
			x += listaSeguros.get(i).getValorMensal();
		System.out.println("Receita total da Seguradora: " + x);
	}
	
	public static Seguradora criarSeguradora() {
		//Criando Seguradora
		System.out.println("\nFavor registrar Seguradora . . .");
		Validacao validacao = new Validacao("", "", 1);
		do {
		System.out.println("CNPJ Valido: ");
		String cnpj = Main.in.nextLine();
		validacao.setCNPJ(cnpj);
		} while (validacao.verificarValidade() == false);
		System.out.println("Nome: ");
		String nome = Main.in.nextLine();
		System.out.println("Telefone: ");
		String telefone = Main.in.nextLine();
		System.out.println("Email: ");
		String email = Main.in.nextLine();
		System.out.println("Endereco: ");
		String endereco = Main.in.nextLine();
		Seguradora seguradora = new Seguradora(validacao.getCNPJ(), nome, telefone, email, endereco); //instancia obj da classe Seguradora
		System.out.println(". . .\nSeguradora Registrada! Verificar Informacoes:\n" + seguradora.toString(0));
		return seguradora;
	}	
}
