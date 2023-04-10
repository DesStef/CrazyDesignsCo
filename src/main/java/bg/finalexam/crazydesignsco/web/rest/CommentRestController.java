package bg.finalexam.crazydesignsco.web.rest;

import bg.finalexam.crazydesignsco.exception.ObjectNotFoundException;
import bg.finalexam.crazydesignsco.model.dto.comment.CommentMessageDTO;
import bg.finalexam.crazydesignsco.model.dto.comment.CreateCommentDTO;
import bg.finalexam.crazydesignsco.model.user.DesignCoUserDetails;
import bg.finalexam.crazydesignsco.model.view.CommentViewModel;
import bg.finalexam.crazydesignsco.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{designId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(@PathVariable("designId") UUID designId) {
        return ResponseEntity.ok(commentService.getAllCommentsForDesign(designId));
    }

    @PostMapping(value = "/{designId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentViewModel> createComment(@PathVariable("designId") UUID designId,
                                                         @AuthenticationPrincipal DesignCoUserDetails userDetails,
                                                         @RequestBody CommentMessageDTO commentMessageDTO) {

        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setDesignId(designId);
        createCommentDTO.setEmail(userDetails.getUsername());
        createCommentDTO.setMessage(commentMessageDTO.getMessage());

        CommentViewModel comment = commentService.createComment(createCommentDTO);

        return ResponseEntity
                .created(URI.create(String.format("/api/%s/comments/%d", designId, comment.getId())))
                .body(comment);
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<ErrorApiResponse> handleDesignNotFound() {
        return ResponseEntity.status(404).body(new ErrorApiResponse("This design doesn't exist!", 1004));
    }
}

class ErrorApiResponse {
    private String message;
    private Integer errorCode;

    public ErrorApiResponse(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}