package lailabd;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class FuncionarioMethods {

    // Método para cadastrar um funcionário no Firebase
    public static void cadastrarFuncionario() {
        Funcionario f = new Funcionario();
        Scanner leia = new Scanner(System.in);
        DaoFuncionario dao = new DaoFuncionario();

        System.out.print("ID: ");
        f.setCodFuncionario(leia.nextInt());
        leia.nextLine();

        System.out.print("Nome: ");
        f.setNome(leia.nextLine());

        System.out.print("CPF: ");
        f.setCpf(leia.nextLine());

        System.out.print("Funcao: ");
        f.setFuncao(leia.nextLine());

        System.out.print("Telefone: ");
        f.setTelefone(null);leia.nextDouble();
        leia.nextLine();

        System.out.print("Turno: ");
        f.setTurno(leia.nextLine());

        dao.setF(f);
        dao.gravar();

        Map<String, Object> dados = new HashMap<>();
        dados.put("ID", f.getCodFuncionario());
        dados.put("Nome", f.getNome());
        dados.put("Funcao", f.getFuncao());
        dados.put("Telefone", f.getTelefone());
        dados.put("Turno", f.getTurno());

        Firestore db = Conexao.initializeFirebase();
        if (db != null) {
            db.collection("funcionarios").document(String.valueOf(f.getCodFuncionario())).set(dados);
            System.out.println("Funcionário cadastrado no Firestore!");
        } else {
            System.out.println("Erro ao conectar com Firestore.");
        }
    }

    // Método para buscar um funcionário no Firebase
    public static void buscarFuncionario() {
        Firestore db = Conexao.initializeFirebase();
        if (db == null) {
            System.out.println("Erro ao conectar ao Firestore!");
            return;
        }

        Scanner leia = new Scanner(System.in);
        System.out.println("Digite o ID do funcionário: ");
        
        int idFuncionario;
        try {
            idFuncionario = Integer.parseInt(leia.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Digite um número.");
            return;
        }

        String documentoId = String.valueOf(idFuncionario);
        String colecao = "funcionarios";

        Map<String, Object> dados = buscarDocumento(colecao, documentoId);

        if (dados == null) {
            System.out.println("Registro não encontrado!");
        } else {
            System.out.println("\n=== Dados do funcionário ===");
            dados.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
        }
    }

    public static Map<String, Object> buscarDocumento(String colecao, String documentoId) {
        Firestore db = FirestoreClient.getFirestore();

        if (db == null) {
            System.out.println("Erro: Firestore não foi inicializado corretamente!");
            return null;
        }

        try {
            DocumentSnapshot document = db.collection(colecao).document(documentoId).get().get();
            if (document.exists()) {
                return document.getData();
            } else {
                System.out.println("Documento não encontrado.");
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Erro ao buscar documento: " + e.getMessage());
            return null;
        }
    }

    // Método para atualizar dados de um funcionário
    public static void atualizarFuncionario() {
        Scanner leia = new Scanner(System.in);

        System.out.println("ID do funcionário:");
        int idFuncionario = leia.nextInt();
        leia.nextLine();

        System.out.println("Novo salário:");
        double novoSalario = leia.nextDouble();
        leia.nextLine();

        System.out.println("Novo turno:");
        String novoTurno = leia.nextLine();

        Map<String, Object> novosDados = new HashMap<>();
        novosDados.put("Salario", novoSalario);
        novosDados.put("Turno", novoTurno);

        String documentoId = String.valueOf(idFuncionario);
        String colecao = "funcionarios";

        Firestore db = Conexao.initializeFirebase();
        DocumentReference docRef = db.collection(colecao).document(documentoId);
        ApiFuture<WriteResult> resultado = docRef.update(novosDados);
        try {
            System.out.println("Documento atualizado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            System.err.println("Erro ao atualizar documento: " + e.getMessage());
        }
    }

    // Método para deletar um funcionário
    public static void deletarFuncionario() {
        Firestore db = Conexao.initializeFirebase();
        Scanner leia = new Scanner(System.in);

        System.out.println("Qual é o ID do funcionário: ");
        int idFuncionario = leia.nextInt();

        String documentoId = String.valueOf(idFuncionario);
        String colecao = "funcionarios";

        if (!documentoExiste(colecao, documentoId)) {
            System.out.println("Registro não encontrado!");
            return;
        }

        deletarDocumentoFuncionario(colecao, documentoId);
        System.out.println("Registro excluído com sucesso!");
    }

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
    // Método para listar todos os funcionários
public static void listarFuncionarios() {
    Firestore db = Conexao.initializeFirebase();
    if (db == null) {
        System.out.println("Erro ao conectar ao Firestore!");
        return;
    }

    String colecao = "funcionarios";
    
    try {
        // Recuperando todos os documentos da coleção
        ApiFuture<QuerySnapshot> future = db.collection(colecao).get();
        QuerySnapshot querySnapshot = future.get();
        
        if (querySnapshot.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
            return;
        }
        
        System.out.println("\n=== Lista de Funcionários ===");
        
        // Iterando sobre cada documento da coleção e imprimindo seus dados
        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
            System.out.println("\nID: " + document.getId());
            document.getData().forEach((chave, valor) -> {
                System.out.println(chave + ": " + valor);
            });
            System.out.println("-------------------------------");
        }
    } catch (InterruptedException | ExecutionException e) {
        System.out.println("Erro ao listar documentos: " + e.getMessage());
    }
}

}
