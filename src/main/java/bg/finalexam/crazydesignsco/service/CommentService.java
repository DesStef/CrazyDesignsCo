package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.comment.CreateCommentDTO;
import bg.finalexam.crazydesignsco.model.view.CommentViewModel;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<CommentViewModel> getAllCommentsForDesign(UUID designId);

    CommentViewModel createComment(CreateCommentDTO createCommentDTO);
}
