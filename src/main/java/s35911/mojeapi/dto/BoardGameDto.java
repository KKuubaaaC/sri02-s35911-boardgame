package s35911.mojeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameDto {
    private Long id;
    private String title;
    private String publisher;
    private Integer releaseYear;
    private Integer maxPlayers;
}
