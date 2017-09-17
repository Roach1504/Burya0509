package uk.co.ribot.androidboilerplate.ui.NewsFragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RibotViewHolder> {


    private List<News> mRibots;

    @Inject
    public NewsAdapter() {
        mRibots = new ArrayList<>();

    }

    public void setRibots(List<News> ribots) {
        mRibots = ribots;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, int position) {


        News example = mRibots.get(position);
        //Log.e("ExampleList", "id=" + example.getId() + " url= " + example.getAvatarUrl());

//        if (example.getAvatarUrl() != null)
//            if (!example.getAvatarUrl().isEmpty()) {
//                Picasso.with(holder.itemView.getContext())
//                        .load(example.getAvatarUrl())
//                        .placeholder(R.drawable.ic_account_circle_black_24dp)
//                        .fit()
//                        .centerCrop()
//                        .into(holder.avatar);
//            }
holder.dateTextView.setText(example.getDate());
        holder.autorTextView.setText("Autor");
        holder.headTextView.setText(example.getTitle());
        holder.subTextView.setText(example.getShort());
        holder.viewsTextView.setText(example.getViews());
        holder.ratingTextView.setText(example.getMark());


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                itemClickAdapter(v.getContext(), holder.getAdapterPosition());
                Log.e("click", "click= " + holder.getAdapterPosition());

            }
        });
    }

//    public void itemClickAdapter(Context context, int position) {
//        Example example = mRibots.get(position);
//        Intent intent = new Intent(context, UserActivity.class);
//
//        intent.putExtra("Id", example.getId());
//        if (example.getAvatarUrl() == null) {
//            intent.putExtra("AvatarUrl", "");
//        } else {
//            intent.putExtra("AvatarUrl", example.getAvatarUrl());
//        }
//        intent.putExtra("FirstName", example.getFirstName());
//        intent.putExtra("LastName", example.getLastName());
//        intent.putExtra("Email", example.getEmail());
//
//        context.startActivity(intent);

//    }

    @Override
    public int getItemCount() {
        return mRibots.size();
    }


    class RibotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.textDate)
        TextView dateTextView;
        @BindView(R.id.textAutor)
        TextView autorTextView;
        @BindView(R.id.head)
        TextView headTextView;
        @BindView(R.id.subtitle)
        TextView subTextView;
        @BindView(R.id.views)
        TextView viewsTextView;
        @BindView(R.id.rating)
        TextView ratingTextView;
        @BindView(R.id.layout)
        View layout;


        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
