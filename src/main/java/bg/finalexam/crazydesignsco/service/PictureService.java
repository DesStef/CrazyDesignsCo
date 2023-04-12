package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.PictureEntity;

public interface PictureService {

    PictureEntity addPicture(String imageUrl, DesignEntity design);

    void deletePicture(Long picId);
}
