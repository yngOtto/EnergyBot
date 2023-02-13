package org.example.entity;

public class Deployment {

  private String name;
  private String imageName;
  private String nameSpace;

  private int replicas;
  private int readyreplicas;
  private int updatedReplicas;
  private int unavailableReplicas;



  public int getReplicas() {
    return replicas;
  }

  public void setReplicas(int replicas) {
    this.replicas = replicas;
  }

  public int getReadyreplicas() {
    return readyreplicas;
  }

  public void setReadyreplicas(int readyreplicas) {
    this.readyreplicas = readyreplicas;
  }

  public int getUpdatedReplicas() {
    return updatedReplicas;
  }

  public void setUpdatedReplicas(int updatedReplicas) {
    this.updatedReplicas = updatedReplicas;
  }

  public int getUnavailableReplicas() {
    return unavailableReplicas;
  }

  public void setUnavailableReplicas(int unavailableReplicas) {
    this.unavailableReplicas = unavailableReplicas;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getNameSpace() {
    return nameSpace;
  }

  public void setNameSpace(String nameSpace) {
    this.nameSpace = nameSpace;
  }
}
