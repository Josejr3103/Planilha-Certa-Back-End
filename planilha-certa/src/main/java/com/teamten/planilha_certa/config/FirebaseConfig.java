package com.teamten.planilha_certa.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() throws IOException {

        //"C:/Users/daldo/OneDrive/Área de Trabalho/GitHub/Planilha-Certa-Back-End/planilha-certa/src/main/java/com/teamten/planilha_certa/planilhacerta-114b1-firebase-adminsdk-mpy8f-322e882417.json"
        //"C:/Users/Felipe/Documents/GitHub/Planilha-Certa-Back-End/planilha-certa/src/main/java/com/teamten/planilha_certa/planilhacerta-114b1-firebase-adminsdk-mpy8f-322e882417.json"
        FileInputStream serviceAccount = new FileInputStream("C:/Users/daldo/OneDrive/Área de Trabalho/GitHub/Planilha-Certa-Back-End/planilha-certa/src/main/java/com/teamten/planilha_certa/planilhacerta-114b1-firebase-adminsdk-mpy8f-322e882417.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        return FirestoreClient.getFirestore();
    }


}
