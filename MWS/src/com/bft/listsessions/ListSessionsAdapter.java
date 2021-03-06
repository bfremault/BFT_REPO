package com.bft.listsessions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bft.mws.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;

public class ListSessionsAdapter extends SimpleAdapter {

	private LayoutInflater	mInflater;
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM yyyy");

	public ListSessionsAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		mInflater = LayoutInflater.from (context);
	}
	public Object getItem(int position) {
		return super.getItem(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	
		if (convertView == null) {
			convertView = mInflater.inflate (R.layout.session_detail, null);
			convertView.setTag (position);
			}
		else{
			convertView.getTag ();
			}

		TableRow tr1 = (TableRow) convertView.findViewById(R.id.tableRow0);
		TextView separator = (TextView) convertView.findViewById(R.id.separator);
		Map<String, String> firstItem = (Map<String, String>) getItem(0);
		String firstMonth = firstItem.get("date").substring(3);
		Date date;
		try {
			date = sdf.parse(firstMonth);
			separator.setText(sdf1.format(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		//tr1.setVisibility(View.GONE);

			if(position>0){
				Map<String, String> currentItem = (Map<String, String>) getItem(position);
				String currentMonth = currentItem.get("date").substring(3);
				Map<String, String> nextItem = (Map<String, String>) getItem(position-1);
				String nextMonth = nextItem.get("date").substring(3);
	
				if(currentMonth.equalsIgnoreCase(nextMonth)){
					tr1.setVisibility(View.GONE);
				}
				else{
					tr1.setVisibility(View.VISIBLE);
					try {
						Date d = sdf.parse(currentMonth);
						separator.setText(sdf1.format(d));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}	
			
	    return  super.getView (position, convertView, parent);
	}
	
}
