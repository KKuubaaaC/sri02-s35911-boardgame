package s35911.mojeapi.repo;

import s35911.mojeapi.model.BoardGame;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BoardGameRepository extends CrudRepository<BoardGame, Long> {
    List<BoardGame> findAll();
}