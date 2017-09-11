package uk.co.ribot.androidboilerplate.ui.CommentFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Comment;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;


public class CommentsFragment extends BaseFragment implements CommentsMvpView {

    //loadComments - метод выгрузки коментов
    //параметр post_id

    @Override
    public void showComments(List<Comment> comments) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showCommentsEmpty() {

    }
}
