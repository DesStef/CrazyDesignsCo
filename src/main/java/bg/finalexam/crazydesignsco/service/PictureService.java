package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.PictureEntity;
import bg.finalexam.crazydesignsco.repository.PictureRepository;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public PictureEntity addPicture(String imageUrl, DesignEntity design) {
        PictureEntity pictureEntity = new PictureEntity();

        pictureEntity.setImageUrl(imageUrl);
        pictureEntity.setDesign(design);

        pictureRepository.save(pictureEntity);

        return pictureEntity;
    }

    public void deletePicture(Long picId) {
        pictureRepository.deleteById(picId);
    }

}
