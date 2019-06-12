package gradingsecured.com.gradingsecured.view_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import gradingsecured.com.gradingsecured.FeedbackActivity;
import gradingsecured.com.gradingsecured.R;
import gradingsecured.com.gradingsecured.image_adapters.CircularImageView;
import java.util.ArrayList;
import java.util.Date;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {
  private Context context;
  private ArrayList<String> profiles;
  private ArrayList<String> titles;
  private ArrayList<String> uploaders;
  private ArrayList<Date> dates;
  private ArrayList<String> thumbnails;

  public VideoListAdapter(Context context, ArrayList<String> profiles, ArrayList<String> titles, ArrayList<String> uploaders, ArrayList<Date> dates, ArrayList<String> thumbnails) {
    this.context = context;
    this.profiles = profiles;
    this.titles = titles;
    this.uploaders = uploaders;
    this.dates = dates;
    this.thumbnails = thumbnails;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    CardView row_cv;
    LinearLayout row_ll;
    CircularImageView profile_iv;
    TextView title_tv;
    TextView uploader_tv;
    TextView date_tv;
    ImageView thumbnail_iv;

    public ViewHolder(View v) {
      super(v);
      row_cv = v.findViewById(R.id.video_row_cv);
      row_ll = v.findViewById(R.id.video_row_ll);
      profile_iv = v.findViewById(R.id.video_row_profile_iv);
      title_tv = v.findViewById(R.id.video_row_title_tv);
      uploader_tv = v.findViewById(R.id.video_row_uploader_tv);
      date_tv = v.findViewById(R.id.video_row_date_tv);
      thumbnail_iv = v.findViewById(R.id.video_row_thumbnail_iv);
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.video_list_row, null);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    final String profile_url = profiles.get(position);
    final String title = titles.get(position);
    final String uploader = uploaders.get(position);
    Date date = dates.get(position);
    final String thumbnail_url = thumbnails.get(position);

    Glide.with(context).load(profile_url).into(holder.profile_iv);
    holder.title_tv.setText(title);
    holder.uploader_tv.setText(uploader);
    holder.date_tv.setText(date.toString());
    Glide.with(context).load(thumbnail_url).into(holder.thumbnail_iv);

    if(position%2 == 1) {
      holder.row_cv.setCardBackgroundColor(Color.rgb(230, 230, 230));
    }

    holder.row_cv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("profile_url",profile_url);
        intent.putExtra("title", title);
        intent.putExtra("uploader", uploader);
        intent.putExtra("thumbnail_url", thumbnail_url);
        context.startActivity(intent);
      }
    });

    holder.title_tv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("profile_url",profile_url);
        intent.putExtra("title", title);
        intent.putExtra("uploader", uploader);
        intent.putExtra("thumbnail_url", thumbnail_url);
        context.startActivity(intent);
      }
    });

    holder.uploader_tv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("profile_url",profile_url);
        intent.putExtra("title", title);
        intent.putExtra("uploader", uploader);
        intent.putExtra("thumbnail_url", thumbnail_url);
        context.startActivity(intent);
      }
    });

    holder.date_tv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("profile_url",profile_url);
        intent.putExtra("title", title);
        intent.putExtra("uploader", uploader);
        intent.putExtra("thumbnail_url", thumbnail_url);
        context.startActivity(intent);
      }
    });

    holder.thumbnail_iv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("profile_url",profile_url);
        intent.putExtra("title", title);
        intent.putExtra("uploader", uploader);
        intent.putExtra("thumbnail_url", thumbnail_url);
        context.startActivity(intent);
      }
    });

  }

  @Override
  public int getItemCount() {
    return titles.size();
  }
}
