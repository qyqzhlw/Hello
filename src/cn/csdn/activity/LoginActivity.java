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
	    .detectNetwork() // 这里可以替换为detectAll() 就包括了磁盘读写和网络I/O
	    .penaltyLog() //打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
	    .build());
	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
	    .detectLeakedSqlLiteObjects() //探测SQLite数据库操作
	    .penaltyLog() //打印logcat
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
	
				//成功
			}
			else {
				DialogUtil.showDialog(this,"验证错误",false);
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
	    	DialogUtil.showDialog(this,"用户账户是必填项",false);
	    	return false;
	    }
	    if (password.equals(""))
	    {
	    	DialogUtil.showDialog(this,"用户口令是必填项",false);
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
	//    	DialogUtil.showDialog(this,"服务器响应异常，请稍后再试！",false);
	    	DialogUtil.showDialog(this,e.toString(),false);
	    	e.printStackTrace();
	
	    }
		return false;
	}
	
	private JSONObject query(String username, String password) throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		// 定义发送请求的URL
		String url =HttpUtil.BASE_URL+ "login.jsp";
	//	DialogUtil.showDialog(this,url,false);
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
	
	

}
