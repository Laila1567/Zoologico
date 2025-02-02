package lailabd;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


public class Main {
public static void main(String [] args) {
	InputStream serviceAccount=null;
	// Use the application default credentials
	try {
	serviceAccount = new FileInputStream("C:\\Users\\laila\\Documents\\lailabd[1]\\lailabd\\chave2.json");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	GoogleCredentials credentials = null;
	try {
		credentials = GoogleCredentials.fromStream(serviceAccount);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	FirebaseOptions options = FirebaseOptions.builder()
	    .setCredentials(credentials)
	    .build();
	FirebaseApp.initializeApp(options);

	Firestore db = FirestoreClient.getFirestore();
	  // Adding document 1
	
	String [ ] docNames= {"alovelace","Rafa","cbabbage"};
    System.out.println("########## Adding document 1 ##########");
    try {
		addDocument(db,docNames[0]);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    // Adding document 2
    System.out.println("########## Adding document 2 ##########");
    try {
		addDocument(db,docNames[1]);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    // Adding document 3
    System.out.println("########## Adding document 3 ##########");
    try {
		addDocument(db,docNames[2]);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    // retrieve all users born before 1900
    System.out.println("########## users born before 1900 ##########");
    //runQuery();

    // retrieve all users
    System.out.println("########## All users ##########");
   // retrieveAllDocuments();
    System.out.println("###################################");

    // retrieve all users born before 1900
    System.out.println("########## users born before 1900 ##########");
    try {
		runQuery(db);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    // retrieve all users
    System.out.println("########## All users ##########");
    try {
		retrieveAllDocuments(db);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    System.out.println("###################################");

}
public static void addDocument(Firestore db, String docName) throws Exception {
    switch (docName) {
      case "alovelace": {
        // [START firestore_setup_dataset_pt1]
        DocumentReference docRef = db.collection("users").document("alovelace");
        // Add document data  with id 
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Laila");
        data.put("last", "Lovelace");
        data.put("born", 2000);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
        // [END firestore_setup_dataset_pt1]
        break;
      }
      case "aturing": {
        // [START firestore_setup_dataset_pt2]
        DocumentReference docRef = db.collection("users").document("aturing");
        // Add document data with an additional field ("middle")
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Alan");
        data.put("middle", "Mathison");
        data.put("last", "Turing");
        data.put("born", 1912);

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
        // [END firestore_setup_dataset_pt2]
        break;
      }
      case "cbabbage": {
        DocumentReference docRef = db.collection("users").document("cbabbage");
        Map<String, Object> data =
            new ImmutableMap.Builder<String, Object>()
                .put("first", "Charles")
                .put("last", "Babbage")
                .put("born", 1791)
                .build();
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
        break;
      }
      default:
    }
  }

  public static void runQuery(Firestore db) throws Exception {
    // [START firestore_setup_add_query]
    // asynchronously query for all users born before 1900
    ApiFuture<QuerySnapshot> query =
        db.collection("users").whereLessThan("born", 1900).get();
    // ...
    // query.get() blocks on response
    QuerySnapshot querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      System.out.println("User: " + document.getId());
      System.out.println("First: " + document.getString("first"));
      if (document.contains("middle")) {
        System.out.println("Middle: " + document.getString("middle"));
      }
      System.out.println("Last: " + document.getString("last"));
      System.out.println("Born: " + document.getLong("born"));
    }
    // [END firestore_setup_add_query]
  }

  public static void retrieveAllDocuments(Firestore db) throws Exception {
    // [START firestore_setup_dataset_read]
    // asynchronously retrieve all users
    ApiFuture<QuerySnapshot> query = db.collection("users").get();
    // ...
    // query.get() blocks on response
    QuerySnapshot querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      System.out.println("User: " + document.getId());
      System.out.println("First: " + document.getString("first"));
      if (document.contains("middle")) {
        System.out.println("Middle: " + document.getString("middle"));
      }
      System.out.println("Last: " + document.getString("last"));
      System.out.println("Born: " + document.getLong("born"));
    }
    // [END firestore_setup_dataset_read]
  }

  
  /** Closes the gRPC channels associated with this instance and frees up their resources. */
  void close(Firestore db) throws Exception {
    db.close();
  }
}

