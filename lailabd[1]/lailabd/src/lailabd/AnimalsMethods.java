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

public class AnimalsMethods {

    //********************************************************************************************************************//
    //********************************************************************************************************************//
    //Metodo para cadastrar um animal e suas especificações no Firebase.
    public static void cadastrarAnimal() {

        Animal a = new Animal();
        Scanner leia = new Scanner(System.in);
        DaoAnimal dao = new DaoAnimal();

        System.out.print("Código: ");
        a.setCodAnimal(leia.nextInt());
        leia.nextLine(); // Consumir a quebra de linha

        System.out.print("Nome: ");
        a.setNomeAnimal(leia.nextLine());

        System.out.print("Espécie: ");
        a.setEspecies(leia.nextLine());

        System.out.print("Sexo: ");
        a.setSexo(leia.nextLine());

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataTexto = leia.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataNasci = formato.parse(dataTexto);
            a.setDataNasc(dataNasci);
        } catch (Exception e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
            return; // Encerra o cadastro se a data for inválida
        }

        System.out.print("Quantidade na jaula: ");
        a.setQuantJaula(leia.nextInt());
        leia.nextLine();



        System.out.print("Alimentação 1: ");
        a.setAlimentacao1(leia.nextLine());


        System.out.print("Alimentação 2: ");
        a.setAlimentacao2(leia.nextLine());


        System.out.print("Alimentação 3: ");
        a.setAlimentacao3(leia.nextLine());

        System.out.print("Horário da alimentação 1: ");
        a.setHalimentacao1(leia.nextInt());

        System.out.print("Horário da alimentação 2: ");
        a.setHalimentacao2(leia.nextInt());

        System.out.print("Horário da alimentação 3: ");
        a.setHalimentacao3(leia.nextInt());
        leia.nextLine(); // Consumir a quebra de linha

        // Salvar no banco local
        dao.setA(a);
        dao.gravar();

        // Criando um mapa de dados para Firestore
        Map<String, Object> dados = new HashMap<>();
        dados.put("Codigo", a.getCodAnimal());
        dados.put("Nome", a.getNomeAnimal());
        dados.put("Especie", a.getEspecies());
        dados.put("Sexo", a.getSexo());
        dados.put("Data de nascimento", a.getDataNasc());
        dados.put("Quantidade na jaula", a.getQuantJaula());
        dados.put("Alimentacao 1", a.getAlimentacao1());
        dados.put("Alimentacao 2", a.getAlimentacao2());
        dados.put("Alimentacao 3", a.getAlimentacao3());
        dados.put("Hora da alimentacao 1", a.getHalimentacao1());
        dados.put("Hora da alimentacao 2", a.getHalimentacao2());
        dados.put("Hora da alimentacao 3", a.getHalimentacao3());

        // Salvar no Firestore
        Firestore db = Conexao.initializeFirebase();
        if (db != null) {
            db.collection("animais").document(String.valueOf(a.getCodAnimal())).set(dados);
            System.out.println("Animal cadastrado no Firestore!");
        } else {
            System.out.println("Erro ao conectar com Firestore.");
        }
    }

//********************************************************************************************************************//
//********************************************************************************************************************//

    //Metodo para buscar qualquer animal no Firebase por meio do seu codigo
    public static void buscarAnimal() {
        Firestore db = Conexao.initializeFirebase();
        if (db == null) {
            System.out.println("Erro ao conectar ao Firestore!");
            return;
        }

        Scanner leia = new Scanner(System.in);
        System.out.println("Digite o código referente ao animal: ");

        int codAnimal;
        try {
            codAnimal = Integer.parseInt(leia.nextLine()); // Evita erro do nextInt()
        } catch (NumberFormatException e) {
            System.out.println("Código inválido! Digite um número.");
            return;
        }

        String documentoId = String.valueOf(codAnimal);
        String colecao = "animais";

        Map<String, Object> dados = buscarDocumento(colecao, documentoId);

        if (dados == null) {
            System.out.println("Registro não encontrado!");
        } else {
            System.out.println("\n=== Dados do animal ===");
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

    //*****************************************************************************************************************//
    //********************************************************************************************************************//

    //Metodo destinado a fazer alterações nos registros dos animais já cadastrados
    public static void atualizarAnimal() {

        Animal a = new Animal();
        Scanner leia = new Scanner(System.in);


        System.out.println("Codigo do animal:");
        int codAnimal = leia.nextInt();



        System.out.println("Quantidade na jaula:");
        a.setQuantJaula(leia.nextInt());
        leia.nextLine();  // Consome qualquer entrada pendente

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


        Map<String, Object> novosDados = new HashMap<>();
        novosDados.put("Quantidade na jaula", a.getQuantJaula());
        novosDados.put("Alimentacao 1", a.getAlimentacao1());
        novosDados.put("Alimentacao 2", a.getAlimentacao2());
        novosDados.put("Alimentacao 3", a.getAlimentacao3());
        novosDados.put("Hora da alimentacao 1", a.getHalimentacao1());
        novosDados.put("Hora da alimentacao 2", a.getHalimentacao2());
        novosDados.put("Hora da alimentacao 3", a.getHalimentacao3());


        String documentoId = String.valueOf(codAnimal);
        String colecao = "animais";

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

    //********************************************************************************************************************//
    //********************************************************************************************************************//
    public static void deletarAnimal() {
        Firestore db = Conexao.initializeFirebase();
        Animal a = new Animal();

        @SuppressWarnings("resource")

        Scanner leia = new Scanner(System.in);
        DaoAnimal dao = new DaoAnimal();

        System.out.println("Qual é o Codigo do animal: ");
        int codAnimal = leia.nextInt();
        a.setCodAnimal(codAnimal);

        String documentoId = String.valueOf(codAnimal);
        String colecao = "animais";

        if (!documentoExiste(colecao, documentoId)) {
            System.out.println("Registro não encontrado!");
            return;
        }

        deletarDocumentoAnimal(colecao, documentoId);
        System.out.println("Registro excluído com sucesso!");


    }
    //********************************************************************************************************************//
    //********************************************************************************************************************//

    public static void deletarDocumentoAnimal(String colecao, String documentoId) {
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
    //********************************************************************************************************************//
    //********************************************************************************************************************//

}
