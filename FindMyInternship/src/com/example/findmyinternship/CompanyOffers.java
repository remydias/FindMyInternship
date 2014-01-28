package com.example.findmyinternship;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import Model.Offer;
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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class CompanyOffers extends Fragment{
	public  CompanyOffers() {
        // Empty constructor required for fragment subclasses
		
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {        
        final FrameLayout mLinearLayout =(FrameLayout)inflater.inflate(R.layout.company_offers,container, false);
        
        Bundle b= this.getArguments();
        
        // if(company_name.length()<30)
        final String company_name = b.getString("name");
        
        ListView l = (ListView)mLinearLayout.findViewById(R.id.listViewcompoff);
        
        //On récupère les offres en BDD
        MainActivity.getOffBdd().open();
        
        
        if(MainActivity.getOffBdd().getOfferByComp(company_name) != null)
        {    	
        	final Offer[] myOffers = MainActivity.getOffBdd().getOfferByComp(company_name);
	        
	        String[] values = new String[myOffers.length];
	        
	        for(int i=0;i<myOffers.length;i++)
	        {
	        	System.out.println("valll == "+myOffers[i].getTitle());
	        	values[i]=myOffers[i].getTitle();
	        	
	        }
        	Toast.makeText(this.getActivity(),values[0], Toast.LENGTH_LONG).show();

	        
	        ArrayList<String> list = new ArrayList<String>();
	        for (int i = 0; i < values.length; i++) 
	        {
	          list.add(values[i]);
	        }
	        
	        
	        
	        l.setAdapter(new ArrayAdapter<String>(mLinearLayout.getContext(), android.R.layout.simple_list_item_1,values));
	
	            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
				@Override
	              public void onItemClick(AdapterView<?> parent, final View view,int position, long id) 
	              {
	            	  Offer temp = myOffers[position];
	            	  
	            	  Intent intent = new Intent(getActivity(), ViewOffer.class);
	            	  Bundle b = new Bundle();
	            	  b.putString("company", temp.getCompany());
	            	  b.putString("titree", temp.getTitle());
	            	  b.putString("desc", temp.getDescription());
	            	  b.putString("skills", temp.getCompetences());
	            	  b.putString("profile", temp.getProfil());
	            	  b.putString("duration", temp.getDuration());
	            	  b.putString("salary", temp.getSalary());
    				  intent.putExtras(b);

    				  startActivity(intent);
    				  
    				  
	            	  
	            }
	
	            });
	            
        }
	            
	        ImageButton img = (ImageButton)mLinearLayout.findViewById(R.id.myimageButton1);
	        
	        img.setOnClickListener(new OnClickListener() {
				
	        	  @Override
	        	  public void onClick(View v) 
	        	  {
	        	      Intent intent = new Intent(mLinearLayout.getContext(), CompanyOffersDeposit.class);
	        	      Bundle extras = new Bundle();
					  extras.putString("name", company_name);
					  intent.putExtras(extras);
	        	      
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

