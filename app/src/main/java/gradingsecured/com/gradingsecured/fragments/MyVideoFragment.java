package gradingsecured.com.gradingsecured.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import gradingsecured.com.gradingsecured.UploadVideoActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gradingsecured.com.gradingsecured.R;
import gradingsecured.com.gradingsecured.view_adapters.MyVideoListAdapter;

/*
 * Main Fragment displaying the user's videos
 */
public class MyVideoFragment extends android.support.v4.app.Fragment {

  ArrayList<String> profiles = new ArrayList<>();
  ArrayList<String> titles = new ArrayList<>();
  ArrayList<String> uploaders = new ArrayList<>();
  ArrayList<Date> dates = new ArrayList<>();
  ArrayList<String> thumbnails = new ArrayList<>();
  ArrayList<Integer> feedback_numbers = new ArrayList<>();

  /*
   * Default Constructor
   */
  public static MyVideoFragment newInstance() {
    MyVideoFragment fragment = new MyVideoFragment();
    return fragment;
  }

  /*
   * Craete a view for the fragment
   */
  /*
   * Craete a view for the fragment
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_myvideo, container, false);

    /*
     * Add relevant data to ArrayLists
     */
    Date date = Calendar.getInstance().getTime();
    profiles.add("https://yt3.ggpht.com/a-/AN66SAw_XS4yDtkh95u0YPOvmWh-Us1i2UaGkcRDcQ=s288-mo-c-c0xffffffff-rj-k-no");
    titles.add("Steve Jobs' 2005 Stanford Commencement Address");
    uploaders.add("Steve Jobs");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/UF8uR6Z6KLc/0.jpg");
    feedback_numbers.add(3);

    profiles.add("https://yt3.ggpht.com/a-/AN66SAzTnfZZPKLAfPgEsAKQkew8uEGzkMFrLhJYZw=s88-mo-c-c0xffffffff-rj-k-no");
    titles.add("How great leaders inspire action | Simon Sinek");
    uploaders.add("TED");
    dates.add(date);
    thumbnails.add("http://img.youtube.com/vi/qp0HIF3SfI4/0.jpg");
    feedback_numbers.add(2);

    FloatingActionButton button = view.findViewById(R.id.add_video_fab);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getActivity(), UploadVideoActivity.class);
        startActivity(intent);
      }
    });

    /*
     * Initialize a recycler view.
     */
    RecyclerView mRecyclerView = view.findViewById(R.id.myvideo_rv);

    /*
     * Initialize an adapter with data
     */
    MyVideoListAdapter mAdapter = new MyVideoListAdapter(getContext(), profiles, titles, uploaders, dates, thumbnails, feedback_numbers);
    mAdapter.notifyDataSetChanged();

    /*
     * Link the view with the adapter.
     */
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);

    return view;
  }
}
