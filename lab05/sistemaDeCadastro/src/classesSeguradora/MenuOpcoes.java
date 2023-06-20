package classesSeguradora;

/* enum para menu externo
Cada constante é vista como uma descrição e uma lista de outras constantes (que são as constantes do submenu)

new SubmenuOpcoes[]{} cria uma lista de constantes do submenu.
*/
public enum MenuOpcoes {
	CRIACAO("Criacao", new SubmenuOpcoes[] {
		SubmenuOpcoes.CRIAR_CLIENTE,
		SubmenuOpcoes.CRIAR_VEICULO,
		SubmenuOpcoes.CRIAR_SEGURADORA,
		SubmenuOpcoes.CRIAR_FROTA,
		SubmenuOpcoes.CRIAR_CONDUTOR,
		SubmenuOpcoes.VOLTAR
	}),
	
	CADASTROS("Cadastros", new SubmenuOpcoes[] {
			SubmenuOpcoes.CADASTRAR_CLIENTE,
			SubmenuOpcoes.CADASTRAR_VEICULO_CLIENTEPF,
			SubmenuOpcoes.CADASTRAR_FROTA_CLIENTEPJ,
			SubmenuOpcoes.CADASTRAR_VEICULO_FROTA,
			SubmenuOpcoes.VOLTAR
	}),
	LISTAR("Listar", new SubmenuOpcoes[] {
			SubmenuOpcoes.LISTAR_CLIENTES,
			SubmenuOpcoes.LISTAR_SINISTROS_POR_SEGURADORA,
			SubmenuOpcoes.LISTAR_SEGUROS_POR_SEGURADORA,
			SubmenuOpcoes.LISTAR_SEGUROS_POR_CLIENTE,
			SubmenuOpcoes.LISTAR_VEICULOS_POR_SEGURADORA,
			SubmenuOpcoes.LISTAR_VEICULOS_POR_CLIENTE,
			SubmenuOpcoes.VOLTAR
	}),
	EXCLUIR("Excluir", new SubmenuOpcoes[] {
			SubmenuOpcoes.EXCLUIR_CLIENTE,
			SubmenuOpcoes.EXCLUIR_VEICULO,
			SubmenuOpcoes.EXCLUIR_SEGURO,
			SubmenuOpcoes.EXCLUIR_FROTA,
			SubmenuOpcoes.VOLTAR}),
	GERAR_SEGURO("Gerar Seguro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	SAIR("Sair", new SubmenuOpcoes[] {});
	
	//Atributos
	private final String descricao;
	private final SubmenuOpcoes[] submenu;
	
	//Construtor
	MenuOpcoes(String descricao, SubmenuOpcoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	//Getters
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOpcoes[] getSubmenu() {
		return submenu;
	}
}
