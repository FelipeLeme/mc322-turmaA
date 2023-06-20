package classesSeguradora;
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
	private static void executarOpcaoMenuExterno(MenuOpcoes op, List<Cliente> cl, List<Veiculo> ve, List<Seguradora> se, List<Frota> fr, List<Condutor> cr) {
		int entr1, entr2;
		switch(op) {		
			case CRIACAO:
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, cl, ve, se, fr, cr);
				break;
				
			case GERAR_SEGURO:
				//Gera um seguro envolvendo uma seguradora, uma lista de sinistros e de condutores
				System.out.println("Escolher Seguradora:");
				for (int i = 0; i < se.size(); i++)
					System.out.println(i + ". " + se.get(i).getNome());
				entr1 = Integer.parseInt(in.nextLine());
				se.get(entr1).gerarSeguro(cl);
				break;
				
			case GERAR_SINISTRO:
				//Gera um sinistro envolvendo uma seguradora, um cliente e um veiculo, e informacoes adicionais
				System.out.println("Escolher Seguradora:");
				for (int i = 0; i < se.size(); i++)
					System.out.println(i + ". " + se.get(i).getNome());
				entr1 = Integer.parseInt(in.nextLine());
				System.out.println("Escolher um de seus Seguros:");
				for (int i = 0; i < se.get(entr1).getListaSeguros().size(); i++)
					System.out.println(i + ". " + se.get(entr1).getListaSeguros().get(i).getID());
				entr2 = Integer.parseInt(in.nextLine());
				se.get(entr1).getListaSeguros().get(entr2).gerarSinistro(cr);
				break;
				
			case CALCULAR_RECEITA:
				System.out.println("Escolher Seguradora:");
				for (int i = 0; i < se.size(); i++)
					System.out.println(i + ". " + se.get(i).getNome());
				entr1 = Integer.parseInt(in.nextLine());
				se.get(entr1).calcularReceita();
				break;
				
			case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, List<Cliente> cl, List<Veiculo> ve, List<Seguradora> se, List<Frota> fr, List<Condutor> cr) {
		int entr1, entr2, entr3;
		switch(opSubmenu) {
		case CRIAR_CLIENTE:
			//Le e instancia clientes
			Cliente cliente = criarCliente();
			cl.add(cliente);
			if (cliente instanceof ClientePF) {
				System.out.println("Digitar 0 se deseja VOLTAR, digitar 1 se deseja registrar também como condutor: ");
				entr1 = Integer.parseInt(in.nextLine());
				if (entr1 == 1) {
					Condutor condutor = new Condutor(((ClientePF) cliente).getCPF(), cliente.getNome(),
					cliente.getEndereco(), cliente.getEmail(), cliente.getTelefone(),
					((ClientePF) cliente).getDataNascimento());//instancia obj da classe Condutor com base em ClientePF
					cr.add(condutor);
					System.out.println(". . .\nCondutor Registrado!");
				}
			}
			break;
			
		case CRIAR_VEICULO:
			//Le e instancia veiculos
			ve.add(Veiculo.criarVeiculo());
			break;
			
		case CRIAR_SEGURADORA:
			//Le e instancia seguradoras
			se.add(Seguradora.criarSeguradora());
			break;
			
		case CRIAR_FROTA:
			//Le e instancia seguradoras
			fr.add(Frota.criarFrota(fr));
			break;
			
		case CRIAR_CONDUTOR:
			//Le e instancia condutores
			cr.add(Condutor.criarCondutor());
			
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
			
		case CADASTRAR_VEICULO_CLIENTEPF:
			//Permite escolher 1 Veiculo e 1 Cliente e relaciona-los (unidirecionalmente)
			List<ClientePF> clientespf = new ArrayList<ClientePF>();
			int skip = 0;
			System.out.println("Escolher ClientePF:");
			for (int i = 0; i < cl.size(); i++) {
				if (cl.get(i) instanceof ClientePF) {
					System.out.println(i - skip + ". " + cl.get(i).getNome());
					clientespf.add((ClientePF) cl.get(i));
				}
				else
					skip++; //pula clientes pj
			}
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher Veiculo:");
			for (int i = 0; i < ve.size(); i++)
				System.out.println(i + ". " + ve.get(i).getPlaca());
			entr2 = Integer.parseInt(in.nextLine());
			clientespf.get(entr1).adicionarCarros(ve.get(entr2));
			break;
			
		case CADASTRAR_VEICULO_FROTA:
			//Permite escolher 1 Veiculo e 1 Frota e relaciona-los
			System.out.println("Escolher Frota:");
			for (int i = 0; i < fr.size(); i++)
				System.out.println(i + ". " + fr.get(i).getCode());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher Veiculo:");
			for (int i = 0; i < ve.size(); i++)
				System.out.println(i + ". " + ve.get(i).getPlaca());
			entr2 = Integer.parseInt(in.nextLine());
			fr.get(entr1).adicionarCarros(ve.get(entr2));
			break;
			
		case CADASTRAR_FROTA_CLIENTEPJ:
			//Permite escolher 1 Frote e 1 Cliente e relaciona-los
			List<ClientePJ> clientespj = new ArrayList<ClientePJ>();
			skip = 0;
			System.out.println("Escolher ClientePJ:");
			for (int i = 0; i < cl.size(); i++) {
				if (cl.get(i) instanceof ClientePJ) {
					System.out.println(i - skip + ". " + cl.get(i).getNome());
					clientespj.add((ClientePJ) cl.get(i));
				}
				else
					skip++; //pula clientes pf
			}
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher Frota:");
			for (int i = 0; i < fr.size(); i++)
				System.out.println(i + ". " + fr.get(i).getCode());
			entr2 = Integer.parseInt(in.nextLine());
			clientespj.get(entr1).adicionarFrota(fr.get(entr2));
			break;
			
		case LISTAR_CLIENTES:
			//Da o nome de todos os clientes de uma seguradora
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarClientes(se.get(entr1).getListaClientes(),0)); //Parametros pra impressao recursiva
			break;
			
		case LISTAR_SEGUROS_POR_SEGURADORA:
			//Da todos o id de todos os sinistros associados a uma seguradora especifica
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarSeguros(se.get(entr1).getListaSeguros(), 0));
			break;
			
		case LISTAR_SINISTROS_POR_SEGURADORA:
			//Da todos o id de todos os sinistros associados a uma seguradora especifica
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarSinistros(se.get(entr1).getListaSeguros(), 0));
			break;
			
		case LISTAR_SEGUROS_POR_CLIENTE:
			//Da todos os ids de todos os sinistros associados a um cliente de uma seguradora
			System.out.println("Escolher Seguradora:");
			for (int i = 0; i < se.size(); i++)
				System.out.println(i + ". " + se.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			System.out.println("Escolher um de seus Clientes:");
			for (int i = 0; i < se.get(entr1).getListaClientes().size(); i++)
				System.out.println(i + ". " + se.get(entr1).getListaClientes().get(i).getNome());
			entr2 = Integer.parseInt(in.nextLine());
			System.out.println(se.get(entr1).listarSeguros(se.get(entr1).getListaClientes().get(entr2).getNome(), 0));
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
			if (cl.get(entr1) instanceof ClientePF)
				System.out.println(((ClientePF) cl.get(entr1)).listarCarros(((ClientePF) cl.get(entr1)).getListaVeiculos(),0));
			else
				System.out.println(((ClientePJ) cl.get(entr1)).listarFrotas(((ClientePJ) cl.get(entr1)).getListaFrota(),0));
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
			//Remove um Veiculo de um Cliente ou de um Frota
			System.out.println("Escolher Cliente:");
			for (int i = 0; i < cl.size(); i++)
				System.out.println(i + ". " + cl.get(i).getNome());
			entr1 = Integer.parseInt(in.nextLine());
			if (cl.get(entr1) instanceof ClientePF) {
			System.out.println("Escolher um de seus Veiculos:");
			for (int i = 0; i < ((ClientePF) cl.get(entr1)).getListaVeiculos().size(); i++)
				System.out.println(i + ". " + ((ClientePF) cl.get(entr1)).getListaVeiculos().get(i).getPlaca());
			entr2 = Integer.parseInt(in.nextLine());
			((ClientePF) cl.get(entr1)).removerCarros(entr2);
			}
			else {
				System.out.println("Escolher Frota:");
				for (int i = 0; i < cl.size(); i++)
					System.out.println(i + ". " + cl.get(i).getNome());
				entr2 = Integer.parseInt(in.nextLine());
				System.out.println("Escolher um de seus Veiculos:");
				for (int i = 0; i < ((ClientePF) cl.get(entr1)).getListaVeiculos().size(); i++)
					System.out.println(i + ". " + ((ClientePF) cl.get(entr1)).getListaVeiculos().get(i).getPlaca());
				entr3 = Integer.parseInt(in.nextLine());
				((ClientePJ) cl.get(entr1)).removerCarros(entr2, entr3);
			}
			break;
			
		case EXCLUIR_SEGURO:
			break;
			
		case VOLTAR:
			break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op, List<Cliente> cl, List<Veiculo> ve, List<Seguradora> se, List<Frota> fr, List<Condutor> cr) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, cl, ve, se, fr, cr);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) {
		MenuOpcoes op;
		List<Cliente> todosClientes = new ArrayList<Cliente>(); //A ideia é ter listas com objetos desassociados a uma seguradora
		List<Veiculo> todosVeiculos = new ArrayList<Veiculo>(); //É que eles possam ser registrados numa seguradora apenas posteriormente
		List<Seguradora> todasSeguradoras = new ArrayList<Seguradora>(); //Ou entao serem associados a seguradoras diferentes
		//De forma que o usuario seria uma especie de agencia reguladora ciente
		List<Frota> todasFrotas = new ArrayList<Frota>(); //De forma que o usuario seria uma especie de agencia reguladora ciente
		List<Condutor> todosCondutores = new ArrayList<Condutor>(); //De tudo que ocorre em todas as seguradoras
		todosClientes.add(new ClientePF("98067475504", "outro", instanciarDatas("11-02-1999"), "Ensino Superior", instanciarDatas("10-01-1981"), 
				"C", 20, "Alex", "Rua X", "A@lex.com", "192"));
		todosClientes.add(new ClientePJ("56075103000175", instanciarDatas("10-12-1835"), "Bernard e Co.", "Rua Z", "Bco@gmail.com", "191", 350));
		todosVeiculos.add(new Veiculo("ISB-3707", "Ford", "Ka", 2010));
		todasSeguradoras.add(new Seguradora("07423289000109", "Seguros San", "195", "SanSeg@ymail.com", "Rua Y"));
		todasFrotas.add(new Frota(0));
		todosCondutores.add(new Condutor("98067475504", "Alex", "Rua X", "A@lex.com", "192", instanciarDatas("10-01-1981")));
		
		//Criacao serve para registrar as classes em suas respectivas todasX listas;
		//Registrar serve para associar as classes entre si; ex: um cliente é cliente de uma seguradora, um carro faz parte de uma frota etc
		//Excluir eh oposto ao registro, nao a criacao
		//Listar retorna strings curtas com as informacoes relevantes	
		System.out.println("Bem-Vindo . . .");
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op, todosClientes, todosVeiculos, todasSeguradoras, todasFrotas, todosCondutores);
		}while(op != MenuOpcoes.SAIR);
		for (int i = 0; i < todosClientes.size(); i++)
		System.out.println("Saiu do sistema");
	}
	
	public static Cliente criarCliente() {
		//Criando Cliente
				Validacao validacao; //Usado para validar CPF ou CNPJ
				System.out.println("\nFavor registrar Cliente . . .");
				String nome;
				do {
					System.out.println("Nome: ");
					nome = in.nextLine().replaceAll("[^A-Za-z]+", "");
					if (nome == "")
						System.out.println("Erro, o nome deve conter ao menos uma letra");
				} while (nome == ""); //Para assegurar que o nome é composto por ao menos uma letra
				System.out.println("Endereco: ");
				String endereco = in.nextLine();
				System.out.println("Email: ");
				String email = in.nextLine();
				System.out.println("Telefone: ");
				String telefone = in.nextLine();
				System.out.println("Digitar 0 se Trata-se de PF, ou 1 se Trata-se de PJ");
				int pfpj = Integer.parseInt(in.nextLine());
				if (pfpj == 0) {
					validacao = new Validacao("","",0);
					do {
					System.out.println("CPF: ");
					String cpf = in.nextLine();
					validacao.setCPF(cpf);
					} while (validacao.verificarValidade() == false);
					System.out.println("Genero: ");
					String genero = in.nextLine();
					System.out.println("Data de Licenca (dd-MM-yyyy): ");
					LocalDate dataLicenca = entradaDatas();
					System.out.println("Nivel de Educacao: ");
					String educacao = in.nextLine();
					System.out.println("Data de Nascimento (dd-MM-yyyy): ");
					LocalDate dataNascimento = entradaDatas();
					System.out.println("Classe Economica: ");
					String classeEconomica = in.nextLine();
					System.out.println("Idade: ");
					int idade = Integer.parseInt(in.nextLine());
					ClientePF clientepf = new ClientePF(validacao.getCPF(), genero, dataLicenca, educacao, dataNascimento,
							classeEconomica, idade, nome, endereco, email, telefone);//instancia obj da classe ClientePF
					System.out.println(". . .\nPessoa Fisica Registrada!\nVerificar Informacoes:\n" + clientepf.toString(0));
					return clientepf;
				}
				else {
					validacao = new Validacao("","",1);
					do {
					System.out.println("CNPJ: ");
					String cnpj = in.nextLine();
					validacao.setCNPJ(cnpj);
					} while(validacao.verificarValidade() == false);
					LocalDate dataFundacao;
					System.out.println("Data de Fundacao (dd-MM-yyyy): ");
					dataFundacao = entradaDatas();
					System.out.println("Numero de Funcionarios: ");
					int qtdeFuncionarios = Integer.parseInt(in.nextLine());
					ClientePJ clientepj = new ClientePJ(validacao.getCNPJ(), dataFundacao, nome, endereco, email, telefone, qtdeFuncionarios);//instancia obj da classe ClientePJ
					System.out.println(". . .\nPessoa Juridica Registrada!\nVerificar Informacoes:\n" + clientepj.toString(0));
					return clientepj;
				}
	}
	
	/*public static Sinistro criarSinistro(Seguradora se, int indicecl, int indiceve) {
		Sinistro sinistro = new Sinistro();
		return sinistro;
	}*/
	
	public static LocalDate entradaDatas() { //Le inputs e converte Strings em Datas no formato dd-MM-yyyy
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-yyyy");
		LocalDate data = null;
		do {
		String entrada = in.nextLine();
		try{data = LocalDate.parse(entrada, formato);
		} catch (java.time.format.DateTimeParseException de) {
			System.out.println("Erro de Formatacao, tente novamente . . .");
		}
		} while (data == null);
		return data;
	}
	
	public static LocalDate instanciarDatas(String entrada) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-yyyy");
		return LocalDate.parse(entrada, formato);
	}
} //fim da classe Main
