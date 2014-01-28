package com.example.findmyinternship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import Model.Offer;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class StudentAnnonces extends Fragment{
	
	int StudID;
	
	public StudentAnnonces() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.annonces_layout, container, false);
        
        final FrameLayout mLinearLayout =(FrameLayout)inflater.inflate(R.layout.annonces_layout,container, false);
        
        Bundle b= this.getArguments();
        
        StudID = b.getInt("StudID");
        
        ListView l = (ListView)mLinearLayout.findViewById(R.id.listViewresult);
        
        //On récupère les offres en BDD
        MainActivity.getOffBdd().open();
        
        
        if(MainActivity.getOffBdd().getAllOffers() != null)
        {    	
        	final Offer[] myOffers = MainActivity.getOffBdd().getAllOffers();
	        
	        String[] values = new String[myOffers.length];
	        
	        for(int i=0;i<myOffers.length;i++)
	        {
	        	//System.out.println("valll == "+myOffers[i].getTitle());
	        	values[i]=myOffers[i].getTitle();
	        	
	        }

	       
        	
        	//Création de la ArrayList qui nous permettra de remplire la listView
            ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
     
            //On déclare la HashMap qui contiendra les informations pour un item
            HashMap<String, String> map;
            
            for(int i=0;i<values.length;i++)
            {
            	 map = new HashMap<String, String>();
            	 map.put("titre",values[i]);            	 
            	 map.put("descr",myOffers[i].getProfil());
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
	            	  b.putInt("StudID", StudID);
	            	  b.putString("company", temp.getCompany());
	            	  b.putString("titree", temp.getTitle());
	            	  b.putString("desc", temp.getDescription());
	            	  b.putString("skills", temp.getCompetences());
	            	  b.putString("profile", temp.getProfil());
	            	  b.putString("duration", temp.getDuration());
	            	  b.putString("salary", temp.getSalary());
	            	  b.putInt("starvisibility", 1);
    				  intent.putExtras(b);

    				  startActivity(intent);
    				  
    				  
	            	  
	            }
	
	            });
	            
        
        
    }
        
        return mLinearLayout;

}
}
