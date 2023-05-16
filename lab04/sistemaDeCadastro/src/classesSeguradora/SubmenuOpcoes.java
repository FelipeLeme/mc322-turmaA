package classesSeguradora;

/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CRIAR_CLIENTE("Criar cliente"),
	CRIAR_VEICULO("Criar veiculo"),
	CRIAR_SEGURADORA("Criar seguradora"),
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SINISTROS_POR_SEGURADORA("Listar sinistros por seguradora"),
	LISTAR_SINISTROS_POR_CLIENTE("Listar sinistros por cliente"),
	LISTAR_VEICULOS_POR_SEGURADORA("Listar veiculo por seguradora"),
	LISTAR_VEICULOS_POR_CLIENTE("Listar veiculo por cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
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
