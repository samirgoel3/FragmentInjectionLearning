package com.apporio.ERetailing;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.shizhefei.view.multitype.ItemBinderFactory;
import com.shizhefei.view.multitype.MultiTypeAdapter;
import com.shizhefei.view.multitype.MultiTypeView;
import com.shizhefei.view.multitype.provider.FragmentData;

import java.util.ArrayList;
import java.util.List;

//
//        / **
//        * Demonstrated here with the recovery state of MultiTypeStateAdapter, onSaveInstanceState state of preservation , onCreate (Bundle savedInstanceState) recovery state <br/>
//        * Fragment recovery mechanisms can view EditLazyFragment <br/>
//        * /
public class MainActivity extends AppCompatActivity {
    private MultiTypeAdapter<Parcelable> multiTypeStateAdapter;
    private int page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MultiTypeView multiTypeView = (MultiTypeView) findViewById(R.id.recyclerView);


        // Constructor with FragmentManager , the default help you add support Fragment data FragmentHolderProvider
        // This means that you can put on the adapter using Fragment , Fragment here only the first slide until the position corresponding method onCreateView
        ItemBinderFactory factory = new ItemBinderFactory(getSupportFragmentManager());
        multiTypeStateAdapter = new MultiTypeAdapter<>(factory);

        // Recover the data from the list in savedInstanceState (onSaveInstanceState () method to save data )
        if (MultiTypeAdapter.restoreState("data", multiTypeStateAdapter, savedInstanceState)) {
            page = savedInstanceState.getInt("page");
        } else {
            //Reload the list data

            multiTypeStateAdapter.notifyDataChanged(loadData(0), false);
        }
        multiTypeView.setAdapter(multiTypeStateAdapter);





    }




























    private List<Parcelable> loadData(int page) {
        List<Parcelable> data = new ArrayList<>();

        data.add(new FragmentData(HotelFragment.class, "EditFragment" + page));

        // Here demo , you can add any Serializable, as well as save data , and automatically correspond to the data type ItemViewProvider
        // Basic types are type Serializable
        //        data.add(SerializableData.valueOf("HHHHH_1"));

        //data.add(SerializableData.valueOf(1));// not registered int the ItemViewProvider, do not add the demonstration
        // Serializable data list into a list of SerializableData
//        List<String> texts = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            texts.add(String.valueOf(i));
//        }
//        data.addAll(SerializableData.valueOfList(texts));

        data.add(new FragmentData(HotelFragment.class, "ShopFragment" + page));

//        // FragmentData Note tag to be unique , to locate and recover the corresponding Fragment Fragment.
//        data.add(new FragmentData(HotelFragment.class, "HotelFragment" + page));
//        data.add(new FragmentData(HotelFragment.class, "HotelFragment" + page));
//        data.add(new FragmentData(HotelFragment.class, "HotelFragment" + page));
//        data.add(new FragmentData(RecommendFragment.class, "RecommendFragment" + page));
        data.add(new FragmentData(HotelFragment.class, "FoodFragment" + page));


        FragmentData fragmentData = new FragmentData(HotelFragment.class, "CultureFragment" + page);

        // Can get Fragment has been created in this way , but to get here is null, because not created
        // So when created ? In RecyclerView Fragment corresponding slide position will be created .
        // You can also (). Get (position) to obtain the corresponding FragmentData by multiTypeStateAdapter.getData, by FragmentData get the corresponding Fragment.
        Fragment fragment = fragmentData.getFragment();

        // Why FragmentData way to add , because it implements Parcelable can be saved as a data
        data.add(fragmentData);

        this.page = page;
        return data;
    }
















    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("page", page);
        MultiTypeAdapter.saveState("data", multiTypeStateAdapter, outState);
    }

}
