package com.prashant.custom.crouton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.prashant.custom.widget.crouton.Configuration;
import com.prashant.custom.widget.crouton.Crouton;
import com.prashant.custom.widget.crouton.Style;

@SuppressLint("ResourceAsColor")
public class CroutonDemoActivity extends Activity implements OnClickListener
{
	private static CroutonDemoActivity thisActivity;

	private static final int ALERT = 0;
	private static final int CONFIRM = 1;
	private static final int INFO = 2;
	private static final int INFINITE = 3;
	private static final int CUSTOM_STYLE = 4;
	private static final int CUSTOM_VIEW = 5;
	
	private static boolean FLAG_INFINITE = true; 
	private static final Configuration CONFIGURATION_INFINITE = new Configuration.Builder().setDuration(Configuration.DURATION_INFINITE).build();
	
	private Spinner spinnerCrouton;
	private Button btnShow;
	private EditText txtCustomText;
	private CheckBox displayOnTop;
	private Crouton crouton = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinnerCrouton = (Spinner)findViewById(R.id.spinnerCrouton);
		txtCustomText = (EditText) findViewById(R.id.txtCustomText);
		btnShow = (Button)findViewById(R.id.btnShow);
		displayOnTop = (CheckBox) findViewById(R.id.display_on_top);
		btnShow.setOnClickListener(this);
		thisActivity = this;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//Don't forget to cancel All when close the activity.
		Crouton.cancelAllCroutons();
	}
	
	@Override
	public void onClick(View v) 
	{
		if(txtCustomText.getText().toString().trim().equals(""))
		{
			txtCustomText.requestFocus();
			if(displayOnTop.isChecked())
			{
				crouton = Crouton.makeText(thisActivity, "Please enter some text first", Style.ALERT);
			}
			else
			{
				crouton = Crouton.makeText(thisActivity, "Please enter some text first", Style.ALERT, R.id.alternate_view_group);	
			}
			crouton.show();
			return;
		}
		if(v.getId()==R.id.btnShow)
		{
			String customText = txtCustomText.getText().toString().trim();
			int id = spinnerCrouton.getSelectedItemPosition();
			Style style = null;
			switch(id)
			{
				case ALERT:
//					Crouton.showText(thisActivity, customText, Style.ALERT);
					style = Style.ALERT;
					if(displayOnTop.isChecked())
					{
						crouton = Crouton.makeText(thisActivity, customText, style);
					}
					else
					{
						crouton = Crouton.makeText(thisActivity, customText, style, R.id.alternate_view_group);
					}
					crouton.show();
					break;
				case CONFIRM:
//					Crouton.showText(thisActivity, customText, Style.CONFIRM);
					style = Style.CONFIRM;
					if(displayOnTop.isChecked())
					{
						crouton = Crouton.makeText(thisActivity, customText, style);
					}
					else
					{
						crouton = Crouton.makeText(thisActivity, customText, style, R.id.alternate_view_group);
					}
					crouton.show();
					break;
				case INFO:
//					Crouton.showText(thisActivity, customText, Style.INFO);
					style = Style.INFO;
					if(displayOnTop.isChecked())
					{
						crouton = Crouton.makeText(thisActivity, customText, style);
					}
					else
					{
						crouton = Crouton.makeText(thisActivity, customText, style, R.id.alternate_view_group);
					}
					crouton.show();
					break;
				case INFINITE:
					if(FLAG_INFINITE)
					{
						if(displayOnTop.isChecked())
						{
							crouton = Crouton.makeText(thisActivity, getString(R.string.infinite_hide), Style.CUSTOM_BY_PRASHANT_ORANGE).setConfiguration(CONFIGURATION_INFINITE);
						}
						else
						{
							crouton = Crouton.makeText(thisActivity, getString(R.string.infinite_hide), Style.CUSTOM_BY_PRASHANT_ORANGE, R.id.alternate_view_group).setConfiguration(CONFIGURATION_INFINITE);
						}
						crouton.show();
						FLAG_INFINITE=false;
					}
					crouton.setOnClickListener(thisActivity);
					break;
				case CUSTOM_STYLE:
					style = new Style.Builder()
						.setBackgroundColor(R.color.barLightColor)
						.setGravity(Gravity.RIGHT)
						.setTextColor(android.R.color.black)
						.setHeight(50).build();
					if(displayOnTop.isChecked())
					{
						crouton = Crouton.makeText(this, customText, style);
					}
					else
					{
						crouton = Crouton.makeText(this, customText, style, R.id.alternate_view_group);
					}
					crouton.show();
					break;
				case CUSTOM_VIEW:
					final LayoutInflater inflater = (thisActivity).getLayoutInflater();
					View viw = inflater.inflate(R.layout.crouton_custom_view, null);
					if(displayOnTop.isChecked())
					{
						crouton =  Crouton.make(thisActivity, viw);
					}
					else
					{
						crouton =  Crouton.make(thisActivity, viw, R.id.alternate_view_group);
					}
					crouton.show();
					break;
				default: 
			        break;
			}
		}
		else
		{
			Crouton.hide(crouton);	
			FLAG_INFINITE = true;
		}
	}
}