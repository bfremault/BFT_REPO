package com.bft.sessions;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;

public class ListSpotAdapter extends SimpleAdapter {

    private List<? extends Map<String, String>> mData;
    private long[] mCurrentIds;

    public ListSpotAdapter(Context context,
            List<? extends Map<String, String>> data, int resource,
            String[] from, int[] to) {
        super(context, data, resource, from, to);
        mData = data;
    }

    @Override
    public long getItemId(int position) {       
        // this will be used to get the id provided to the onItemClick callback
        return mCurrentIds[position];
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public void setRealIds(long[] realIds) {
        mCurrentIds = realIds;
    }

}
