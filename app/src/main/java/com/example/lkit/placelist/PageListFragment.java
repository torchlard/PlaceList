package com.example.lkit.placelist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PageListFragment extends Fragment {


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

    Bundle args = getArguments();
    int index = args.getInt("index", 0);
//    View view;
//    if (index != 2){
//      view = inflater.inflate(R.layout.fragment_page, container, false);
//    } else {
//      view = inflater.inflate(R.layout.fragment_page2, container, false);
//    }
    RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_wrapper, container, false);
    setupRecyclerView(rv, index);

    return rv;
  }

  private void setupRecyclerView(RecyclerView rv, int tabIndex){
    rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
    rv.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(), getSeqNum(tabIndex)));
  }

  private List<String> getSeqNum(int tabIndex){
    int amount = 50;
    ArrayList<String> list = new ArrayList<>(amount);
    for(int i= (tabIndex-1)*amount; i<tabIndex*amount; i++) {
      list.add(String.valueOf(i));
    }
    return list;
  }

  private static class SimpleStringRecyclerViewAdapter extends
      RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>{

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<String> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
      private String mBoundedString;
      private final View mView;
      private final TextView tv1;
      private final TextView tv2;

      private ViewHolder(View view){
        super(view);
        mView = view;
        tv1 = view.findViewById(R.id.text1);
        tv2 = view.findViewById(R.id.text2);
      }

      @Override
      public String toString(){
        return super.toString()+" '"+tv1.getText();
      }
    }

    private SimpleStringRecyclerViewAdapter(Context context,List<String> items){
      context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
      mBackground = mTypedValue.resourceId;
      mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
      view.setBackgroundResource(mBackground);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
      holder.mBoundedString = mValues.get(position);
      holder.tv1.setText(mValues.get(position));
      holder.tv2.setText(mValues.get(position)+"noo");
//      holder.mView.setOnClickListener(v -> {
//        Context context = v.getContext();
//      })
    }

    @Override
    public int getItemCount(){
      return mValues.size();
    }

  }

}

