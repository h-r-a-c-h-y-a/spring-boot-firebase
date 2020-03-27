package com.cerebrum.demo.bootfirebaseauth.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FirebaseConfig {
    static {
        try {
            FirebaseApp.initializeApp(new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
