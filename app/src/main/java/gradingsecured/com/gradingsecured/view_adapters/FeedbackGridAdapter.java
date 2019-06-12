package gradingsecured.com.gradingsecured.view_adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import gradingsecured.com.gradingsecured.R;
import java.util.ArrayList;

public class FeedbackGridAdapter extends BaseAdapter {
  private Context context;
  private ArrayList<String> categories;

  public FeedbackGridAdapter(Context context, ArrayList<String> categories) {
    this.context = context;
    this.categories = categories;
  }

  @Override
  public int getCount() {
    return categories.size();
  }

  @Override
  public Object getItem(int i) {
    return null;
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(final int i, View view, ViewGroup viewGroup) {

    String category = categories.get(i);
    char initial = category.charAt(0);

    CardView cardView;
    LinearLayout linearLayout;
    TextView titleTextView;
    TextView buttonTextView = new TextView(context);
    cardView = new CardView(context);
    cardView.setLayoutParams(new GridView.LayoutParams(260, 260));
    cardView.setCardBackgroundColor(View.INVISIBLE);
    cardView.setCardElevation(0);
    cardView.setMaxCardElevation(0);
    cardView.setPreventCornerOverlap(true);
    linearLayout = new LinearLayout(context);
    linearLayout.setLayoutParams(new GridView.LayoutParams(260, 260));
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    linearLayout.setGravity(Gravity.CENTER);
    linearLayout.setPadding(10, 10, 10, 10);
    buttonTextView.setLayoutParams(new GridView.LayoutParams(150, 150));
    buttonTextView.setGravity(Gravity.CENTER);
    buttonTextView.setTextSize(25);
    buttonTextView.setTextColor(Color.WHITE);
    buttonTextView.setTypeface(null, Typeface.BOLD);
    titleTextView = new TextView(context);
    titleTextView.setLayoutParams(new GridView.LayoutParams(260, 80));
    titleTextView.setGravity(Gravity.CENTER);
    titleTextView.setText(category);
    titleTextView.setTextSize(18);
    titleTextView.setTypeface(null, Typeface.BOLD);

    switch (category) {
      case "Content":
        buttonTextView.setBackgroundResource(R.drawable.rounded_textview1);
        buttonTextView.setText("C");

        break;
      case "Language":
        buttonTextView.setBackgroundResource(R.drawable.rounded_textview2);
        buttonTextView.setText("L");
        break;
      case "Visual":
        buttonTextView.setBackgroundResource(R.drawable.rounded_textview3);
        buttonTextView.setText("V");
        break;
      case "Clarity":
        buttonTextView.setBackgroundResource(R.drawable.rounded_textview4);
        buttonTextView.setText("CL");
        break;
      case "Brevity":
        buttonTextView.setBackgroundResource(R.drawable.rounded_textview5);
        buttonTextView.setText("B");
        break;
    }

    linearLayout.addView(buttonTextView);
    linearLayout.addView(titleTextView);
    cardView.addView(linearLayout);

    return cardView;
  }
}
