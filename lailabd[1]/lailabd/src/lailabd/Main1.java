package lailabd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		int escolha;
		do{
			menu();
			escolha = leia.nextInt();
			switch (escolha){
			case 1:{
				funcionario();
			}
			break;
			case 2:{
				jaula();
			}
			break;
			case 3:{
				animal();
			}
			break;
			case 4: {
				sair();
			}
			break;
			}
		}while(escolha != 4);
	}


	private static void sair() {
		// TODO Auto-generated method stub

	}

	private static void animal() {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		int escolha;
		do{
			menu2();
			escolha = leia.nextInt();
			switch (escolha){
			case 1:{
				cadastrarAnimal();
			}
			break;
			case 2:{
				listarAnimal();
			}
			break;
			case 3:{
				buscarAnimal();

			}
			break;
			case 4: {
				atualizarAnimal();
			}
			break;
			case 5: {
				deletarAnimal();
			}
			break;
			case 6:
				break;
			}
		}while(escolha != 6);
	}



	private static void listarAnimal() {
		// TODO Auto-generated method stub
		
	}


	private static void deletarAnimal() {
		// TODO Auto-generated method stub
		Animal a = new Animal();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoAnimal dao = new DaoAnimal();

		System.out.println("Qual é o Codigo do funcionario:");
		a.setCodAnimal(leia.nextInt());
		dao.setA(a);
		dao.deletar();
		

	}


	private static void atualizarAnimal() {
		// TODO Auto-generated method stub
		Animal a = new Animal();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoAnimal dao = new DaoAnimal();

		System.out.println("Qual é o Codigo do funcionario:");
		a.setCodAnimal(leia.nextInt());
		System.out.println("Nome:");
		a.setNomeAnimal(leia.nextLine());
		System.out.println("Especies:");
		a.setEspecies(leia.nextLine());
		System.out.println("Sexo:");
		a.setSexo(leia.nextLine());
		System.out.println("Data de nascimento:");
		String dataTexto = leia.nextLine();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataNasci = formato.parse(dataTexto);
			a.setDataNasc(dataNasci);
		} catch (Exception e) {
			System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
		}
		System.out.println("Quantidade na jaula:");
		a.setQuantJaula(leia.nextInt());
		System.out.println("Alimentação 1:");
		a.setAlimentacao1(leia.nextLine());
		System.out.println("Alimentação 2:");
		a.setAlimentacao2(leia.nextLine());
		System.out.println("Alimentação 3:");
		a.setAlimentacao3(leia.nextLine());
		System.out.println("Horario da alimentação 1:");
		a.setHalimentacao1(leia.nextInt());
		System.out.println("Horario da alimentação 2:");
		a.setHalimentacao2(leia.nextInt());
		System.out.println("Horario da alimentação 3:");
		a.setHalimentacao3(leia.nextInt());
		dao.setA(a);
		dao.atualizar();

	}


	private static void buscarAnimal() {
		// TODO Auto-generated method stub
		Animal a = new Animal();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoAnimal dao = new DaoAnimal();
		System.out.println("Você quer burcar por nome ou codigo?");
		int op;
		do{
			menu3();
			op = leia.nextInt();
			switch (op){
			case 1:{
				System.out.println("Nome:");
				a.setNomeAnimal(leia.nextLine());
			}
			break;
			case 2:{
				System.out.println("Codigo:");
				a.setCodAnimal(leia.nextInt());
			}
			break;
			case 3:{
				System.out.println("Sair");
			}
			break;
			}
		}while(op!=3);
		dao.setA(a);
		dao.buscar();


	}


	private static void cadastrarAnimal() {
		// TODO Auto-generated method stub
		Animal a = new Animal();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoAnimal dao = new DaoAnimal();

		System.out.println("Codigo:");
		a.setCodAnimal(leia.nextInt());
		System.out.println("Nome:");
		a.setNomeAnimal(leia.nextLine());
		System.out.println("Especies:");
		a.setEspecies(leia.nextLine());
		System.out.println("Sexo:");
		a.setSexo(leia.nextLine());
		System.out.println("Data de nascimento:");
		String dataTexto = leia.nextLine();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataNasci = formato.parse(dataTexto);
			a.setDataNasc(dataNasci);
		} catch (Exception e) {
			System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
		}
		System.out.println("Quantidade na jaula:");
		a.setQuantJaula(leia.nextInt());
		System.out.println("Alimentação 1:");
		a.setAlimentacao1(leia.nextLine());
		System.out.println("Alimentação 2:");
		a.setAlimentacao2(leia.nextLine());
		System.out.println("Alimentação 3:");
		a.setAlimentacao3(leia.nextLine());
		System.out.println("Horario da alimentação 1:");
		a.setHalimentacao1(leia.nextInt());
		System.out.println("Horario da alimentação 2:");
		a.setHalimentacao2(leia.nextInt());
		System.out.println("Horario da alimentação 3:");
		a.setHalimentacao3(leia.nextInt());
		dao.setA(a);
		dao.gravar();

	}


	private static void jaula() {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		int escolha;
		do{
			menu2();
			escolha = leia.nextInt();
			switch (escolha){
			case 1:{
				cadastrarJaula();
			}
			break;
			case 2:{
				listarJaula();
			}
			break;
			case 3:{
				buscarJaula();

			}
			break;
			case 4: {
				atualizarJaula();
			}
			break;
			case 5: {
				deletarJaula();
			}
			break;
			case 6:
				break;
			}
		}while(escolha != 6);
	}


	private static void listarJaula() {
		// TODO Auto-generated method stub
		
	}


	private static void deletarJaula() {
		// TODO Auto-generated method stub
		Jaula j = new Jaula();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoJaula dao = new DaoJaula();
		System.out.println("qual é o numero da jaula?");
		j.setNumJaula(leia.nextInt());
		dao.setJ(j);
		dao.deletar();


	}


	private static void atualizarJaula() {
		// TODO Auto-generated method stub
		Jaula j = new Jaula();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoJaula dao = new DaoJaula();
		
		System.out.println("Qual é o numero da Jaula:");
		j.setNumJaula(leia.nextInt());
		System.out.println("Tamanho:");
		j.setTamanho(leia.nextInt());
		System.out.println("Última limpeza:");
		String dataTexto = leia.nextLine();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataLimpeza = formato.parse(dataTexto);
			j.setLimpeza(dataLimpeza);
		} catch (Exception e) {
			System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
		}

		System.out.println("Manuteção:");
		j.setManutencao(leia.nextLine());

		dao.setJ(j);
		dao.atualizar();

	}


	private static void buscarJaula() {
		// TODO Auto-generated method stub
		Jaula j = new Jaula();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoJaula dao = new DaoJaula();

		System.out.println("Nome:");
		j.setNumJaula(leia.nextInt());
		dao.setJ(j);
		dao.buscar();

	}


	private static void cadastrarJaula() {
		// TODO Auto-generated method stub
		Jaula j = new Jaula();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoJaula dao = new DaoJaula();
		System.out.println("Numero:");
		j.setNumJaula(leia.nextInt());
		System.out.println("Tamanho:");
		j.setTamanho(leia.nextInt());
		System.out.println("Última limpeza:");
		String dataTexto = leia.nextLine();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataLimpeza = formato.parse(dataTexto);
			j.setLimpeza(dataLimpeza);
		} catch (Exception e) {
			System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
		}

		System.out.println("Manuteção:");
		j.setManutencao(leia.nextLine());

		dao.setJ(j);
		dao.gravar();
	}


	private static void funcionario() {
		// TODO Auto-generated method stub
		Scanner leia = new Scanner(System.in);
		int escolha;
		do{
			menu2();
			escolha = leia.nextInt();
			switch (escolha){
			case 1:{
				cadastrarFuncionario();
			}
			break;
			case 2:{
				listarFuncionario();
			}
			break;
			case 3:{
				buscarFuncionario();

			}
			break;
			case 4: {
				atualizarFuncionario();
			}
			break;
			case 5: {
				deletarFuncionario();
			}
			break;
			case 6:
				break;
			}
		}while(escolha != 6);
	}

	private static void listarFuncionario() {
		// TODO Auto-generated method stub
		
	}


	private static void deletarFuncionario() {
		// TODO Auto-generated method stub
		Funcionario f = new Funcionario();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoFuncionario dao = new DaoFuncionario();
		System.out.println("qual é o codigo do funcionario?");
		f.setCodFuncionario(leia.nextInt());
		dao.setF(f);
		dao.deletar();

	}


	private static void buscarFuncionario() {
		// TODO Auto-generated method stub
		Funcionario f = new Funcionario();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoFuncionario dao = new DaoFuncionario();
		System.out.println("Você quer burcar por nome ou codigo?");
		int op;
		do{
			menu3();
			op = leia.nextInt();
			switch (op){
			case 1:{
				System.out.println("Nome:");
				f.setNome(leia.nextLine());
			}
			break;
			case 2:{
				System.out.println("Codigo:");
				f.setCodFuncionario(leia.nextInt());
			}
			break;
			case 3:{
				System.out.println("Sair");
			}
			break;
			}
		}while(op!=3);
		dao.setF(f);
		dao.buscar();

	}


	private static void menu3() {
		// TODO Auto-generated method stub

	}


	private static void atualizarFuncionario() {
		// TODO Auto-generated method stub
		Funcionario f = new Funcionario();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoFuncionario dao = new DaoFuncionario();
		System.out.println("qual é o codigo do funcionario?");
		f.setCodFuncionario(leia.nextInt());
		System.out.println("Nome:");
		f.setNome(leia.nextLine());
		System.out.println("Funcâo:");
		f.setFuncao(leia.nextLine());
		System.out.println("Turno:");
		f.setTurno(leia.nextLine());
		System.out.println("CPF:");
		f.setCpf(leia.nextInt());
		System.out.println("Telefone:");
		f.setTelefone(leia.nextLine());
		dao.setF(f);
		dao.atualizar();

	}




	private static void cadastrarFuncionario() {
		// TODO Auto-generated method stub
		Funcionario f = new Funcionario();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoFuncionario dao = new DaoFuncionario();
		System.out.println("Nome:");
		f.setNome(leia.nextLine());
		System.out.println("Codigo:");
		f.setCodFuncionario(leia.nextInt());
		System.out.println("Funcâo:");
		f.setFuncao(leia.nextLine());
		System.out.println("Turno:");
		f.setTurno(leia.nextLine());
		System.out.println("CPF:");
		f.setCpf(leia.nextInt());
		System.out.println("Telefone:");
		f.setTelefone(leia.nextLine());
		dao.setF(f);
		dao.gravar();
	}


	private static void menu() {
		// TODO Auto-generated method stub
		System.out.println(" escolha uma opção: ");
		System.out.println("1-Funcionario\n"
				+ "2-Jaulas\n"
				+ "3-Animais\n"
				+ "4-Sair\n"
				+ "Opção: ");
	}

	private static void menu2() {
		// TODO Auto-generated method stub
		System.out.println(" escolha uma opção: ");
		System.out.println("1-Castratar\n"
				+ "2-Listar\n"
				+ "3-Buscar\n"
				+ "4-Atualizar\n"
				+ "5-deletar\n"
				+ "6-voltar\n"
				+ "Opção: ");
	}


}
