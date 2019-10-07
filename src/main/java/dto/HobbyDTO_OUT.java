package dto;

import entities.Hobby;
import entities.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Objects;

@Schema(name = "Hobby")
public class HobbyDTO_OUT {
    @Schema(required = true,example = "Lacrosse")
    private String name;
    @Schema(required = true,example = "Rich People Game")
    private String description;

    public HobbyDTO_OUT() {
    }

    public HobbyDTO_OUT(HobbyDTO_IN hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public HobbyDTO_OUT(Hobby hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public HobbyDTO_OUT(String name, String description, List<Person> persons) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HobbyDTO_OUT other = (HobbyDTO_OUT) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
}
