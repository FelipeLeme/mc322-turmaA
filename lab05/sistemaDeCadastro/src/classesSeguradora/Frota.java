package classesSeguradora;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Frota { 
	static List<Integer> todoscodigos = new ArrayList<Integer>();
	private int code;
	private List<Veiculo> listaVeiculos;

	//Constructor
		public Frota (int code){
			this.code = code;
			listaVeiculos = new ArrayList<Veiculo>();
			adicionarCodigo(code);
		}
		
	//Getters e Setters
		public int getCode() {
			return code;
		}
		
		public List<Veiculo> getListaVeiculos(){
			return listaVeiculos;
		}
		
		public void setCode(int code) {
			this.code = code;
		}
		
		public void removerCarros(Veiculo v) {
			this.listaVeiculos.remove(v);
		}
		
		public void adicionarCarros(Veiculo v) {
			this.listaVeiculos.add(v);
		}
		
		public String listarCarros(List<Veiculo> listaVeiculos,int i) {
			if (listaVeiculos.size() > i)
				return "\n*" + this.listaVeiculos.get(i).getPlaca() + listarCarros(listaVeiculos,i+1);
			else
				return "";
		}
		
		public int nCarros() {
			return listaVeiculos.size();
		}
		
		public static void adicionarCodigo(int code) {
			Frota.todoscodigos.add(code);
		}
		
		public static Frota criarFrota(List<Frota> fr) {
			Random random = new Random();
			Integer code;
			do {
			code = random.nextInt(9999);
			} while (procurarCode(code, fr) == true);
			Frota frota = new Frota(code);
			System.out.println("Codigo de Frota:" + frota.getCode());
			return frota;
		}
		
		//Metodo procurarCode, garante que o codigo da frota sera unico
		public static boolean procurarCode(int id, List<Frota> fr) {
			if (fr.isEmpty())
				return false;
			else { 
				for (int i = 0; i < Frota.todoscodigos.size(); i++)
					if (Frota.todoscodigos.get(i) == id)
						return true;
				return false;
			}
		}
}
