package pl.gran.gran.trail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CruxRepository extends JpaRepository<Crux, Long> {

	List<Crux> findByTrailIdOrderByPositionAsc(Long trailId);

	void deleteByTrailId(Long trailId);

}
