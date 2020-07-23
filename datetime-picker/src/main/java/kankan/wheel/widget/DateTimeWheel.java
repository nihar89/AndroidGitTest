package kankan.wheel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import kankan.wheel.widget.adapters.DayArrayAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

import static kankan.wheel.widget.R.id.day;

public class DateTimeWheel extends LinearLayout {
	private Calendar calendar = Calendar.getInstance();

	public static ArrayList<Calendar> strings;
	private OnTimeChangedListener timeChangedListener = null;
	private DayArrayAdapter dayArrayAdapter;

	private final int daysCount = 364;
	
	public DateTimeWheel(Context context) {
		this(context, null);
	}

	public DateTimeWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(VERTICAL);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.date_time_wheel, this, true);
		
		final WheelView hours = (WheelView) findViewById(R.id.hour);
		NumericWheelAdapter hourAdapter = new NumericWheelAdapter(context, 0, 23, "%2d");
		hourAdapter.setItemResource(R.layout.wheel_text_item);
		hourAdapter.setItemTextResource(R.id.text);
		hours.setViewAdapter(hourAdapter);
		hours.setCyclic(true);

		final WheelView mins = (WheelView) findViewById(R.id.mins);
		NumericWheelAdapter minAdapter = new NumericWheelAdapter(context, 0, 59, "%02d");
		minAdapter.setItemResource(R.layout.wheel_text_item);
		minAdapter.setItemTextResource(R.id.text);
		mins.setViewAdapter(minAdapter);
		mins.setCyclic(true);
		
		mins.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				calendar.set(Calendar.MINUTE, newValue);
				fireTimeChanged(calendar.getTimeInMillis());
			}
		});

		hours.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				calendar.set(Calendar.HOUR, newValue);
				fireTimeChanged(calendar.getTimeInMillis());
			}
		});

		// set current time
		hours.setCurrentItem(calendar.get(Calendar.HOUR));
		mins.setCurrentItem(calendar.get(Calendar.MINUTE));

		DateTimeWheel.strings = new ArrayList<>();

		for (int i = 0; i < daysCount; i++) {
			int day = -daysCount / 2 + i;
			Calendar newCalendar = (Calendar) calendar.clone();
			newCalendar.roll(Calendar.DAY_OF_YEAR, day);
			DateTimeWheel.strings.add(newCalendar);
		}

		final WheelView dayView = (WheelView) findViewById(day);

		class DateCompare implements Comparator<Calendar> {
			public int compare(Calendar one, Calendar two) {
				return one.compareTo(two);
			}
		}
		DateCompare dateCompare = new DateCompare();
		Collections.sort(strings,dateCompare);
		dayArrayAdapter = new DayArrayAdapter(context, calendar);
		dayView.setViewAdapter(dayArrayAdapter);
		//dayView.setCurrentItem(calendar.get(Calendar.DAY_OF_YEAR));
		dayView.setCyclic(true);
		dayView.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				calendar.set(Calendar.MONTH,strings.get(newValue).get(Calendar.MONTH));
				calendar.set(Calendar.YEAR,strings.get(newValue).get(Calendar.YEAR));
				calendar.set(Calendar.DATE,strings.get(newValue).get(Calendar.DATE));
				//calendar.setTime(dateTime(strings.get(newValue).getTime(),calendar.getTime()));
				fireTimeChanged(calendar.getTimeInMillis());
			}
		});
	}

	/*public Date dateTime(Date date, Date time) {
		return new Date(
				date.getYear(), date.getMonth(), date.getDay(),
				time.getHours(), time.getMinutes(), time.getSeconds()
		);
	}*/

	public void setCalenderTime(Calendar calenderTime)
	{
		calendar = calenderTime;
		final WheelView dayView = (WheelView) findViewById(day);
		dayView.setCurrentItem(calendar.get(Calendar.DAY_OF_YEAR)-1);

		final WheelView hours = (WheelView) findViewById(R.id.hour);
		hours.setCurrentItem(calendar.get(Calendar.HOUR));

		final WheelView mins = (WheelView) findViewById(R.id.mins);
		mins.setCurrentItem(calendar.get(Calendar.MINUTE));
	}

	private void fireTimeChanged(long timeInMillis) {
		if (timeChangedListener != null) {
			timeChangedListener.onTimeChanged(timeInMillis);
		}
	}
	
	public void setOnTimeChangedListener(OnTimeChangedListener timeChangedListener) {
		this.timeChangedListener = timeChangedListener;
	}

	public interface OnTimeChangedListener {
		void onTimeChanged(long time);
	}
}