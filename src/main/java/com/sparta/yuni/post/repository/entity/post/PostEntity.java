package com.sparta.yuni.post.repository.entity.post;

import com.sparta.yuni.common.domain.PositiveIntegerCounter;
import com.sparta.yuni.common.repository.entity.TimeBaseEntity;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.content.PostContent;
import com.sparta.yuni.post.domain.content.PostPublicationState;
import com.sparta.yuni.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
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
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="sample_board_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String password;


    private String title;
    private String content;

    private Integer likeCount;

    @ColumnDefault("0")
    private int commentCount;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    public PostEntity(Post post){
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost(){
        return Post.builder()
            .id(id)
            .author(author.toUser())
            .content(new PostContent(content))
            .state(state)
            .likeCount(new PositiveIntegerCounter(likeCount))
            .build();
    }

}
