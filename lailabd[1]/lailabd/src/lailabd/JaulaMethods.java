package lailabd;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class JaulaMethods {

    // Método para cadastrar uma jaula no Firebase
   public static void cadastrarJaula() {
        Jaula j = new Jaula();
        Scanner leia = new Scanner(System.in);
        DaoJaula dao = new DaoJaula();

        System.out.print("Número da Jaula: ");
        j.setNumJaula(leia.nextInt());
        leia.nextLine();

        System.out.print("Tamanho: ");
        j.setTamanho(leia.nextInt());
        leia.nextLine();

        System.out.print("Data da última limpeza (formato: yyyy-mm-dd): ");
        String dataLimpezaStr = leia.nextLine();

        // Usando SimpleDateFormat para converter a String para Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dataLimpeza = format.parse(dataLimpezaStr);
            j.setLimpeza(dataLimpeza);
        } catch (Exception e) {
            System.out.println("Formato de data inválido. O formato esperado é yyyy-mm-dd.");
            return;
        }

        System.out.print("Manutenção (última manutenção): ");
        j.setManutencao(leia.nextLine());

        dao.setJ(j);
        dao.gravar();

        Map<String, Object> dados = new HashMap<>();
        dados.put("NumJaula", j.getNumJaula());
        dados.put("Tamanho", j.getTamanho());
        dados.put("Limpeza", j.getLimpeza());
        dados.put("Manutencao", j.getManutencao());

        Firestore db = Conexao.initializeFirebase();
        if (db != null) {
            db.collection("jaulas").document(String.valueOf(j.getNumJaula())).set(dados);
            System.out.println("Jaula cadastrada no Firestore!");
        } else {
            System.out.println("Erro ao conectar com Firestore.");
        }
    }
    // Método para buscar uma jaula no Firebase
    public static void buscarJaula() {
        Firestore db = Conexao.initializeFirebase();
        if (db == null) {
            System.out.println("Erro ao conectar ao Firestore!");
            return;
        }

        Scanner leia = new Scanner(System.in);
        System.out.println("Digite o número da jaula: ");
        
        int numJaula;
        try {
            numJaula = Integer.parseInt(leia.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Número inválido! Digite um número.");
            return;
        }

        String documentoId = String.valueOf(numJaula);
        String colecao = "jaulas";

        Map<String, Object> dados = buscarDocumento(colecao, documentoId);

        if (dados == null) {
            System.out.println("Registro não encontrado!");
        } else {
            System.out.println("\n=== Dados da jaula ===");
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

    // Método para atualizar dados de uma jaula
    public static void atualizarJaula() {
        Scanner leia = new Scanner(System.in);

        System.out.println("Número da jaula:");
        int numJaula = leia.nextInt();
        leia.nextLine();

        System.out.println("Novo tamanho:");
        int novoTamanho = leia.nextInt();
        leia.nextLine();

        System.out.println("Nova manutenção:");
        String novaManutencao = leia.nextLine();

        Map<String, Object> novosDados = new HashMap<>();
        novosDados.put("Tamanho", novoTamanho);
        novosDados.put("Manutencao", novaManutencao);

        String documentoId = String.valueOf(numJaula);
        String colecao = "jaulas";

        Firestore db = Conexao.initializeFirebase();
        DocumentReference docRef = db.collection(colecao).document(documentoId);
        ApiFuture<WriteResult> resultado = docRef.update(novosDados);
        try {
            System.out.println("Documento atualizado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            System.err.println("Erro ao atualizar documento: " + e.getMessage());
        }
    }

    // Método para deletar uma jaula
    public static void deletarJaula() {
        Firestore db = Conexao.initializeFirebase();
        Scanner leia = new Scanner(System.in);

        System.out.println("Qual é o número da jaula: ");
        int numJaula = leia.nextInt();

        String documentoId = String.valueOf(numJaula);
        String colecao = "jaulas";

        if (!documentoExiste(colecao, documentoId)) {
            System.out.println("Registro não encontrado!");
            return;
        }

        deletarDocumentoJaula(colecao, documentoId);
        System.out.println("Registro excluído com sucesso!");
    }

    public static void deletarDocumentoJaula(String colecao, String documentoId) {
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

    // Método para listar todas as jaulas
    public static void listarJaulas() {
        Firestore db = Conexao.initializeFirebase();
        if (db == null) {
            System.out.println("Erro ao conectar ao Firestore!");
            return;
        }

        String colecao = "jaulas";
        
        try {
            // Recuperando todos os documentos da coleção
            ApiFuture<QuerySnapshot> future = db.collection(colecao).get();
            QuerySnapshot querySnapshot = future.get();
            
            if (querySnapshot.isEmpty()) {
                System.out.println("Não há jaulas cadastradas.");
                return;
            }
            
            System.out.println("\n=== Lista de Jaulas ===");
            
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
