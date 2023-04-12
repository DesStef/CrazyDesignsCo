package bg.finalexam.crazydesignsco.service.impl;

import bg.finalexam.crazydesignsco.model.dto.comment.CreateCommentDTO;
import bg.finalexam.crazydesignsco.model.entity.CommentEntity;
import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.mapper.DesignMapper;
import bg.finalexam.crazydesignsco.model.mapper.UserMapper;
import bg.finalexam.crazydesignsco.model.view.CommentViewModel;
import bg.finalexam.crazydesignsco.repository.CommentRepository;
import bg.finalexam.crazydesignsco.repository.DesignRepository;
import bg.finalexam.crazydesignsco.repository.UserRepository;
import bg.finalexam.crazydesignsco.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final DesignRepository designRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DesignMapper designMapper;

    public CommentServiceImpl(CommentRepository commentRepository, DesignRepository designRepository, UserRepository userRepository, UserMapper userMapper, DesignMapper designMapper) {
        this.commentRepository = commentRepository;
        this.designRepository = designRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.designMapper = designMapper;
    }

    @Override
    public List<CommentViewModel> getAllCommentsForDesign(UUID designId) {
        DesignEntity design = designRepository.findById(designId).orElseThrow(null);

        List<CommentEntity> comments = commentRepository.findAllByDesignEntity(design);
        return comments.stream().map(comment -> new CommentViewModel(comment.getId(),
                comment.getUser().getFullName(), comment.getUserComment())).collect(Collectors.toList());
    }

    @Override
    public CommentViewModel createComment(CreateCommentDTO createCommentDTO) {
        UserEntity userEntity = userRepository.findByEmail(createCommentDTO.getEmail()).get();

        CommentEntity comment = new CommentEntity();

        comment.setDesign(designRepository.getReferenceById(createCommentDTO.getDesignId()));
        comment.setUser(userEntity);
        comment.setUserComment(createCommentDTO.getMessage());

        commentRepository.save(comment);

        return new CommentViewModel(comment.getId(), comment.getUser().getFullName(), comment.getUserComment());
    }
}
