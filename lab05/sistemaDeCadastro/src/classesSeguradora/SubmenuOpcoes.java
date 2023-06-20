package classesSeguradora;

/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CRIAR_CLIENTE("Criar cliente"),
	CRIAR_VEICULO("Criar veiculo"),
	CRIAR_FROTA("Criar frota"),
	CRIAR_CONDUTOR("Criar condutor"),
	CRIAR_SEGURADORA("Criar seguradora"),
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO_CLIENTEPF("Cadastrar veiculo em ClientePF"),
	CADASTRAR_VEICULO_FROTA("Cadastrar veiculo em Frota de ClientePJ"),
	CADASTRAR_FROTA_CLIENTEPJ("Cadastrar frota em ClientePJ"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SINISTROS_POR_SEGURADORA("Listar sinistros por seguradora"),
	LISTAR_SEGUROS_POR_SEGURADORA("Listar seguros por seguradora"),
	LISTAR_SEGUROS_POR_CLIENTE("Listar seguros por cliente"),
	LISTAR_VEICULOS_POR_SEGURADORA("Listar veiculo por seguradora"),
	LISTAR_VEICULOS_POR_CLIENTE("Listar veiculo por cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_FROTA("Excluir frota"),
	EXCLUIR_SEGURO("Excluir sininstro"),
	VOLTAR("Voltar");
	
	//Atributos
	private final String descricao;
	
	//Construtor
	SubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}
	
	//getter
	public String getDescricao() {
		return descricao;
	}
}
