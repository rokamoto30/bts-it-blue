package net.bts.hr.challenge.repo;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bts.hr.challenge.model.Speed;  

@Repository
public interface SpeedRepo extends CrudRepository<Speed, Integer>
{
	@Query(value="SELECT * FROM speed s", nativeQuery=true)
	public List<Speed> getSpeeds();
}
