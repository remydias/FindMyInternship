package com.example.findmyinternship;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import Model.Offer;
import Model.OfferFav;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class StudentSearch extends Fragment{
	
	Bundle b;
	int StudID;
	int[] indice1;
	int[] indice2;
	
	public StudentSearch() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.student_search_layout, container, false);
        
        b= this.getArguments();
        StudID = b.getInt("StudID");
        
        final FrameLayout mLinearLayout =(FrameLayout)inflater.inflate(R.layout.student_search_layout,container, false);
        
        ListView l = (ListView)mLinearLayout.findViewById(R.id.listViewsearch);
      //On récupère les offres en BDD
        MainActivity.getOffFavBdd().open();
        
        
        if(MainActivity.getOffFavBdd().getOffersFavByStudent(StudID) != null)
        {    	
        	final OfferFav[] myOffers = MainActivity.getOffFavBdd().getOffersFavByStudent(StudID);
	        
	        int[] IDS = new int[myOffers.length];
	        
	        //Récupération des ID des offres favories
	        
	        for(int i=0;i<myOffers.length;i++)
	        {
	        	IDS[i]=myOffers[i].getOfferID();
	        }
	        
	        MainActivity.getOffFavBdd().close();
	        
	        final ArrayList<Offer> ll = new ArrayList<Offer>();
	        
	        //Récupération des offres
	        
	        MainActivity.getOffBdd().open();
	        
	        final Offer[] myOffers2 = MainActivity.getOffBdd().getAllOffers();
	        
	        for(int i=0;i<IDS.length;i++)
	        {
	        	for(int j=0;j<myOffers2.length;j++)
	        	{
	        		if(IDS[i]==myOffers2[j].getID())
	        		{
	        			ll.add(myOffers2[j]);
	        		}
	        	}
	        }
        	
        	//Création de la ArrayList qui nous permettra de remplire la listView
            ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
     
            //On déclare la HashMap qui contiendra les informations pour un item
            HashMap<String, String> map;
            
            if(ll.size()<10)
            {
            
	            for(int i=0;i<ll.size();i++)
	            {
	            	 map = new HashMap<String, String>();
	            	 map.put("titre",ll.get(i).getTitle());            	 
	            	 map.put("descr",ll.get(i).getProfil());
	            	// map.put("img", String.valueOf(R.drawable.star));
	            	 listItem.add(map);
	            }
	            LayoutParams lp = (LayoutParams) l.getLayoutParams();
	            lp.height = 100*ll.size();
	            l.setLayoutParams(lp);
	            
            }
            else
            {
            	for(int i=0;i<10;i++)
	            {
	            	 map = new HashMap<String, String>();
	            	 map.put("titre",ll.get(i).getTitle());            	 
	            	 map.put("descr",ll.get(i).getProfil());
	            	// map.put("img", String.valueOf(R.drawable.star));
	            	 listItem.add(map);
	            }
            	LayoutParams lp = (LayoutParams) l.getLayoutParams();
	            lp.height = 100*10;
	            l.setLayoutParams(lp);
            }
     
	        
	        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
	        SimpleAdapter mSchedule = new SimpleAdapter (getActivity().getBaseContext(), listItem, R.layout.affichageitem,new String[] {/*"img", */"titre", "descr"}, new int[] {/*R.id.img,*/ R.id.titre, R.id.descr});
	        
	        
	        l.setAdapter(mSchedule);
	
	            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
				@Override
	              public void onItemClick(AdapterView<?> parent, final View view,int position, long id) 
	              {
	            	  Offer temp = ll.get(position);
	            	  
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
        
        final SearchView SV = (SearchView)mLinearLayout.findViewById(R.id.searchView1);
        
        SV.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String arg0) {
				// Lorsque l'on clique sur Rechercher				
				CharSequence queryHint = SV.getQuery();
				
				
				
				String SearchText = queryHint.toString();
				ListView lresult = (ListView)mLinearLayout.findViewById(R.id.listViewresult);
				
				MainActivity.getOffBdd().open();
				
				Offer[] AllOff = MainActivity.getOffBdd().getAllOffers();
				
				final ArrayList<Offer> result = findOffers(AllOff, SearchText);
				
				
				
				MainActivity.getOffBdd().close();
				
				
				//Création de la ArrayList qui nous permettra de remplire la listView
	            ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	     
	            //On déclare la HashMap qui contiendra les informations pour un item
	            HashMap<String, String> map;
	            
	            for(int i=0;i<result.size();i++)
	            {
	            	 map = new HashMap<String, String>();
	            	 map.put("titre",result.get(i).getTitle());            	 
	            	 map.put("descr",result.get(i).getProfil());
	            	// map.put("img", String.valueOf(R.drawable.star));
	            	 listItem.add(map);
	            }
	            LayoutParams lp = (LayoutParams) lresult.getLayoutParams();
	            lp.height = 100*result.size();
	            lresult.setLayoutParams(lp);
	     
		        
		        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
		        SimpleAdapter mSchedule = new SimpleAdapter (getActivity().getBaseContext(), listItem, R.layout.affichageitem,new String[] {/*"img", */"titre", "descr"}, new int[] {/*R.id.img,*/ R.id.titre, R.id.descr});
		        
		        
		        lresult.setAdapter(mSchedule);
		
		        lresult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
					@Override
		              public void onItemClick(AdapterView<?> parent, final View view,int position, long id) 
		              {
		            	  Offer temp = result.get(position);
		            	  
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
		            
				
				
				
				
				
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
        
        return mLinearLayout;

}
	
	public ArrayList<Offer> findOffers(Offer[] tab, String search)
	{
		ArrayList<Offer> result = new ArrayList<Offer>();
		
		String[] words = search.split(" ");
		
		int[] score = new int[tab.length];
		
		for(int i=0;i<tab.length;i++)
		{
			score[i]=0;
			
			for(int j=0;j<words.length;j++)
			{
				if(tab[i].getTitle().contains(words[j]) || words[j].contains(tab[i].getTitle()))
				{
					score[i]+=150;
				}
				if(tab[i].getCompany().contains(words[j]) || words[j].contains(tab[i].getCompany()))
				{
					score[i]+=50;
				}
				if(tab[i].getCompetences().contains(words[j]) || words[j].contains(tab[i].getCompetences()))
				{
					score[i]+=20;
				}
				if(tab[i].getDescription().contains(words[j]) || words[j].contains(tab[i].getDescription()))
				{
					score[i]+=20;
				}
				
			}
		}
		
		
		score=tri_a_bulle(score, score.length);
		
		//Toast.makeText(getActivity(), "score : "+score[0]+" / "+score[1], Toast.LENGTH_LONG).show();
		
		ArrayList<Offer> resultfin = new ArrayList<Offer>();
		
		for(int i=0;i<tab.length;i++)
		{
			resultfin.add(i, tab[indice2[i]]);
		}
		
		//Toast.makeText(getActivity(), "score : "+score[0]+" / "+score[1], Toast.LENGTH_LONG).show();
				
		return resultfin;
	}
	
	int[] tri_a_bulle(int[] t,int	n)
	{ 
		int	j =0;
		int	tmp =0;
		int	en_desordre =1;
		
		indice1 = new int[t.length];
		
		for(int i=0;i<t.length;i++)
			indice1[i]=i;
		
		indice2 = new int[t.length];
		
		while(en_desordre == 1)
		{
			en_desordre = 0; 
			for	(j =0; j < n-1; j++)
			{ 
				if(t[j] > t[j+1])
				{	
					tmp = t[j+1];
					t[j+1] = t[j];
					indice1[j]++;
					indice1[j+1]--;
					t[j] = tmp;
					en_desordre = 1;
				}
			}
		}
		
		int[] tab = new int[t.length];
		for(int i=0;i<t.length;i++)
		{
			tab[i]=t[t.length-i-1];
			indice2[i]=indice1[t.length-i-1];
		}
				
		return tab;
	}


}
