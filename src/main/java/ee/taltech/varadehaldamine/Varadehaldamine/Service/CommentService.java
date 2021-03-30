package ee.taltech.varadehaldamine.Varadehaldamine.Service;

import ee.taltech.varadehaldamine.Varadehaldamine.Model.Comment;
import ee.taltech.varadehaldamine.Varadehaldamine.ModelDTO.CommentInfo;
import ee.taltech.varadehaldamine.Varadehaldamine.Repository.AssetRepository;
import ee.taltech.varadehaldamine.Varadehaldamine.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AssetRepository assetRepository;

    public List<CommentInfo> getAllByAssetId(String assetId) {
        List<CommentInfo> comments = new LinkedList<>();
        for (Comment comment : commentRepository.findAllByAssetId(assetId)) {
            CommentInfo commentInfo = new CommentInfo(comment.getAssetId(), comment.getText(), comment.getCreatedAt().getTime());
            comments.add(commentInfo);
        }
        return comments;
    }

    public CommentInfo addComment(CommentInfo commentInfo) {
        if (!assetRepository.findAllById(Collections.singletonList(commentInfo.getAssetId())).isEmpty() && !commentInfo.getText().isBlank()) {
            Comment comment = new Comment(commentInfo.getAssetId(), commentInfo.getText(), new Timestamp(System.currentTimeMillis()));
            Comment newComment = commentRepository.save(comment);
            return new CommentInfo(newComment.getAssetId(), newComment.getText(), newComment.getCreatedAt().getTime());
        }
        return null;
    }

}
