package uk.co.ribot.androidboilerplate.ui.CommentFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Comment;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;


public interface CommentsMvpView extends MvpView {
    void showComments(List<Comment> comments);

    void showError();

    void showCommentsEmpty();

}
