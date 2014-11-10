package cn.csdn.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.csdn.util.DialogUtil;
import cn.csdn.util.HttpUtil;


public class LoginActivity extends Activity {
	private static final int COMM_ENRTY_REQUEST_CODE = 1;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.login);
	    String strVer = android.os.Build.VERSION.RELEASE;
	    strVer = strVer.substring(0,3).trim();
	    float fv=Float.valueOf(strVer);
	    if(fv>2.3)
	    {
	    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	    .detectDiskReads()
	    .detectDiskWrites()
	    .detectNetwork() // ��������滻ΪdetectAll() �Ͱ����˴��̶�д������I/O
	    .penaltyLog() //��ӡlogcat����ȻҲ���Զ�λ��dropbox��ͨ���ļ�������Ӧ��log
	    .build());
	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
	    .detectLeakedSqlLiteObjects() //̽��SQLite���ݿ����
	    .penaltyLog() //��ӡlogcat
	    .penaltyDeath()
	    .build());
	    }
	    Button button = (Button)findViewById(R.id.register);
	    Button button2 = (Button)findViewById(R.id.login);
	    button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
	    button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onLogin();
			}
		});
	}
	
	public void onRegister(View view){
		Intent intent=new Intent(this,RegisterActivity.class);
		startActivity(intent);
	}
	
	
	public void onLogin(){
		if (validate()) {
			if (login()){ 
			
				Intent intent=new Intent(this,MainActivity.class);
				startActivity(intent);
	
				//�ɹ�
			}
			else {
				DialogUtil.showDialog(this,"��֤����",false);
			}
				
				
		}		
	}		
			
		
		
	
	private boolean validate(){
		EditText username_input=(EditText)findViewById(R.id.username);
		EditText password_input=(EditText)findViewById(R.id.password);
	    String username=username_input.getText().toString();
	    String password=password_input.getText().toString();
	    if (username.equals(""))
	    {
	    	DialogUtil.showDialog(this,"�û��˻��Ǳ�����",false);
	    	return false;
	    }
	    if (password.equals(""))
	    {
	    	DialogUtil.showDialog(this,"�û������Ǳ�����",false);
	    	return false;
	    }
		return true;
	}
	
	private boolean login(){
		EditText username_input=(EditText)findViewById(R.id.username);
		EditText password_input=(EditText)findViewById(R.id.password);
	    String username=username_input.getText().toString();
	    String password=password_input.getText().toString();
	    JSONObject jsonObj;
	    try
	    {
	    	jsonObj=query(username,password);
	    	if (jsonObj.getInt("userId")>0)
	    	{
	    		return true;
	    	}
	    }
	    catch(Exception e)
	    {
	//    	DialogUtil.showDialog(this,"��������Ӧ�쳣�����Ժ����ԣ�",false);
	    	DialogUtil.showDialog(this,e.toString(),false);
	    	e.printStackTrace();
	
	    }
		return false;
	}
	
	private JSONObject query(String username, String password) throws Exception
	{
		// ʹ��Map��װ�������
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		// ���巢�������URL
		String url =HttpUtil.BASE_URL+ "login.jsp";
	//	DialogUtil.showDialog(this,url,false);
		// ��������
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
	
	

}
