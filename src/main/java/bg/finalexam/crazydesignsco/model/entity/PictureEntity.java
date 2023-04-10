package bg.finalexam.crazydesignsco.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "pictures")
public class PictureEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    private DesignEntity design;

    public PictureEntity() {
    }

    public PictureEntity(String imageUrl, DesignEntity design) {
        this.imageUrl = imageUrl;
        this.design = design;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PictureEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public DesignEntity getDesign() {
        return design;
    }

    public PictureEntity setDesign(DesignEntity designEntity) {
        this.design = designEntity;
        return this;
    }
}
