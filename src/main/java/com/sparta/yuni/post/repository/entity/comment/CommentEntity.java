package com.sparta.yuni.post.repository.entity.comment;


import com.sparta.yuni.common.domain.PositiveIntegerCounter;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.post.domain.content.CommentContent;
import com.sparta.yuni.post.repository.entity.post.PostEntity;
import com.sparta.yuni.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sample_board_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;
    private Integer likeCount;

    public CommentEntity(Comment comment){
        this.id= comment.getId();
        this.author = new UserEntity(comment.getAuthor());
        this.post = new PostEntity(comment.getPost());
        this.content = comment.getContentText();
        this.likeCount = comment.getLikeCount();
    }

    public Comment toComment(){
        return Comment.builder()
            .id(id)
            .author(author.toUser())
            .post(post.toPost())
            .content(new CommentContent(content))
            .likeCounter(new PositiveIntegerCounter(likeCount))
            .build();

    }

}
