package org.example.adapters;

import org.example.config.ApiServiceClientFile;
import org.example.ports.spi.KubeApiClientPersistencePort;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class KubeApiClientAdapter implements KubeApiClientPersistencePort {


  @Override
  public boolean saveFile(InputStream inputStream) throws IOException {
    File file = new File("config");
    try (OutputStream output = new FileOutputStream(file)) {
      inputStream.transferTo(output);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    File newfile = new File("config");
    ApiServiceClientFile apiServiceClientFile = ApiServiceClientFile.getInstance(newfile);
    return apiServiceClientFile.isLoaded();
  }
}
