package org.example.service.kubeservices;

import org.example.ports.api.KubeApiClientServicePort;
import org.example.ports.spi.KubeApiClientPersistencePort;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class kubeApiClientServiceImpl implements KubeApiClientServicePort {



  private KubeApiClientPersistencePort kubeApiClientPersistencePort;

  public kubeApiClientServiceImpl(KubeApiClientPersistencePort kubeApiClientPersistencePort) {
    this.kubeApiClientPersistencePort = kubeApiClientPersistencePort;
  }

  @Override
  public boolean saveFile(MultipartFile multipartFile) throws IOException {
    InputStream inputStream =  new BufferedInputStream(multipartFile.getInputStream());
    return kubeApiClientPersistencePort.saveFile(inputStream);
  }
}
