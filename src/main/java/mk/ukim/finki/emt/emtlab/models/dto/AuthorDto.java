package mk.ukim.finki.emt.emtlab.models.dto;

import lombok.Data;

@Data
public class AuthorDto {

    private String name;

    private String surname;

    private String country;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
