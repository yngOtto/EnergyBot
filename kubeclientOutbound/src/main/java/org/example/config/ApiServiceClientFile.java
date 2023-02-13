package org.example.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ApiServiceClientFile {

  private ApiClient client;
  private boolean isLoaded;
  private static ApiServiceClientFile INSTANCE=null;

  private ApiServiceClientFile(File file) throws IOException {
    this.client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(file))).build();
    this.isLoaded =true;
  }

  public static ApiServiceClientFile getInstance(File file) throws IOException {
    if (INSTANCE == null){
      INSTANCE = new ApiServiceClientFile(file);

    }
    return INSTANCE;
  }
  public static ApiServiceClientFile getInstance() throws IOException {
    if (INSTANCE != null){
      return INSTANCE;
    }
    String filePath = "config";
    File file = new File(filePath);
    if (file.exists()) {
      INSTANCE = new ApiServiceClientFile(file);
    }

   return null;
  }

  public ApiClient getClient() {
    return client;
  }

  public boolean isLoaded() {
    return isLoaded;
  }
}
