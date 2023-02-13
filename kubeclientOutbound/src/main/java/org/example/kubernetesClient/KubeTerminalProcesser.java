package org.example.kubernetesClient;

import java.io.IOException;

public class KubeTerminalProcesser {



  public static void Scale(int rep, String deploymentname) throws IOException {
    Runtime rt = Runtime.getRuntime();
    String command = "kubectl scale --replicas="+rep+" deployment/"+deploymentname;
    Process pr = rt.exec(command);
    pr.destroy();
  }
}
