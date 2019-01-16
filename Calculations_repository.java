package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import hello.History;
@Repository
public interface  Calculations_repository extends CrudRepository<History, Long>
{

}
