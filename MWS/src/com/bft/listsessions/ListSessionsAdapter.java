package com.bft.listsessions;

import java.util.List;
import java.util.Map;

import com.bft.mws.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TableRow;

public class ListSessionsAdapter extends SimpleAdapter {

	private LayoutInflater	mInflater;
	
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
			TableRow tr = (TableRow) convertView.findViewById (R.id.tableRow1);
			//ImageButton bt = (ImageButton) convertView.findViewById (R.id.button1);
			tr.setTag (position);
			//bt.setTag (position);
			
		}
	    return  super.getView (position, convertView, parent);
	}

	
}
