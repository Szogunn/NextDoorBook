package pl.orange.NextDoorBook.author.dto;

import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.author.Author;

import java.util.HashSet;

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

    public AuthorAddDTO authorTOAuthorAddDTOMap(Author author) {

        return new AuthorAddDTO(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getNationality()
        );
    }


    public Author authorAddDTOToAuthorMap(AuthorAddDTO authorAddDTO){
        return Author.builder()
                .id(authorAddDTO.id())
                .firstName(authorAddDTO.firstName())
                .lastName(authorAddDTO.lastName())
                .nationality(authorAddDTO.nationality())
                .books(new HashSet<>())
                .build();
    }
}
