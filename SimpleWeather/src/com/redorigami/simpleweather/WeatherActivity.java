package com.redorigami.simpleweather;

import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class WeatherActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		 
	    if (savedInstanceState == null) {
	        getSupportFragmentManager().beginTransaction()
	                .add(R.id.container, new WeatherFragment())
	                .commit();
	    }
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);*/
	    if(item.getItemId() == R.id.change_city){
	        showInputDialog();
	    }
	    return false;
		
	}

	
	private void showInputDialog(){
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("Change city");
	    final EditText input = new EditText(this);
	    input.setInputType(InputType.TYPE_CLASS_TEXT);
	    builder.setView(input);
	    builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            changeCity(input.getText().toString());
	        }
	    });
	    builder.show();
	}
	 
	public void changeCity(String city){
	    WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
	                            .findFragmentById(R.id.container);
	    wf.changeCity(city);
	    new CityPreference(this).setCity(city);
	}
	
}
