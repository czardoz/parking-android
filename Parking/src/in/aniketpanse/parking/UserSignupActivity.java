package in.aniketpanse.parking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class UserSignupActivity extends Activity
{
	EditText new_name;
	EditText new_pass;
	Button reg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_signup);
		new_name = (EditText) findViewById(R.id.etnewuser);
		new_pass = (EditText) findViewById(R.id.etnewpasswd);
		reg = (Button) findViewById(R.id.bregister);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_signup, menu);
		return true;
	}
}
