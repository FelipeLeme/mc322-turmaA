package classesSeguradora;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//User Inputs
		Scanner in = new Scanner(System.in);
		
		//Criando Seguradora
		System.out.println("Bem-Vindo . . .\nFavor registrar Seguradora . . .\nNome: ");
		String nome = in.nextLine();
		System.out.println("\nTelefone: ");
		String telefone = in.nextLine();
		System.out.println("\nEmail: ");
		String email = in.nextLine();
		System.out.println("\nEndereco: ");
		String endereco = in.nextLine();
		Seguradora seguradora1 = new Seguradora(nome, telefone, email, endereco); //instancia obj da classe Seguradora
		System.out.println(". . .\nSeguradora Registrada!\nVerificar Informacoes:\n" + seguradora1.toString());
		
		//Criando Cliente
		System.out.println("Favor registrar Cliente . . .\nNome: ");
		nome = in.nextLine();
		System.out.println("\nCPF: ");
		String cpf = in.nextLine();
		System.out.println("\nData de Nascimento: ");
		String dataNascimento = in.nextLine();
		System.out.println("\nIdade: ");
		int idade = in.nextInt();
		in.nextLine(); //Consome \n
		System.out.println("\nEndereco: ");
		endereco = in.nextLine();
		Cliente cliente1 = new Cliente(nome, cpf, dataNascimento, idade, endereco); //instancia obj da classe Cliente
		
		//Verificando do CPF
		boolean validadeCPF = cliente1.validarCPF(cliente1.getCPF());
		while (!validadeCPF) {
			System.out.println("ERRO! CPF invalido, favor providenciar CPF valido:\n");
			cliente1.setCPF(in.nextLine());
			validadeCPF = cliente1.validarCPF(cliente1.getCPF());
		}
		System.out.println(". . .\nCliente Registrado!\nVerificar Informacoes:\n" + cliente1.toString());
		
		//Criando Veiculo
		System.out.println("Favor registrar Veiculo . . .\nPlaca: ");
		String placa = in.nextLine();
		System.out.println("\nMarca: ");
		String marca = in.nextLine();
		System.out.println("\nModelo: ");
		String modelo = in.nextLine();
		Veiculo veiculo1 = new Veiculo(placa, marca, modelo); //instancia obj da classe Veiculo
		System.out.println(". . .\nVeiculo Registrado!\nVerificar Informacoes:\n" + veiculo1.toString());
		
		//Criando Sinistro
		System.out.println("Favor registrar Sinistro . . .\nData: ");
		String data = in.nextLine();
		System.out.println("\nEndereco: ");
		endereco = in.nextLine();
		System.out.println("\nGerando ID unico . . .");
		Random random = new Random();
		int id = random.nextInt(9999);
		Sinistro sinistro1 = new Sinistro(id, data, endereco); //instancia obj da classe Sinistro
		System.out.println(". . .\nSinistro Registrado!\nVerificar Informacoes:\n" + sinistro1.toString());
		
	} //fim do metodo main
} //fim da classe Main
