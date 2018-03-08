package iteso.com.sesin10_tabslists;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

import iteso.com.sesin10_tabslists.beans.ItemProduct;

/**
 * Created by hecto on 28/02/2018.
 */

public class FragmentTechnology extends android.support.v4.app.Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ItemProduct> myDataSet;

    public FragmentTechnology(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_technology_recycler_view);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        myDataSet = new ArrayList<ItemProduct>();
        myDataSet.add(new ItemProduct(
                getResources().getString(R.string.product_a),
                getResources().getString(R.string.store_a),
                getResources().getString(R.string.address_a),
                getResources().getString(R.string.phone_a),
                0,
                getResources().getString(R.string.description_a),
                0,
                0));
        myDataSet.add(new ItemProduct(getResources().getString(R.string.product_b),
                getResources().getString(R.string.store_b),
                getResources().getString(R.string.address_b),
                getResources().getString(R.string.phone_b),
                1,
                getResources().getString(R.string.description_b),
                1,
                1));
        myDataSet.add(new ItemProduct(getResources().getString(R.string.product_c),
                getResources().getString(R.string.store_c),
                getResources().getString(R.string.address_c),
                getResources().getString(R.string.phone_c),
                2,
                getResources().getString(R.string.description_c),
                2,
                2));

        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ItemProduct itemProduct = data.getParcelableExtra("ITEM");
        Iterator<ItemProduct> iterator = myDataSet.iterator();
        int position = 0;
        while(iterator.hasNext()){
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                myDataSet.set(position, itemProduct);
                break;
            }
            position++;
        }
        mAdapter.notifyDataSetChanged();
    }
}
