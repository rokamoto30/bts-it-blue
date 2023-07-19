package net.bts.hr.challenge.repo;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bts.hr.challenge.model.WalkedSteps;  

@Repository
public interface WalkStepRepo extends CrudRepository<WalkedSteps, Integer>
{
	@Query(value="SELECT * FROM walked_steps w", nativeQuery=true)
	public List<WalkedSteps> getSteps();
}
