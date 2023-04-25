package classesSeguradora;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros;
	private List<Cliente> listaClientes;
	
	//Constructor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaClientes = new ArrayList<Cliente>();
	}
	
	//Getters e Setters
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
	
	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	//String mais curta para nao poluir saida
	public String shortString() {
		return "\n\tNome: " + this.nome;
	}
	
	public String toString() {
		return "####\nServicos Seguradora " + this.nome + "\n####\nTelefone: " + 
	    this.telefone + "\nEmail: " + this.email + "\nEndereco: " + this.endereco + 
	    "\n\n*Sinistros: " + this.listarSinistros(this.listaSinistros,0) + 
	    "\n*Clientes: " + this.listarClientes(this.listaClientes,0);
	}
	
	public String listarSinistros(List<Sinistro> listaSinistros, int i) {
		if (listaSinistros.size() > i)
			return "\n" + this.listaSinistros.get(i).toString() + "\t" + listarSinistros(listaSinistros,i+1);
		else
			return "";
	}
	
	public String listarClientes(List<Cliente> listaClientes, int i) {
		if (listaClientes.size() > i)
			return "\n" + this.listaClientes.get(i).toString() + "\t" + listarClientes(listaClientes,i+1);
		else
			return "";
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		if (cliente instanceof ClientePF) {
			ClientePF clientepf = (ClientePF) cliente;
			if (clientepf.validarCPF(clientepf.getCPF())) {
				this.listaClientes.add(clientepf);
				return true;
			}
			else
				return false;
		}
		else {
			ClientePJ clientepj = (ClientePJ) cliente;
			if (clientepj.validarCNPJ(clientepj.getCNPJ())) {
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
				for (int j = 0; j < listaSinistros.size(); j++) {
					if (nome == listaSinistros.get(i).getCliente().getNome()) {
						listaSinistros.remove(i);
						break;
					}
				}
				return !success;
			}
		}
		return success;
	}
	
	public boolean cadastrarSinistro(int id) {
		//User Inputs
		Scanner in = new Scanner(System.in);
		
		System.out.println("Favor registrar Sinistro . . .\nData: ");
		String data = in.nextLine();
		System.out.println("\nEndereco: ");
		endereco = in.nextLine();
		System.out.println("\nUsar Cliente 1 ou 2?: ");
		int qualcliente = in.nextInt() - 1;
		in.nextLine(); //Consome \n
		Sinistro sinistro = new Sinistro(id, data, endereco, this,
				this.listaClientes.get(qualcliente).getListaVeiculos().get(0), 
				this.listaClientes.get(qualcliente)); //instancia obj da classe Sinistro
		System.out.println(". . .\nSinistro Registrado!\nVerificar Informacoes:\n" + sinistro.toString());
		this.listaSinistros.add(sinistro);
		in.close();
		return true;
	}
	
	public boolean visualizarSinistro(String nome) {
		boolean success = false;
		for (int i = 0; i < listaSinistros.size(); i++)
			if (nome == listaSinistros.get(i).getCliente().getNome())
				return !success;
		return success;
	}
}
