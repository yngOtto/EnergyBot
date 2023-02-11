package org.example.repositorys;


import org.example.entity.kube.Pode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PodeRepository extends JpaRepository<Pode, Long> {
  Optional<Pode> findAllByName(String name);
}
