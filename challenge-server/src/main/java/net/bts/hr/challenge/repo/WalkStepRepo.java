package net.bts.hr.challenge.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bts.hr.challenge.model.WalkedSteps;  

@Repository
public interface WalkStepRepo extends CrudRepository<WalkedSteps, Integer>
{

}
