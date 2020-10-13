package edu.bistu.gwhost.dao.repository;

import edu.bistu.gwhost.dao.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity, Long>
{
    Optional<MusicEntity> findById(Long id);
    List<MusicEntity> findAll();
}
