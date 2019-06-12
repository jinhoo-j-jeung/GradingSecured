package gradingsecured.com.gradingsecured.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayer;

import gradingsecured.com.gradingsecured.R;
import gradingsecured.com.gradingsecured.view_adapters.VideoListAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Main Fragment displaying a list of vidoes that the user can comment
 */
public class VideoFragment extends android.support.v4.app.Fragment {
  ArrayList<String> profiles = new ArrayList<>();
  ArrayList<String> titles = new ArrayList<>();
  ArrayList<String> uploaders = new ArrayList<>();
  ArrayList<Date> dates = new ArrayList<>();
  ArrayList<String> thumbnails = new ArrayList<>();

  /*
   * Default Constructor
   */
  public static VideoFragment newInstance() {
    VideoFragment fragment = new VideoFragment();
    return fragment;
  }

  /*
   * Craete a view for the fragment
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_video, container, false);

    /*
     * Add relevant data to ArrayLists
     */
    Date date = Calendar.getInstance().getTime();
    profiles.add("https://yt3.ggpht.com/a-/AN66SAw_XS4yDtkh95u0YPOvmWh-Us1i2UaGkcRDcQ=s288-mo-c-c0xffffffff-rj-k-no");
    titles.add("Steve Jobs' 2005 Stanford Commencement Address");
    uploaders.add("Steve Jobs");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/UF8uR6Z6KLc/0.jpg");
    profiles.add("https://yt3.ggpht.com/a-/AN66SAzTnfZZPKLAfPgEsAKQkew8uEGzkMFrLhJYZw=s88-mo-c-c0xffffffff-rj-k-no");
    titles.add("How great leaders inspire action | Simon Sinek");
    uploaders.add("TED");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/qp0HIF3SfI4/0.jpg");
    profiles.add("https://yt3.ggpht.com/a-/AN66SAxIEUI6f-101_t2Dy8703mNjD8eikQOVffxBw=s88-mo-c-c0xffffffff-rj-k-no");
    titles.add("Authoritarianism: Last Week Tonight with John Oliver (HBO)");
    uploaders.add("LastWeekTonight");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/ximgPmJ9A5s/0.jpg");
    profiles.add("https://yt3.ggpht.com/a-/AN66SAzVdWJPusWtrkuDLTzWmLkdXG-fvfopnsbuJA=s88-mo-c-c0xffffffff-rj-k-no");
    titles.add("Ilhan Omar - Fighting for a Better Life for All Americans | The Daily Show");
    uploaders.add("The Daily Show with Trevor Noah");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/dKhhMCyXfck/0.jpg");
    profiles.add("https://yt3.ggpht.com/a-/AN66SAw_XS4yDtkh95u0YPOvmWh-Us1i2UaGkcRDcQ=s288-mo-c-c0xffffffff-rj-k-no");
    titles.add("Steve Jobs' 2005 Stanford Commencement Address");
    uploaders.add("Steve Jobs");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/UF8uR6Z6KLc/0.jpg");
    profiles.add("https://yt3.ggpht.com/a-/AN66SAw_XS4yDtkh95u0YPOvmWh-Us1i2UaGkcRDcQ=s288-mo-c-c0xffffffff-rj-k-no");
    titles.add("Steve Jobs' 2005 Stanford Commencement Address");
    uploaders.add("Steve Jobs");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/UF8uR6Z6KLc/0.jpg");
    profiles.add("https://yt3.ggpht.com/a-/AN66SAw_XS4yDtkh95u0YPOvmWh-Us1i2UaGkcRDcQ=s288-mo-c-c0xffffffff-rj-k-no");
    titles.add("Steve Jobs' 2005 Stanford Commencement Address");
    uploaders.add("Steve Jobs");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/UF8uR6Z6KLc/0.jpg");

    /*
     * Initialize a recycler view.
     */
    RecyclerView mRecyclerView = view.findViewById(R.id.video_rv);

    /*
     * Initialize an adapter with data
     */
    VideoListAdapter mAdapter = new VideoListAdapter(getContext(), profiles, titles, uploaders, dates, thumbnails);
    mAdapter.notifyDataSetChanged();
    /*
     * Link the view with the adapter.
     */
    LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);

    return view;
  }
}