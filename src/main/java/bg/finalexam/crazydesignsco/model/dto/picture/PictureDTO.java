package bg.finalexam.crazydesignsco.model.dto.picture;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PictureDTO {

    @NotBlank(message = "Please add picture name!")
    @Size(min=1,max=20,message = "Picture name should be between 1 and 20 characters, please!")
    private String name;

    @NotBlank(message = "Please add short description!")
    @Size(min=5, max=100,message = "Picture description should be between 5 and 100 characters.")
    private String description;

    @NotBlank(message = "Please add picture url!")
    @URL(protocol = "http")
    private String url;

    public PictureDTO() {
    }

    public String getName() {
        return name;
    }

    public PictureDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PictureDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureDTO setUrl(String url) {
        this.url = url;
        return this;
    }
}
