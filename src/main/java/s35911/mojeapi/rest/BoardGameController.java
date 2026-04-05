package s35911.mojeapi.rest;

import s35911.mojeapi.dto.BoardGameDto;
import s35911.mojeapi.model.BoardGame;
import s35911.mojeapi.repo.BoardGameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {

    private final BoardGameRepository boardGameRepository;
    private final ModelMapper modelMapper;

    public BoardGameController(BoardGameRepository boardGameRepository, ModelMapper modelMapper) {
        this.boardGameRepository = boardGameRepository;
        this.modelMapper = modelMapper;
    }

    private BoardGameDto convertToDto(BoardGame e) {
        return modelMapper.map(e, BoardGameDto.class);
    }

    private BoardGame convertToEntity(BoardGameDto dto) {
        return modelMapper.map(dto, BoardGame.class);
    }

    @GetMapping
    public ResponseEntity<Collection<BoardGameDto>> getBoardGames() {
        List<BoardGame> allGames = boardGameRepository.findAll();
        List<BoardGameDto> result = allGames.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<BoardGameDto> getBoardGameById(@PathVariable Long gameId) {
        Optional<BoardGame> game = boardGameRepository.findById(gameId);
        if (game.isPresent()) {
            BoardGameDto dto = convertToDto(game.get());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> saveNewBoardGame(@RequestBody BoardGameDto dto) {
        BoardGame entity = convertToEntity(dto);
        boardGameRepository.save(entity);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Void> updateBoardGame(@PathVariable Long gameId, @RequestBody BoardGameDto dto) {
        Optional<BoardGame> currentGame = boardGameRepository.findById(gameId);
        if (currentGame.isPresent()) {
            dto.setId(gameId);
            BoardGame entity = convertToEntity(dto);
            boardGameRepository.save(entity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteBoardGame(@PathVariable Long gameId) {
        boolean found = boardGameRepository.existsById(gameId);
        if (found) {
            boardGameRepository.deleteById(gameId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}