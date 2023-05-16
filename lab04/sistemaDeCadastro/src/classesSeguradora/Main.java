package classesSeguradora;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
	static Scanner in = new Scanner(System.in);
	
	//exibir menu externo
	private static void exibirMenuExterno() {
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for(MenuOpcoes op: menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}
	
	/* exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, a opção Voltar
	 * é printada com a posição que está na lista do enum (9 - Voltar). Por isso, a lista é percorrida 
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOpcoes op) {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
	}
	
	//ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno() {
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Integer.parseInt(in.nextLine());
		}while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	//ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Integer.parseInt(in.nextLine());
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	//executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOpcoes op, List<Cliente> cl, List<Veiculo> ve, List<Seguradora> se, List<Sinistro> si) {
		int entr1, entr2, entr3;
		switch(op) {
		
			case CRIACAO:
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, cl, ve, se, si);
				break;
				
			case GERAR_SINISTRO:
				//Gera um sinistro envolvendo uma seguradora, um cliente e um veiculo, e informacoes adicionais
				System.out.println("Escolher Seguradora:");
				for (int i = 0; i < se.size(); i++)
					System.out.println(i + ". " + se.get(i).getNome()); //Se X
				entr1 = Integer.parseInt(in.nextLine());
				System.out.println("Escolher um de seus Clientes:");
				for (int i = 0; i < se.get(entr1).getListaClientes().size(); i++)
					System.out.println(i + ". " + se.get(entr1).getListaClientes().get(i).getNome()); //Cl Y de Se X
				entr2 = Integer.parseInt(in.nextLine());
				System.out.println("Escolher um de seus Veiculos:");
				for (int i = 0; i < se.get(entr1).getListaClientes().get(entr2).getListaVeiculos().size(); i++)
					System.out.println(i + ". " + se.get(entr1).getListaClientes().get(entr2).getListaVeiculos().get(i).getPlaca()); //Ve Z de Cl Y de Se X
				entr3 = Integer.parseInt(in.nextLine());
				si.add(criarSinistro(se.get(entr1), entr2, entr3));
				break;
				
			case TRANSFERIR_SEGURO:
				//Em uma seguradora, remove todos os carros do cliente de indice transferir, e os adiciona ao cliente de indice receber
				System.out.println("Escolher Seguradora:");
				for (int i = 0; i < se.size(); i++)
					System.out.println(i + ". " + se.get(i).getNome());
				entr1 = Integer.parseInt(in.nextLine());
				System.out.println("Escolher um de seus Clientes para transferir o Seguro:");
				for (int i = 0; i < se.get(entr1).getListaClientes().size(); i++)
					System.out.println(i + ". " + se.get(entr1).getListaClientes().get(i).getNome());
				entr2 = Integer.parseInt(in.nextLine());
				System.out.println("Escolher outro de seus Clientes para receber o Seguro:");
				for (int i = 0; i < se.get(entr1).getListaClientes().size(); i++)
					if (i != entr2)
						System.out.println(i + ". " + se.get(entr1).getListaClientes().get(i).getNome());
				entr3 = Integer.parseInt(in.nextLine());
				se.get(entr1).transferirSeguro(entr2, entr3);
				break;
				
			case CALCULAR_RECEITA:
				//Como estamos atualizando valorSeguro de cada Cliente sempre que mexemos em sua lista de carros, podemos simplesmente somar todos os valores, sem chamar as funcoes de cliente novamente
				double receita = 0.0;
				System.out.println("Escolher Seguradora:");
				for (int i = 0; i < se.size(); i++)
					System.out.println(i + ". " + se.get(i).getNome());
				entr1 = Integer.parseInt(in.nextLine());
				for (int i = 0; i < se.get(entr1).getListaClientes().size(); i++)
					receita += se.get(entr1).calcularPrecoSeguroCliente(se.get(entr1).getListaClientes().get(i).getNome());//So precisamos chamar a funcao calcularPrecoSeguroCliente para contar os sinistros em que esteve envolvido
				System.out.println("\n Receita: " + receita + " RS");
				break;
				
			case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, List<Cliente> cl, List<Veiculo> ve, List<Seguradora> se, List<Sinistro>si) {
		int entr1, entr2;
		switch(opSubmenu) {
		
		case CRIAR_CLIENTE:
			//Le e instancia clientes
			cl.add(criarCliente());
			break;
			
		case CRIAR_VEICULO:
			//Le e instancia veiculos
			ve.add(criarVeiculo());
			break;
			
		case CRIAR_SEGURADORA:
			//Le e instancia seguradoras
			se.add(criarSeguradora());
			break;
			
			
		case CADASTRAR_CLIENTE:
			//Permite escolher 1 Cliente e 1 Seguradora e relaciona-los, se cpf/cnpj forem validos
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher Cliente:");
			for (int i = 0; i < cl.size(); i++)
				System.out.println(i + ". " + cl.get(i).getNome());
			entr2 = Integer.parseInt(in.nextLine());
			if (se.get(entr1).cadastrarCliente(cl.get(entr2)) == false)
				System.out.println("ERRO! Algorismos Verificadores Invalidos, Operacao Cancelada:\n");
			else
				System.out.println(". . .\nCliente Registrado!");
			break;
			
		case CADASTRAR_VEICULO:
			//Permite escolher 1 Veiculo e 1 Cliente e relaciona-los (unidirecionalmente)
			System.out.println("Escolher Cliente:");
			for (int i = 0; i < cl.size(); i++)
				System.out.println(i + ". " + cl.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher Veiculo:");
			for (int i = 0; i < ve.size(); i++)
				System.out.println(i + ". " + ve.get(i).getPlaca());
			entr2 = Integer.parseInt(in.nextLine());
			cl.get(entr1).adicionarCarros(ve.get(entr2));
			break;
			
		case CADASTRAR_SEGURADORA:
			System.out.println("obs: Da maneira como eu implementei, nao ha nada a se fazer aqui (?). Pois as seguradoras ja podem ser criadas em Criacao");
			break;
			
			
		case LISTAR_CLIENTES:
			//Da o nome de todos os clientes de uma seguradora
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarClientes(se.get(entr1).getListaClientes(),0)); //Parametros pra impressao recursiva
			break;
			
		case LISTAR_SINISTROS_POR_SEGURADORA:
			//Da todos o id de todos os sinistros associados a uma seguradora especifica
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarSinistros(se.get(entr1).getListaSinistros(),0));
			break;
			
		case LISTAR_SINISTROS_POR_CLIENTE:
			//Da todos os ids de todos os sinistros associados a um cliente de uma seguradora
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher um de seus Clientes:");
			for (int i = 0; i < cl.size(); i++)
				System.out.println(i + ". " + se.get(entr1).getListaClientes().get(i).getNome());
			entr2 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarSinistros(se.get(entr1).getListaClientes().get(entr2).getNome(),0));
			break;
			
		case LISTAR_VEICULOS_POR_SEGURADORA:
			//Da a placa de todos os veiculos de todos os clientes de uma seguradora
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarVeiculosExt(se.get(entr1).getListaClientes(), 0));
			break;
			
		case LISTAR_VEICULOS_POR_CLIENTE:
			//Da a placa de todos os veiculos de um cliente
			System.out.println("Escolher Cliente:");
			for (int i = 0; i < cl.size(); i++)
				System.out.println(i + ". " + cl.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println(cl.get(entr1).listarCarros(cl.get(entr1).getListaVeiculos(),0));
			break;
			
			
		case EXCLUIR_CLIENTE:
			//Remove um Cliente de uma Seguradora
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher Cliente:");
			for (int i = 0; i < cl.size(); i++)
				System.out.println(i + ". " + cl.get(i).getNome());
			entr2 = Integer.parseInt(in.nextLine());
			if (se.get(entr1).removerCliente(cl.get(entr2).getNome()) == true)
				System.out.println("Cliente removido");
			else
				System.out.println("Erro, cliente nao encontrado");
			break;
			
		case EXCLUIR_VEICULO:
			//Remove um Veiculo de um Cliente
			System.out.println("Escolher Cliente:");
			for (int i = 0; i < cl.size(); i++)
				System.out.println(i + ". " + cl.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher um de seus Veiculos:");
			for (int i = 0; i < cl.get(entr1).getListaVeiculos().size(); i++)
				System.out.println(i + ". " + cl.get(entr1).getListaVeiculos().get(i).getPlaca());
			entr2 = Integer.parseInt(in.nextLine());
			cl.get(entr1).removerCarros(cl.get(entr1).getListaVeiculos().get(entr2));
			break;
			
		case EXCLUIR_SINISTRO:
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher um de seus sinistros:");
			for (int i = 0; i < se.get(entr1).getListaSinistros().size(); i++)
				System.out.println(i + ". " + se.get(entr1).getListaSinistros().get(i).getId());
			entr2 = Integer.parseInt(in.nextLine());
			se.get(entr1).removerSinistro(se.get(entr1).getListaSinistros().get(entr2).getId());
			break;
			
			
		case VOLTAR:
			break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op, List<Cliente> cl, List<Veiculo> ve, List<Seguradora> se, List<Sinistro> si) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, cl, ve, se, si);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) {
		MenuOpcoes op;
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
		List<Seguradora> todasSeguradoras = new ArrayList<Seguradora>();
		List<Sinistro> todosSinistros = new ArrayList<Sinistro>();
		
		System.out.println("Bem-Vindo . . .");
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op, todosClientes, todosVeiculos, todasSeguradoras, todosSinistros);
		}while(op != MenuOpcoes.SAIR);
		for (int i = 0; i < todosClientes.size(); i++)
		System.out.println("Saiu do sistema");
	}
	
	public static Cliente criarCliente() {
		//Criando Cliente
				System.out.println("\nFavor registrar Cliente . . .\nNome: ");
				String nome = in.nextLine().replaceAll("[^A-Za-z]+", "");
				System.out.println("Endereco: ");
				String endereco = in.nextLine();
				System.out.println("Digitar 0 se Trata-se de PF, ou 1 se Trata-se de PJ");
				int pfpj = in.nextInt();
				in.nextLine(); //Consome \n
				if (pfpj == 0) {
					System.out.println("\nCPF: ");
					String cpf = in.nextLine();
					System.out.println("\nGenero: ");
					String genero = in.nextLine();
					System.out.println("\nData de Licenca (dd-MM-yyyy): ");
					LocalDate dataLicenca = entradaDatas(in.nextLine());
					System.out.println("\nNivel de Educacao: ");
					String educacao = in.nextLine();
					System.out.println("\nData de Nascimento (dd-MM-yyyy): ");
					LocalDate dataNascimento = entradaDatas(in.nextLine());
					System.out.println("\nClasse Economica: ");
					String classeEconomica = in.nextLine();
					System.out.println("\nIdade: ");
					int idade = Integer.parseInt(in.nextLine());
					ClientePF clientepf = new ClientePF(cpf, genero, dataLicenca, educacao, dataNascimento,
							classeEconomica, idade, nome, endereco);//instancia obj da classe ClientePF
					System.out.println(". . .\nPessoa Fisica Registrada!\nVerificar Informacoes:\n" + clientepf.toString(0));
					return clientepf;
				}
				else {
					System.out.println("\nCNPJ: ");
					String cnpj = in.nextLine();
					System.out.println("\nData de Fundacao (dd-MM-yyyy): ");
					LocalDate dataFundacao = entradaDatas(in.nextLine());
					System.out.println("\nNumero de Funcionarios: ");
					int qtdeFuncionarios = Integer.parseInt(in.nextLine());
					ClientePJ clientepj = new ClientePJ(cnpj, dataFundacao, nome, endereco, qtdeFuncionarios);//instancia obj da classe ClientePJ
					System.out.println(". . .\nPessoa Juridica Registrada!\nVerificar Informacoes:\n" + clientepj.toString(0));
					return clientepj;
				}
	}

	public static Veiculo criarVeiculo() {
		//Criando Veiculo
				System.out.println("\tPlaca: ");
				String placa = in.nextLine();
				System.out.println("\tMarca: ");
				String marca = in.nextLine();
				System.out.println("\tModelo: ");
				String modelo = in.nextLine();
				System.out.println("\tAno de Fabricacao: ");
				int anoFabricacao = Integer.parseInt(in.nextLine());
				Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao); //instancia obj da classe Veiculo
				System.out.println(". . .\nVeiculo Registrado! Verificar Informacoes:\n" + veiculo.toString(0));
				return veiculo;
	}
	
	public static Seguradora criarSeguradora() {
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
		System.out.println(". . .\nSeguradora Registrada! Verificar Informacoes:\n" + seguradora.toString(0));
		return seguradora;
	}	
	
	public static Sinistro criarSinistro(Seguradora se, int indicecl, int indiceve) {
		System.out.println("Favor registrar Sinistro . . .\nData: ");
		String data = in.nextLine();
		System.out.println("\nEndereco: ");
		String endereco = in.nextLine();
		Random random = new Random();
		Integer id = random.nextInt(9999);
		Sinistro sinistro = new Sinistro(id, data, endereco, se, se.getListaClientes().get(indicecl), se.getListaClientes().get(indicecl).getListaVeiculos().get(indiceve)); //instancia obj da classe Sinistro
		System.out.println(". . .\nSinistro Registrado!\nVerificar Informacoes:\n" + sinistro.toString());
		se.cadastrarSinistro(sinistro);
		return sinistro;
	}
	
	public static LocalDate entradaDatas(String entrada) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-yyyy");
		LocalDate data = LocalDate.parse(entrada, formato);
		return data;
	}
} //fim da classe Main
