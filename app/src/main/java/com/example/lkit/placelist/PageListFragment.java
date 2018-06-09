package com.example.lkit.placelist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageListFragment extends Fragment {


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    Bundle args = getArguments();
    int index = args.getInt("index", 0);

    View view = inflater.inflate(R.layout.fragment_page, container, false);
    setText(index);
    return view;
  }

  private void setText(int text){
    TextView tv = getActivity().findViewById(R.id.frag_page);
    tv.setText(text);
  }


}
