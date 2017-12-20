package com.bss.arrahmanlyrics.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bss.arrahmanlyrics.MainActivity;
import com.bss.arrahmanlyrics.R;
import com.bss.arrahmanlyrics.mysqlConnection.models.albums;
import com.bss.arrahmanlyrics.mysqlConnection.models.songs;
import com.bss.arrahmanlyrics.utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ExpandableListAdapterMysql extends BaseExpandableListAdapter {

	private Context _context;
	private List<albums> _listDataHeader; // header titles
	MainActivity activity;
	// child data in format of header title, child title
	private HashMap<String, List<songs>> _listDataChild;

	public ExpandableListAdapterMysql(Context context, List<albums> listDataHeader,
                                      HashMap<String, List<songs>> listChildData, MainActivity activity) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
		this.activity = activity;
	}

	@Override
	public songs getChild(int groupPosition, int childPosititon) {
		return this._listDataHeader.get(groupPosition).getSonglist().get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
	                         boolean isLastChild, View convertView, ViewGroup parent) {

		songs song = getChild(groupPosition, childPosition);
		Log.i(TAG, "getChildView: " + String.valueOf(song));

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.album_song_list_view, null);
		}
		TextView trackNo = (TextView) convertView.findViewById(R.id.trackNo);
		TextView lyricist = (TextView) convertView.findViewById(R.id.Songlyricist);
		TextView songtitle = (TextView) convertView.findViewById(R.id.Songtitle);
		trackNo.setText(String.valueOf(song.getTrack_no()));
		lyricist.setText(Helper.FirstLetterCaps(song.getLyricist()));
		songtitle.setText(Helper.FirstLetterCaps(song.getSong_title()));
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		Log.i(TAG, "getChildrenCount: " + _listDataChild);

		return this._listDataHeader.get(groupPosition).getSonglist().size();

	}

	@Override
	public albums getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
	                         View convertView, ViewGroup parent) {
		albums album = getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.album_view, null);
		}

		TextView title = (TextView) convertView.findViewById(R.id.Title);
		TextView count = (TextView) convertView.findViewById(R.id.TotalSongs);
		ImageView thumbnail = (ImageView) convertView.findViewById(R.id.albumimg);
		title.setText(Helper.FirstLetterCaps(album.getAlbum_name()));
		count.setText(album.getSonglist().size() + " songs");
		//Glide.with(context).load(album.getImageString()).into(holder.thumbnail);
		thumbnail.setImageBitmap(activity.getImageBitmap(album.getAlbum_name()));

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	/*public void setFilter(List<albumModel> list,HashMap<String,List<albumsongs>> map){
		this._listDataHeader.clear();
		this._listDataHeader = list;
		this._listDataChild.clear();
		this._listDataChild = map;
		notifyDataSetChanged();

	}*/

	public void filterData(List<albums> models,HashMap<String,List<songs>> map, String query) {

		query = query.toLowerCase().trim();

		List<albums> dummy = new ArrayList<>();
		HashMap<String, List<songs>> dummy2 = new HashMap<>();

		for (albums model : models) {

			String name = model.getAlbum_name().toLowerCase();
			String songname = "";
			String lyrics="";
			String year="";
			List<songs> songs = map.get(model.getAlbum_name());
			for(songs s : songs){
				songname = s.getSong_title().toLowerCase();
				lyrics = s.getLyricist().toLowerCase();
				year = String.valueOf(model.getYear());
				if(name.contains(query)||songname.contains(query)||lyrics.contains(query)||year.contains(query)){
					if(!dummy2.containsKey(model.getAlbum_name())){
						dummy.add(model);
						dummy2.put(model.getAlbum_name(), model.getSonglist());
					}

				}
			}



		}
		if(dummy.size()>0){
			_listDataHeader = new ArrayList<>();
			_listDataChild = new HashMap<>();
			_listDataHeader.addAll(dummy);
			_listDataChild.putAll(dummy2);
		}
		notifyDataSetChanged();
	}


	public void setall(List<albums> models,HashMap<String,List<songs>> map){
		if(models !=null && map != null) {
			_listDataHeader = new ArrayList<>();
			_listDataChild = new HashMap<>();
			_listDataHeader.addAll(models);
			_listDataChild.putAll(map);
			notifyDataSetChanged();
		}

	}
	public HashMap<String,List<songs>> get_listDataChild(){
		return _listDataChild;
	}
	public List<albums> get_listDataHeader(){
		return _listDataHeader;
	}
}

