package org.example.ports.spi;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface KubeApiClientPersistencePort {

  boolean saveFile(InputStream inputStream) throws IOException;
}
