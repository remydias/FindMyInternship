package com.example.findmyinternship;


import java.security.NoSuchAlgorithmException;

import Model.OfferFav;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ViewOffer extends Activity
{
	Bundle b;
	int OffID;
	int StudID;
	int starVisibility;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        int fav=0;
        b= getIntent().getExtras();
        OffID = b.getInt("id");
        StudID = b.getInt("StudID");
        starVisibility=b.getInt("starvisibility");
        
		setContentView(R.layout.view_offer);
        
        Bundle b= getIntent().getExtras();
        
        TextView Comp = (TextView)findViewById(R.id.compname_view_offer);
        
        TextView title = (TextView)findViewById(R.id.offertitle_view_offer);
        
        TextView desc = (TextView)findViewById(R.id.offerdesc_view_offer);
        
        TextView skills = (TextView)findViewById(R.id.skills_view_offer);
        
        TextView prof = (TextView)findViewById(R.id.profile_view_offer);
        
        TextView duration = (TextView)findViewById(R.id.duration_view_offer);
        
        TextView salary = (TextView)findViewById(R.id.salary_view_offer);
        
        ImageButton back = (ImageButton)findViewById(R.id.backButton);
        
        //ImageButton star = (ImageButton)findViewById(R.id.imgStar);
        
        CheckBox star = (CheckBox)findViewById(R.id.checkstar);
        
        MainActivity.getOffFavBdd().open();
        
        OfferFav[] myOffFav = MainActivity.getOffFavBdd().getOffersFavByStudent(StudID);
        
        if(myOffFav != null)
        {
        	for(int i=0;i<myOffFav.length;i++)
        	{
        		if(myOffFav[i].getOfferID() == OffID)
        		{
        			star.setChecked(true);
        		}
        	}
        }
        
        
        MainActivity.getOffFavBdd().close();
        Comp.setText(b.getCharSequence("titree"));    
        title.setTextColor(Color.BLUE);
        title.setText(b.getCharSequence("company"));  
        
        desc.setText(b.getCharSequence("desc"));
        skills.setText(b.getCharSequence("skills"));
        prof.setText(b.getCharSequence("profile"));
        duration.setText(b.getCharSequence("duration"));
        salary.setText(b.getCharSequence("salary"));
        
        if(starVisibility == 0)
        	star.setVisibility(View.INVISIBLE);
        
        back.setOnClickListener(new OnClickListener() {
			
      	  @Override
      	  public void onClick(View v) {
      		finish();
      		}
      	});
        
        if(fav==0)
        {
        
	        star.setOnClickListener(new OnClickListener() {
				
	        	  @Override
	        	  public void onClick(View v) {
	        		  
	        		  OfferFav stud = new OfferFav();
	        		  stud.setOfferID(OffID);
	        		  stud.setStudID(StudID);
	        		
	        		  MainActivity.getOffFavBdd().open();
	           		  //On insère l'offre que l'on vient de créer
	        	        try {
	    					if(MainActivity.getOffFavBdd().insertOfferFav(stud) ==-2)
	    					{
	    						OfferFav[] myOffFav = MainActivity.getOffFavBdd().getOffersFavByStudent(StudID);
	    						
	    						if(myOffFav != null)
	    		                {
	    		                	for(int i=0;i<myOffFav.length;i++)
	    		                	{
	    		                		if(myOffFav[i].getOfferID() == OffID)
	    		                		{
	    		                			MainActivity.getOffFavBdd().removeOfferFavByID(myOffFav[i].getID());
	    		                		}
	    		                	}
	    		                }
	    						
	    						
	    						Toast.makeText(ViewOffer.this, "Offre supprimée des favoris", Toast.LENGTH_LONG).show();
	    					}
	    					else
	    					{
	    						Toast.makeText(ViewOffer.this, "Offre ajoutée aux favoris  "+OffID+"   "+StudID, Toast.LENGTH_LONG).show();
	    					}
	    				} catch (NoSuchAlgorithmException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	        	        
	        	        
	          	        
	          	        MainActivity.getOffBdd().close();
	        		}
	        	});
        }
    }

}
