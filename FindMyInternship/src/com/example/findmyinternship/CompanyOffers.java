package com.example.findmyinternship;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CompanyOffers extends Fragment{
	public  CompanyOffers() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.company_offers, container, false);
        
        final LinearLayout mLinearLayout =(LinearLayout)inflater.inflate(R.layout.company_offers,container, false);
        
        ListView l = (ListView)mLinearLayout.findViewById(R.id.listView1);
        
        String[] values = new String[] { "Offre n°1", "Offre n°2", "Offre n°3" , "Offre n°4", "Offre n°5", "Offre n°6", "Offre n°7", "Offre n°8", "Offre n°9", "Offre n°10", "Offre n°11", "Offre n°12", "Offre n°13", "Offre n°14", "Offre n°15"};
        
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
          list.add(values[i]);
        }
        
        l.setAdapter(new ArrayAdapter<String>(mLinearLayout.getContext(), android.R.layout.simple_list_item_1,values));

            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

              @SuppressLint("NewApi")
			@Override
              public void onItemClick(AdapterView<?> parent, final View view,int position, long id) 
              {
                
              }

            });
            
        ImageButton img = (ImageButton)mLinearLayout.findViewById(R.id.myimageButton1);
        
        img.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) 
        	  {
        		  //Fragment fragment=new CompanyOffersDeposit();
        		  //FragmentManager fragmentManager = getFragmentManager();
        	      //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        	      
        	      Intent intent = new Intent(mLinearLayout.getContext(), CompanyOffersDeposit.class);
          		  startActivity(intent);
        	  }
        	});
                
        return mLinearLayout;
    }
}

class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
        List<String> objects) {
      super(context, textViewResourceId, objects);
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
    }

    @Override
    public long getItemId(int position) {
      String item = getItem(position);
      return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }

  }

