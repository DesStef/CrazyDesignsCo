package bg.finalexam.crazydesignsco.service.impl;

import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.PictureEntity;
import bg.finalexam.crazydesignsco.repository.PictureRepository;
import bg.finalexam.crazydesignsco.service.PictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public PictureEntity addPicture(String imageUrl, DesignEntity design) {
        PictureEntity pictureEntity = new PictureEntity();

        pictureEntity.setImageUrl(imageUrl);
        pictureEntity.setDesign(design);

        pictureRepository.save(pictureEntity);

        return pictureEntity;
    }

    @Override
    public void deletePicture(Long picId) {
        pictureRepository.deleteById(picId);
    }

}
