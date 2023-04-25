package classesSeguradora;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		//User Inputs
		Scanner in = new Scanner(System.in);
		
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		List<Integer> todosIDS = new ArrayList<Integer>();
		
		System.out.println("Bem-Vindo . . .\n\nFavor Digitar Numero de Clientes: ");
		int qtdclientes = in.nextInt();
		in.nextLine(); //Consome \n
		for (int i = 0; i < qtdclientes; i++) {
			todosClientes.add(lerCliente(in));
		}
		
		//Criando Seguradora
		System.out.println("\nFavor registrar Seguradora . . .\nNome: ");
		String nome = in.nextLine();
		System.out.println("Telefone: ");
		String telefone = in.nextLine();
		System.out.println("Email: ");
		String email = in.nextLine();
		System.out.println("Endereco: ");
		String endereco = in.nextLine();
		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco); //instancia obj da classe Seguradora
		System.out.println("\nTentando Cadastrar Clientes Providenciados . . .");
		for (int i = 0; i < qtdclientes; i++) {
			if (seguradora.cadastrarCliente(todosClientes.get(i)) == false)
				System.out.println("ERRO! Algorismos Verificadores Invalidos, Operacao Cancelada:\n");
			else
				System.out.println(". . .\nCliente Registrado!");
		}
		
		//Criando Sinistro
		Random random = new Random();
		int qtdids = todosIDS.size();
		while (qtdids == todosIDS.size()) { //verifica se o ID eh unico
			Integer id = random.nextInt(9999);
			boolean unico = true;
			for (int i = 0; i < todosIDS.size(); i++)
				if (id == todosIDS.get(i))
					unico = false;
			if (unico = true) {
				todosIDS.add(id);
				seguradora.cadastrarSinistro(id);
			}
		}
		
		System.out.println(". . .\nSeguradora Registrada!\nVerificar Informacoes:\n" + seguradora.toString());
		System.out.println("\nRodando Testes Finais . . .");
		System.out.println(seguradora.listarClientes(seguradora.getListaClientes(), 0));
		System.out.println(seguradora.listarSinistros(seguradora.getListaSinistros(), 0));
		System.out.println(seguradora.visualizarSinistro(seguradora.getListaClientes().get(0).getNome()));
	} //fim do metodo main
	
	public static Cliente lerCliente(Scanner in) {
		//Criando Cliente
				System.out.println("\nFavor registrar Cliente . . .\nNome: ");
				String nome = in.nextLine();
				System.out.println("Endereco: ");
				String endereco = in.nextLine();
				System.out.println("\nFavor Registrar o Veiculo Primario . . . ");
				Veiculo veiculo = lerVeiculo(in);
				System.out.println("Digitar 0 se Trata-se de PF, ou 1 se Trata-se de PJ");
				int pfpj = in.nextInt();
				in.nextLine(); //Consome \n
				if (pfpj == 0) {
					System.out.println("\nCPF: ");
					String cpf = in.nextLine();
					System.out.println("\nGenero: ");
					String genero = in.nextLine();
					System.out.println("\nData de Licenca: ");
					LocalDate dataLicenca = entradaDatas(in.nextLine());
					System.out.println("\nNivel de Educacao: ");
					String educacao = in.nextLine();
					System.out.println("\nData de Nascimento: ");
					LocalDate dataNascimento = entradaDatas(in.nextLine());
					System.out.println("\nClasse Economica: ");
					String classeEconomica = in.nextLine();
					ClientePF clientepf = new ClientePF(cpf, genero, dataLicenca, educacao, dataNascimento,
							classeEconomica, nome, endereco, veiculo);//instancia obj da classe ClientePF
					System.out.println(". . .\nPessoa Fisica Registrada!\nVerificar Informacoes:\n" + clientepf.toString());
					return clientepf;
				}
				else {
					System.out.println("\nCNPJ: ");
					String cnpj = in.nextLine();
					System.out.println("\nData de Fundacao: ");
					LocalDate dataFundacao = entradaDatas(in.nextLine());
					ClientePJ clientepj = new ClientePJ(cnpj, dataFundacao, nome, endereco, veiculo);//instancia obj da classe ClientePJ
					System.out.println(". . .\nPessoa Juridica Registrada!\nVerificar Informacoes:\n" + clientepj.toString());
					return clientepj;
				}
	}

	public static Veiculo lerVeiculo(Scanner in) {
		//Criando Veiculo
				System.out.println("\tPlaca: ");
				String placa = in.nextLine();
				System.out.println("\tMarca: ");
				String marca = in.nextLine();
				System.out.println("\tModelo: ");
				String modelo = in.nextLine();
				System.out.println("\tAno de Fabricacao: ");
				int anoFabricacao = in.nextInt();
				in.nextLine(); //Consome \n
				Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao); //instancia obj da classe Veiculo
				System.out.println(". . .\nVeiculo Registrado!\nVerificar Informacoes:\n" + veiculo.toString());
				return veiculo;
	}	
	
	public static LocalDate entradaDatas(String entrada) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-yyyy");
		LocalDate data = LocalDate.parse(entrada, formato);
		return data;
	}
} //fim da classe Main
