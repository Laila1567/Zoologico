package lailabd;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Menu {

	public static void main(String[] args) {
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
				AnimalsMethods.cadastrarAnimal();
			}
			break;
			case 2:{
				//listarAnimal();
			}
			break;
			case 3:{
				AnimalsMethods.buscarAnimal();

			}
			break;
			case 4: {
				AnimalsMethods.atualizarAnimal();
			}
			break;
			case 5: {
				AnimalsMethods.deletarAnimal();
			}
			break;
			case 6:
				break;
			}
		}while(escolha != 6);
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
				JaulaMethods.cadastrarJaula();
			}
			break;
			case 2:{
				JaulaMethods.listarJaulas();
			}
			break;
			case 3:{
				JaulaMethods.buscarJaula();

			}
			break;
			case 4: {
				JaulaMethods.atualizarJaula();
			}
			break;
			case 5: {
				JaulaMethods.deletarJaula();
			}
			break;
			case 6:
				break;
			}
		}while(escolha != 6);
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
				FuncionarioMethods.cadastrarFuncionario();
			}
			break;
			case 2:{
				FuncionarioMethods.listarFuncionarios();
			}
			break;
			case 3:{
				FuncionarioMethods.buscarFuncionario();

			}
			break;
			case 4: {
				FuncionarioMethods.atualizarFuncionario();
			}
			break;
			case 5: {
				FuncionarioMethods.deletarFuncionario();
			}
			break;
			case 6:
				break;
			}
		}while(escolha != 6);
	}

	private static void menu3() {
		// TODO Auto-generated method stub

	}
	private static void menu() {
		System.out.println(" escolha uma opção: ");
		System.out.println("1-Funcionario\n"
				+ "2-Jaulas\n"
				+ "3-Animais\n"
				+ "4-Sair\n"
				+ "Opção: ");
	}

	private static void menu2() {
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
