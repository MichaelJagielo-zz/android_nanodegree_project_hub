package com.inspirethis.mike.myappportfolio;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

/**
 * A placeholder fragment containing a simple view.
 */
public class SpotifyStreamerActivityFragment extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private ArrayAdapter<String> mMusicListAdapter;
    SearchView searchView;
    SearchManager searchManager;

    public SpotifyStreamerActivityFragment() {
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("", "performing search: " + query);
        performSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("", "text changing: " + newText);
        performSearch(newText);
        return false;
    }

    private void performSearch(String search) {
        FetchArtistTask task = new FetchArtistTask();
        Log.d("", "performing search: " + search);
        task.execute(search);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] data = {};
        List<String> musicList = new ArrayList<>(Arrays.asList(data));

        // TODO: change this to only populate once data received.
        // Create ArrayAdapter, feed dummy data, populate ListView
        mMusicListAdapter =
                new ArrayAdapter<>(
                        getActivity(), // The current context (this activity)
                        R.layout.music_item_list, // The name of the layout ID.
                        R.id.artist, // The ID of the textview to populate.
                        musicList);

        View rootView = inflater.inflate(R.layout.fragment_artist_search, container, false);

        searchView = (SearchView) rootView.findViewById(R.id.search);
        searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity()
                .getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("onQueryTextChange: ", newText);
                performSearch(newText);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mMusicListAdapter.getFilter().filter("");
                return true;
            }
        });

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

    @Override
    public boolean onClose() {
        return false;
    }

    public class FetchArtistTask extends AsyncTask<String, Void, List<Artist>> {
        private final String LOG_TAG = FetchArtistTask.class.getSimpleName();

        @Override
        protected List<Artist> doInBackground(String... params) {

            if (params.length == 0 || params[0].equals("")) {
                return null;
            }

            SpotifyApi api = new SpotifyApi();
            SpotifyService spotify = api.getService();
            ArtistsPager paginator = spotify.searchArtists(params[0]);
            return paginator.artists.items;
        }

        @Override
        protected void onPostExecute(List<Artist> result) {
            if (result != null) {
                mMusicListAdapter.clear();
                for (Artist a : result)
                    mMusicListAdapter.add(a.name);
            }
        }
    }
}
