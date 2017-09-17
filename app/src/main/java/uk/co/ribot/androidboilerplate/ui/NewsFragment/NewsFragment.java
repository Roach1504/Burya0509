package uk.co.ribot.androidboilerplate.ui.NewsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.News;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.prsenterS.ListNewsPresenter;


public class NewsFragment extends BaseFragment implements NewsMvpView {

    @Inject
    ListNewsPresenter listNewsPresenter;

    @Inject
    NewsAdapter newsAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);


        recyclerView.setAdapter(newsAdapter);

        listNewsPresenter.attachView(this);
        //listNewsPresenter.loadNews("0");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (rootView.getParent() != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }




    //loadNews для загрузки новостей в качестве параметра отправлять с какого элемента выгружать, выгружать будет по 20
    @Override
    public void showNews(List<News> news) {
        //отобразить лист новостей

    }

    @Override
    public void showError() {
        //сообщения об ошибки

    }


}
