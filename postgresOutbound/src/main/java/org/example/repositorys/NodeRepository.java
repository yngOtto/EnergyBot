package org.example.repositorys;

import org.example.entity.kube.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
  Node findByName(String name);

  boolean existsByName(String name);
}
