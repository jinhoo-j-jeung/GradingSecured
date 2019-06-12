package gradingsecured.com.gradingsecured.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import gradingsecured.com.gradingsecured.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Radar;
import com.anychart.*;
import com.anychart.core.radar.series.Line;
import com.anychart.core.ui.Legend;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.MarkerType;
//import com.anychart.sample.R;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static gradingsecured.com.gradingsecured.R.*;
import static gradingsecured.com.gradingsecured.R.color.colorEndGradient;

// The sample code for the radar chart class was from gitHub link https://github.com/AnyChart/AnyChart-Android/blob/master/sample/src/main/java/com/anychart/sample/charts/RadarChartActivity.java
// Then it was modified to fit our app
/*
 * Main Fragment displaying the user information
 */
public class ProfileFragment extends android.support.v4.app.Fragment {

  /*
   * Default Constructor
   */
  public static ProfileFragment newInstance() {
    ProfileFragment fragment = new ProfileFragment();
    return fragment;
  }

  /*
   * Craete a view for the fragment
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(layout.fragment_profile, container, false);

    AnyChartView anyChartView = view.findViewById(id.any_chart_view);
    //(findViewById(R.id.progress_bar)); I don't know what this is...
    //textStyle={fontFamily: "Georgia", fontSize: "15px", color: "green"};

    Radar radar = AnyChart.radar();
    //radar.minHeight(430d);
    //radar.minWidth(430d);


    radar.title("Average rating for all videos in each category");
    radar.title().fontSize(20d);
    radar.title().fontColor("#303F9F");
    radar.title().fontStyle("Bold");
    radar.yScale().minimum(1d); // this means it starts at 1
    radar.yScale().maximum(5d); //end at five to replicate our 1-5 star rating
    radar.yScale().minimumGap(0d);
    radar.yScale().maximumGap(1d);//scale of 1
    radar.yScale().ticks().interval(1d); //scale of 1?
    radar.xAxis().labels().padding(5d, 5d, 5d, 5d);
    radar.xAxis().labels().fontSize("20dp");
    // hmm shows up as red? I am guessing string color doesn't work? what is going on
    //String blue=getResources().getString(R.color.colorPrimaryDark);
    radar.xAxis().labels().fontColor("#303F9F");
    radar.xAxis().labels().fontStyle("Bold");
    final Legend enabled = radar.legend()
        .fontSize("40dp")
        .fontColor("#f442e5")
        .align(Align.CENTER)
        .enabled(true);


    //This is where the data goes for the chart, each figure gets it's own value
    List<DataEntry> data = new ArrayList<>();
    data.add(new CustomDataEntry("Clarity", 4));
    data.add(new CustomDataEntry("Content", 1));
    data.add(new CustomDataEntry("Visuals", 5));
    data.add(new CustomDataEntry("Language", 2));
    data.add(new CustomDataEntry("Brevity", 4));


    Set set = Set.instantiate();
    set.data(data);
    Mapping avgDataSet = set.mapAs("{ x: 'x', value: 'value' }");
    //Mapping warriorData = set.mapAs("{ x: 'x', value: 'value2' }");
    //Mapping priestData = set.mapAs("{ x: 'x', value: 'value3' }");

    Line avgRateData = radar.line(avgDataSet);
    avgRateData.color("#f442e5"); //setc the color of the line in the radar chart
    avgRateData.name("Average Rating");
    avgRateData.markers()
        .enabled(true)
        .type(MarkerType.CIRCLE)
        .fill("#f442e5")
        .size(4d);

    radar.tooltip().format("Value: {%Value}");

    anyChartView.setChart(radar);
    return view;
  }

  // This shows how the data should look like and sets the values of the data
  class CustomDataEntry extends ValueDataEntry {
    public CustomDataEntry(String x, Number value) {
      super(x, value);
      //setValue("value2", value2);
      //setValue("value3", value3);
    }
  }

}

