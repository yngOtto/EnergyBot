package org.example.repositorys;

import org.example.entity.kube.NodeUsages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeUsagesJpaRepository extends JpaRepository<NodeUsages, Long> {
  List<NodeUsages> findAllByNodeName(String name);
}
