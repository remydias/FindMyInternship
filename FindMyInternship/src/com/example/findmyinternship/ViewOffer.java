package com.example.findmyinternship;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewOffer extends Activity
{
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
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
        
        Comp.setText(b.getCharSequence("titree"));    
        title.setTextColor(Color.BLUE);
        title.setText(b.getCharSequence("company"));  
        
        desc.setText(b.getCharSequence("desc"));
        skills.setText(b.getCharSequence("skills"));
        prof.setText(b.getCharSequence("profile"));
        duration.setText(b.getCharSequence("duration"));
        salary.setText(b.getCharSequence("salary"));
        
        back.setOnClickListener(new OnClickListener() {
			
      	  @Override
      	  public void onClick(View v) {
      		finish();
      		}
      	});
    }

}
