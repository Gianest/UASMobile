package com.example.uasmobile.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobile.R;
import com.example.uasmobile.detailsAct;
import com.example.uasmobile.detailsAct2;
import com.example.uasmobile.model.Response;
import com.example.uasmobile.ui.Team.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class HomeFragment extends Fragment {
    private List<Response> list ;
    private RecyclerView.Adapter adapterx;
    private RecyclerView recyclerView;
    private List<Object> viewItems = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.title_match);
        addItemsFromJSON();
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        recyclerView = view.findViewById(R.id.rv_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        HomeAdapter adapterx = new HomeAdapter(viewItems);
        recyclerView.setAdapter(adapterx);
        adapterx.setOnItemClickCallback(new HomeAdapter.OnItemClickCallback() {
            public void onItemClicked(Object objek) {
                showSelectedProduct((Team) objek);
            }
        });
        return view;
    }

    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONData();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String tim = itemObj.getString("strLeague");
                String desc = itemObj.getString("strDescriptionEN");
                String imagev = itemObj.getString("strBadge");

                Team teamx = new Team(tim, desc,imagev);
                viewItems.add(teamx);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONData() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.rv1);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
    private void showSelectedProduct(Team team) {
        Intent intent = new Intent(getContext(), detailsAct2.class);
        Toast.makeText( getContext(),team.getTim() , Toast.LENGTH_SHORT).show();
        intent.putExtra("photo", team.getImagev());
        intent.putExtra("nama",team.getTim());
        intent.putExtra("detail",team.getDesc());
        startActivity(intent);
    }

}