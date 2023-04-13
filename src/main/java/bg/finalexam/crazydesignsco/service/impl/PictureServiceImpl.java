package bg.finalexam.crazydesignsco.service.impl;

import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.PictureEntity;
import bg.finalexam.crazydesignsco.repository.PictureRepository;
import bg.finalexam.crazydesignsco.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public PictureEntity addPicture(String imageUrl, DesignEntity design) {

        PictureEntity newPicture = new PictureEntity();
        newPicture.setImageUrl(imageUrl);
        newPicture.setDesign(design);

        pictureRepository.save(newPicture);
        return newPicture;
    }

    @Transactional
    @Override
    public void deletePicture(Long picId) {
        pictureRepository.deletePictureEntityById(picId);
    }

    @Transactional
    @Override
    public void deletePicturesByDesign(DesignEntity design) {
        pictureRepository.deletePictureEntitiesByDesign(design);
    }

}
