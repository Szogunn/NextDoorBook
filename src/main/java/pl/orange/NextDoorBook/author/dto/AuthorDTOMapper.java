package pl.orange.NextDoorBook.author.dto;

import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.author.Author;

@Service
public class AuthorDTOMapper {

    public AuthorDTO authorTOAuthorDTOMap(Author author) {

        return new AuthorDTO(
                author.getFirstName(),
                author.getLastName(),
                author.getNationality()
        );
    }


    public Author authorDTOToAuthorMap(AuthorDTO authorDTO){
        return Author.builder()
                .firstName(authorDTO.firstName())
                .lastName(authorDTO.lastName())
                .nationality(authorDTO.nationality())
                .build();
    }
}
