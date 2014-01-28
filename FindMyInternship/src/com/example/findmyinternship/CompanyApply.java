package com.example.findmyinternship;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Application;
import Model.Offer;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class CompanyApply extends Fragment{
	public  CompanyApply() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.company_apply, container, false);
        
        final ScrollView mLinearLayout =(ScrollView)inflater.inflate(R.layout.company_apply,container, false);
        
        Bundle b= this.getArguments();
        
        ListView l = (ListView)mLinearLayout.findViewById(R.id.listViewApplications);
        
        MainActivity.getAppBdd().open();
        
        final Application[] myApplications = MainActivity.getAppBdd().getApplicationByCompany(b.getString("name"));
       
        
        MainActivity.getAppBdd().close();
        
        if(myApplications != null)
        {
        	LayoutParams lp = (LayoutParams) l.getLayoutParams();
            lp.height = 100*(myApplications.length+1);
            l.setLayoutParams(lp);
            
        	Toast.makeText(getActivity(),"YO", Toast.LENGTH_LONG).show();
        	String[] values = new String[myApplications.length];
        	String[] values2 = new String[myApplications.length];
        	final Offer[] myOffers = new Offer[myApplications.length];
        	
        	MainActivity.getOffBdd().open();
	        
	        for(int i=0;i<myApplications.length;i++)
	        {
	        	//System.out.println("valll == "+myOffers[i].getTitle());
	        	Offer temp = MainActivity.getOffBdd().getOfferByID(myApplications[i].getID());
	        	myOffers[i]=temp;
	        	values[i]=temp.getTitle();
	        	values2[i]=temp.getProfil();
	        	
	        }
	        MainActivity.getOffBdd().close();
	       
        	
        	//Création de la ArrayList qui nous permettra de remplire la listView
            ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
     
            //On déclare la HashMap qui contiendra les informations pour un item
            HashMap<String, String> map;
            
            for(int i=0;i<values.length;i++)
            {
            	 map = new HashMap<String, String>();
            	 map.put("titre",values[i]);            	 
            	 map.put("descr",values2[i]);
            	// map.put("img", String.valueOf(R.drawable.star));
            	 listItem.add(map);
            }
     
	        
	        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
	        SimpleAdapter mSchedule = new SimpleAdapter (getActivity().getBaseContext(), listItem, R.layout.affichageitem,new String[] {/*"img", */"titre", "descr"}, new int[] {/*R.id.img,*/ R.id.titre, R.id.descr});
	        
	        
	        l.setAdapter(mSchedule);
	
	            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
				@Override
	              public void onItemClick(AdapterView<?> parent, final View view,int position, long id) 
	              {
	            	  Offer temp = myOffers[position];
	            	  
	            	  Intent intent = new Intent(getActivity(), ViewOffer.class);
	            	  Bundle b = new Bundle();
	            	  b.putInt("id", temp.getID());
	            	  b.putInt("StudID", myApplications[position].getStudID());
	            	  b.putString("company", temp.getCompany());
	            	  b.putString("titree", temp.getTitle());
	            	  b.putString("desc", temp.getDescription());
	            	  b.putString("skills", temp.getCompetences());
	            	  b.putString("profile", temp.getProfil());
	            	  b.putString("duration", temp.getDuration());
	            	  b.putString("salary", temp.getSalary());
	            	  b.putInt("starvisibility", 0);
    				  intent.putExtras(b);

    				  startActivity(intent);
    				  
    				  
	            	  
	            }
	
	            });
        	
        	
        	
        	
        }
        
        return mLinearLayout;
    }

}
