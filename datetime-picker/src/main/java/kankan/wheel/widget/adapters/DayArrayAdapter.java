package kankan.wheel.widget.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kankan.wheel.widget.DateTimeWheel;
import kankan.wheel.widget.R;

/**
 * Day adapter
 * 
 */
public class DayArrayAdapter extends AbstractWheelTextAdapter {
	// Count of days to be shown


	// Calendar
	Calendar calendar;

	/**
	 * Constructor
	 */
	public DayArrayAdapter(Context context, Calendar calendar) {
		super(context, R.layout.time2_day, NO_RESOURCE);
		this.calendar = calendar;
		setItemTextResource(R.id.time2_monthday);
	}

	@Override
	public View getItem(int index, View cachedView, ViewGroup parent) {

		View view = super.getItem(index, cachedView, parent);

		TextView monthday = (TextView) view.findViewById(R.id.time2_monthday);
		DateFormat format = new SimpleDateFormat("yyyy MMM dd", Locale.ENGLISH);
		monthday.setText(format.format(DateTimeWheel.strings.get(index).getTime()));
		monthday.setTextColor(0xFF111111);

		return view;
	}

	@Override
	public int getItemsCount() {
		return DateTimeWheel.strings.size();
	}

	@Override
	protected CharSequence getItemText(int index) {
		return "";
	}
}
