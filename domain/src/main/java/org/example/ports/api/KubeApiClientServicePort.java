package org.example.ports.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface KubeApiClientServicePort {


  boolean saveFile(MultipartFile multipartFile) throws IOException;
}
