package com.android.bft;

	import java.util.List;
	import java.util.Map;

import com.android.bft.R;

	import android.content.Context;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.CheckBox;
	import android.widget.SimpleAdapter;
import android.widget.TableRow;

	public class SessionListAdapter extends SimpleAdapter
	{
		private LayoutInflater	mInflater;

		public SessionListAdapter (Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to)
		{
			super (context, data, resource, from, to);
			mInflater = LayoutInflater.from (context);

		}
		
		@Override
		public Object getItem (int position)
		{
			return super.getItem (position);
		}

		@Override
		public View getView (int position, View convertView, ViewGroup parent)
		{
			//Ce test permet de ne pas reconstruire la vue si elle est déjà créée
			if (convertView == null)
			{
				// On récupère les éléments de notre vue
				convertView = mInflater.inflate (R.layout.list_detail, null);
				// On récupère notre checkBox
				TableRow tr1 = (TableRow) convertView.findViewById (R.id.row1);
				TableRow tr2 = (TableRow) convertView.findViewById (R.id.row2);
				CheckBox cb = (CheckBox) convertView.findViewById (R.id.check);
				// On lui affecte un tag comportant la position de l'item afin de
				// pouvoir le récupérer au clic de la checkbox
				tr1.setTag (position);
				tr2.setTag (position);
				cb.setTag (position);
			}
			return super.getView (position, convertView, parent);
		}
		

	}

