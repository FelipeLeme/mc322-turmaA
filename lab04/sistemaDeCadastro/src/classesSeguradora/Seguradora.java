package classesSeguradora;
import java.util.List;
import java.util.ArrayList;

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
	public String toString(int i) {
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
	
	public String listarSinistros(String nome, int i) {
		if (listaSinistros.size() > i)
			if (listaSinistros.get(i).getCliente().getNome() == nome)
				return "\n" + this.listaSinistros.get(i).getId() + "\t" + listarSinistros(listaSinistros,i+1);
			else
				return listarSinistros(listaSinistros,i+1);
		return "";
	}
	
	public String listarClientes(List<Cliente> listaClientes, int i) {
		if (listaClientes.size() > i)
			return "\n" + this.listaClientes.get(i).toString() + "\t" + listarClientes(listaClientes,i+1);
		else
			return "";
	}
	
	public String listarVeiculosExt(List<Cliente> listaClientes, int i) {
		if (listaClientes.size() > i)
			return listarVeiculosInt(listaClientes.get(i).getListaVeiculos(),0) + listarVeiculosExt(listaClientes, i+1);
		else
			return "";
	}
	
	public String listarVeiculosInt(List<Veiculo> listaVeiculos, int i) {
		if (listaVeiculos.size() > i)
			return "\n" + listaVeiculos.get(i).toString() + "\t" + listarVeiculosInt(listaVeiculos,i+1);
		else
			return"";
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
	
	public boolean cadastrarSinistro(Sinistro si) {
		this.listaSinistros.add(si);
		return true;
	}
	
	public boolean removerSinistro(int id) {
		boolean success = false;
		for (int i = 0; i < listaSinistros.size(); i++) {
			if (id == listaSinistros.get(i).getId()) {
				listaSinistros.remove(i);
				return !success;
			}
		}
		return success;
	}
	
	public boolean visualizarSinistro(String nome) {
		boolean success = false;
		for (int i = 0; i < listaSinistros.size(); i++)
			if (nome == listaSinistros.get(i).getCliente().getNome())
				return !success;
		return success;
	}
	
	public double calcularPrecoSeguroCliente(String nome) {
		int aparececliente = -1;
		int aparecesinistro = 0;
		for (int i = 0; i < listaClientes.size(); i++) //Verifica se o cliente dado aparece na seguradora e da indice da lista
			if (nome == listaClientes.get(i).getNome())
				aparececliente = i;
		for (int i = 0; i < listaSinistros.size(); i++) //Verifica para cada sinistro se o cliente em questao estava envolvido
			if (nome == listaSinistros.get(i).getCliente().getNome())
				aparecesinistro += 1;
		if (aparececliente != -1)
			return this.listaClientes.get(aparececliente).getvalorSeguro() * (1 + aparecesinistro); //Como ja estamos atualizando sempre que mexemos nos carros de cada cliente, nao precisamos chamar o metodo calculaScore
		else
			return 0.0;
	}
	
	public void transferirSeguro(int transferir, int receber) {
		int tam = listaClientes.get(transferir).getListaVeiculos().size();
		for (int i = tam; i > 0; i--) {
			listaClientes.get(receber).adicionarCarros(listaClientes.get(transferir).getListaVeiculos().get(i-1));
			listaClientes.get(transferir).removerCarros(listaClientes.get(transferir).getListaVeiculos().get(i-1));
		}
		
	}
}
