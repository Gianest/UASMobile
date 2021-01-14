package com.example.uasmobile.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobile.R;
import com.example.uasmobile.dbHelper;
import com.example.uasmobile.detailsAct;
import com.example.uasmobile.model.Response;
import com.example.uasmobile.ui.Team.Team;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private List<Response> list ;
    private RecyclerView.Adapter adapterx;
    private RecyclerView recyclerView;
    private List<Object> viewItems = new ArrayList<>();
    private dbHelper db;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.title_team);
        db = new dbHelper(getContext());
        viewItems.addAll(db.getAllRecord());
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        recyclerView = view.findViewById(R.id.rv_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FavoriteAdapter adapterx = new FavoriteAdapter(db.getAllRecord());
        recyclerView.setAdapter(adapterx);
        adapterx.setOnItemClickCallback(new FavoriteAdapter.OnItemClickCallback() {
            public void onItemClicked(Object objek) {
                showSelectedProduct((Team) objek);
            }
        });
        return view;
    }
    private void showSelectedProduct(Team team) {
        Intent intent = new Intent(getContext(), detailsAct.class);
        Toast.makeText( getContext(),team.getTim() , Toast.LENGTH_SHORT).show();
        intent.putExtra("photo", team.getImagev());
        intent.putExtra("nama",team.getTim());
        intent.putExtra("detail",team.getDesc());
        startActivity(intent);
    }

}