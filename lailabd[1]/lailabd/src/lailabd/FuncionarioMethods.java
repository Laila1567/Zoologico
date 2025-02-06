package lailabd;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class FuncionariosMethods {


	public static void cadastrarFuncionario() {
		// TODO Auto-generated method stub
		Funcionario f = new Funcionario();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);

		DaoFuncionario dao = new DaoFuncionario();
		System.out.println("Codigo:");
		f.setCodFuncionario(leia.nextInt());
		leia.nextLine();

		System.out.println("Nome:");
		f.setNome(leia.nextLine());

		System.out.println("Funcâo:");
		f.setFuncao(leia.nextLine());

		System.out.println("Turno:");
		f.setTurno(leia.nextLine());

		System.out.println("CPF:");
		f.setCpf(leia.nextInt());

		System.out.println("Telefone:");
		f.setTelefone(null);leia.nextDouble();

		Map<String, Object> dados = new HashMap<>();
		dados.put("Codigo", f.getCodFuncionario());
		dados.put("Nome", f.getNome());
		dados.put("Função", f.getFuncao());
		dados.put("Turno", f.getTurno());
		dados.put("CPF", f.getCpf());
		dados.put("Telefone", f.getTelefone());

		Firestore db = Conexao.initializeFirebase();
		if (db != null) {
			db.collection("funcionarios").document(String.valueOf(f.getCodFuncionario())).set(dados);
			System.out.println("Funcionario cadastrado no Firestore!");
		} else {
			System.out.println("Erro ao conectar com Firestore.");
		}
	}


	//********************************************************************************************************************//
	//********************************************************************************************************************//




	public static void buscarFuncionario() {
		// TODO Auto-generated method stub

		Firestore db = Conexao.initializeFirebase();
		if (db == null) {
			System.out.println("Erro ao conectar ao Firestore!");
			return;
		}

		Scanner leia = new Scanner(System.in);
		System.out.println("Digite o código referente ao funcionario: ");

		int codFuncionario;
		try {
			codFuncionario = Integer.parseInt(leia.nextLine()); // Evita erro do nextInt()
		} catch (NumberFormatException e) {
			System.out.println("Código inválido! Digite um número.");
			return;
		}

		String documentoId = String.valueOf(codFuncionario);
		String colecao = "funcionarios";

		Map<String, Object> dados = buscarDocumento(colecao, documentoId);

		if (dados == null) {
			System.out.println("Registro não encontrado!");
		} else {
			System.out.println("\n=== Dados do funcionario ===");
			dados.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
		}
	}

	//********************************************************************************************************************//
	//********************************************************************************************************************//

	public static Map<String, Object> buscarDocumento(String colecao, String documentoId) {
		Firestore db = FirestoreClient.getFirestore();

		if (db == null) {
			System.out.println("Erro: Firestore não foi inicializado corretamente!");
			return null;
		}

		try {
			DocumentSnapshot document = db.collection(colecao).document(documentoId).get().get();
			if (document.exists()) {
				return document.getData(); // Retorna os dados do documento
			} else {
				System.out.println("Documento não encontrado.");
				return null;
			}
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Erro ao buscar documento: " + e.getMessage());
			return null;
		}
	}
	public static void atualizarFuncionario() {
		// TODO Auto-generated method stub
		Funcionario f = new Funcionario();
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		DaoFuncionario dao = new DaoFuncionario();

		System.out.println("Codigo do funcionario:");
		int codFuncionario = leia.nextInt();


		System.out.println("Funcâo:");
		f.setFuncao(leia.nextLine());
		leia.nextLine();

		System.out.println("Turno:");
		f.setTurno(leia.nextLine());

		System.out.println("Telefone:");
		f.setTelefone(leia.nextLine());

		Map<String, Object> novosDados = new HashMap<>();
		novosDados.put("Função", f.getFuncao());
		novosDados.put("Turno", f.getTurno());
		novosDados.put("Telefone", f.getTelefone());

		String documentoId = String.valueOf(codFuncionario);
		String colecao = "funcionarios";

		// Salvar no Firestore
		Firestore db = Conexao.initializeFirebase();

		DocumentReference docRef = db.collection(colecao).document(documentoId);
		ApiFuture<WriteResult> resultado = docRef.update(novosDados);
		try {
			System.out.println("Documento atualizado em: " + resultado.get().getUpdateTime());
		} catch (Exception e) {
			System.err.println("Erro ao atualizar documento: " + e.getMessage());
		}

		System.out.println("Dados atualizados com sucesso!");
	}
	public static void deletarFuncionario() {
		// TODO Auto-generated method stub
		Firestore db = Conexao.initializeFirebase();
		Funcionario f = new Funcionario();

		@SuppressWarnings("resource")

		Scanner leia = new Scanner(System.in);
		DaoFuncionario dao = new DaoFuncionario();

		System.out.println("Qual é o Codigo do Funcionario: ");
		int codFuncionario = leia.nextInt();
		f.setCodFuncionario(codFuncionario);

		String documentoId = String.valueOf(codFuncionario);
		String colecao = "funcionarios";

		if (!documentoExiste(colecao, documentoId)) {
			System.out.println("Registro não encontrado!");
			return;
		}

		deletarDocumentoFuncionario(colecao, documentoId);
		System.out.println("Registro excluído com sucesso!");


	}
	//********************************************************************************************************************//
	//********************************************************************************************************************//

	public static void deletarDocumentoFuncionario(String colecao, String documentoId) {
		Firestore db = Conexao.initializeFirebase();

		DocumentReference docRef = db.collection(colecao).document(documentoId);
		ApiFuture<WriteResult> resultado = docRef.delete();
		try {
			System.out.println("Documento deletado em: " + resultado.get().getUpdateTime());
		} catch (Exception e) {
			System.err.println("Erro ao deletar documento: " + e.getMessage());
		}
	}
	//********************************************************************************************************************//
	//********************************************************************************************************************//

	public static boolean documentoExiste(String colecao, String documentoId) {
		Firestore db = FirestoreClient.getFirestore();
		try {

			DocumentSnapshot document = db.collection(colecao).document(documentoId).get().get();
			return document.exists();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}

}
