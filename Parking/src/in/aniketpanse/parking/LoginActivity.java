package in.aniketpanse.parking;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity
{
	EditText uname;
	EditText passwd;
	Button submit;
	Button newusr;
	String response;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

//		if(!Utils.isNetworkAvailable(LoginActivity.this))
//		{
//			Utils.showToast("Not Connected!", this);
//		}

		uname = (EditText) findViewById(R.id.etusername);
		passwd = (EditText) findViewById(R.id.etpassword);
		submit = (Button) findViewById(R.id.blogin);
		newusr = (Button) findViewById(R.id.bnewuser);
		newusr.setOnClickListener(
				new OnClickListener()
				{
					@Override
					public void onClick(View arg0) 
					{
						Intent i=new Intent(LoginActivity.this, UserSignupActivity.class);
						startActivity(i);
					};
				});
		
		submit.setOnClickListener(
				new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
					{
						new LoginTask().execute();
					}
				});
	}
	
	String trylogin()
	{
		
		String user_name = uname.getText().toString();
        String password= passwd.getText().toString();        
		try
        {
             
        	HttpClient client = new DefaultHttpClient();  
	        user_name = URLEncoder.encode(user_name, "utf-8");
	        password = URLEncoder.encode(password, "utf-8");
	        Resources r=getResources();
		    String getURL = r.getString(R.string.root_url)+"login_user.php"+"?uname="+user_name+"&pwd="+password;
		    HttpGet get = new HttpGet(getURL);
		    HttpResponse responseGet = client.execute(get);  
		    HttpEntity resEntityGet = responseGet.getEntity();  
		    if (resEntityGet != null) 
		    {  
		    	response = EntityUtils.toString(resEntityGet);
		    }
	    } 
        catch(Exception e)
        {
        	response = "-1";
        }
		return response;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	class LoginTask extends AsyncTask<String, Void, String>
	{
		ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(LoginActivity.this);
			pDialog.setCancelable(false);
			pDialog.setProgressStyle(0);
			pDialog.setMessage("Please Wait");
			pDialog.show();
		}
		
		protected String doInBackground(String... params)
		{
			String resp = trylogin();
			return resp;
		}
		
		protected void onPostExecute(String response)
		{
			super.onPostExecute(response);
			if (null != pDialog && pDialog.isShowing()) 
			{
				pDialog.dismiss();
			}
			if(response.compareTo("-1")==0)
			{
				Utils.showToast("Unknown Error Occured, exiting", getApplicationContext());
			}
			else if(response.compareTo("1") == 0)
			{
//				Intent i = new Intent(LoginActivity.this,)
			}
			else
			{
				Utils.showToast("Invalid Username/Password", getApplicationContext());
			}
		}
	}
}
