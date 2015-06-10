package com.inspirethis.mike.myappportfolio;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class SpotifyStreamerActivityFragment extends Fragment {

    private ArrayAdapter<String> mMusicListAdapter;

    public SpotifyStreamerActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_spotify_streamer, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_refresh) {
//
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Create some dummy data for the ListView.
        String[] data = {
                "Jimi Hendrix",
                "Lawrence Welck",
                "Frank Sinatra",
                "Arlo Guthrie"
        };
        List<String> musicList = new ArrayList<String>(Arrays.asList(data));

        // Create ArrayAdapter, feed dummy data, populate ListView
        mMusicListAdapter =
                new ArrayAdapter<>(
                        getActivity(), // The current context (this activity)
                        R.layout.music_item_list, // The name of the layout ID.
                        R.id.artist, // The ID of the textview to populate.
                        musicList);

        View rootView = inflater.inflate(R.layout.fragment_spotify_streamer, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_music);
        listView.setAdapter(mMusicListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String musicSelection = mMusicListAdapter.getItem(position);
                Toast.makeText(getActivity(), musicSelection, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
    //return inflater.inflate(R.layout.fragment_spotify_streamer, container, false);

}
