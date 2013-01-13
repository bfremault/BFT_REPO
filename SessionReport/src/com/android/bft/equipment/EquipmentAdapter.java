package com.android.bft.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.bft.R;
import com.android.bft.R.id;
import com.android.bft.R.layout;
import com.android.bft.data.Equipment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TableRow;

public class EquipmentAdapter extends SimpleAdapter {

	private LayoutInflater	mInflater;
	
	private Context mContext;


	public EquipmentAdapter (Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to)
	{
		super (context, data, resource, from, to);
		mInflater = LayoutInflater.from (context);

	}
	
//	private List<Equipment> mItems = new ArrayList<Equipment>();
//
//	public EquipementAdapter(Context context, ArrayList<Equipment> items) {
//	     mContext = context;
//	     mItems = items;
//	     mInflater = LayoutInflater.from (context);
//
//	}
	
//	public int getCount() {
//		return mItems.size();
//	}

	public Object getItem(int position) {
		return super.getItem(position);
	}

//	public long getItemId(int position) {
//		return position;
//	}

	public View getView(int position, View convertView, ViewGroup parent) {
	//	ContactEntryView btv;
	
		if (convertView == null) {
	
			
			convertView = mInflater.inflate (R.layout.equipment_item, null);
			TableRow tr = (TableRow) convertView.findViewById (R.id.tableRow1);
			ImageButton bt = (ImageButton) convertView.findViewById (R.id.button1);
			tr.setTag (position);
			bt.setTag (position);
			
		}
	    return  super.getView (position, convertView, parent);
	}

}
