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
				AnimalsMethods.cadastrarAnimal();
			}
			break;
			case 2:{
				AnimalsMethods.buscarAnimal();
			}
			break;
			case 3:{
				AnimalsMethods.atualizarAnimal();

			}
			break;
			case 4: {
				AnimalsMethods.deletarAnimal();
			}
			break;
			case 5:
				break;
			}
		}while(escolha != 5);
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
				JaulaMethods.buscarJaula();

			}
			break;
			case 3: {
				JaulaMethods.atualizarJaula();
			}
			break;
			case 4: {
				JaulaMethods.deletarJaula();
			}
			break;
			case 5:
				break;
			}
		}while(escolha != 5);
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
				FuncionariosMethods.cadastrarFuncionario();
			}
			break;
			case 2:{
				FuncionariosMethods.buscarFuncionario();
			}
			break;
			case 3:{
				FuncionariosMethods.atualizarFuncionario();

			}
			break;
			case 4: {
				FuncionariosMethods.deletarFuncionario();
			}
			break;
			case 5: {
			}
			break;
			}
		}while(escolha != 5);
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
			+ "2-Buscar\n"
			+ "3-Atualizar\n"
			+ "4-deletar\n"
			+ "5-voltar\n"
			+ "Opção: ");
}


}
