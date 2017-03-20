package customs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.givingback.R;

import java.util.ArrayList;



public class ViewAllSlotsCustomAdapter extends ArrayAdapter<ViewAllSlotsListClass> {

    private static final String LOG_TAG = ViewAllSlotsCustomAdapter.class.getSimpleName();


    public ViewAllSlotsCustomAdapter(Activity context, ArrayList<ViewAllSlotsListClass> expenseList) {
        super(context, 0, expenseList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.view_all_slots_list_item_layout, parent, false);
        }

        ViewAllSlotsListClass current = getItem(position);


        TextView date = (TextView) listItemView.findViewById(R.id.view_all_slots_item_date);
        date.setText("Date: "+current.getDateOfSlot());

        TextView fromTime = (TextView) listItemView.findViewById(R.id.view_all_slots_item_from_time);
        fromTime.setText("From: "+current.getFromTime());

        TextView toTime = (TextView) listItemView.findViewById(R.id.view_all_slots_item_to_time);
        toTime.setText("To: "+current.getToTime());

        TextView location = (TextView) listItemView.findViewById(R.id.view_all_slots_item_location);
        location.setText("Around"+current.getAroundPlace());

        TextView status = (TextView) listItemView.findViewById(R.id.view_all_slots_item_status);
        status.setText(current.getStatus());


        return listItemView;
    }

}
