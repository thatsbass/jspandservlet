package com.example.webapp;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcatServer {
    public static void main(String[] args) throws Exception {
        // Création d'une instance de Tomcat
        Tomcat tomcat = new Tomcat();
        
        // Définition du port sur lequel Tomcat va écouter
        int port = 8080;
        tomcat.setPort(port);

        // Configuration du répertoire de travail temporaire
        String webAppDir = new File("src/main/webapp/").getAbsolutePath();
        tomcat.addWebapp("/", webAppDir);

        System.out.println("✅ Tomcat est démarré sur http://localhost:" + port);

        // Démarrer le serveur
        tomcat.start();
        tomcat.getServer().await();
    }
}
