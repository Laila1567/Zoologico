package lailabd;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class Conexao {
    public static Firestore bd;
    public static void main(String[] args) {
        Firestore db = initializeFirebase();
        if (db != null) {
            System.out.println("Conexão com Firestore estabelecida com sucesso!");
        } else {
            System.out.println("Falha ao conectar com Firestore.");
        }
    }

    public static Firestore initializeFirebase() {
        if (FirebaseApp.getApps().isEmpty()) { // Verifica se já existe uma instância
            try {
                InputStream serviceAccount = new FileInputStream("lailabd[1]\\lailabd\\ChaveZOO.json");
                GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(credentials)
                        .build();

                FirebaseApp.initializeApp(options);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return FirestoreClient.getFirestore(); // Retorna a instância existente
    }

}
