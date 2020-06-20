package com.b2w.api.desafio.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.b2w.api.desafio.models.Planeta;

@Repository
@Transactional
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {

}
