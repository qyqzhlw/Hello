package cn.csdn.activity;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cn.csdn.util.DialogUtil;
import cn.csdn.util.HttpUtil;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	Builder builder;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
	    Button button = (Button)findViewById(R.id.Register);
	    button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onRegister();
			}
		});
	}
	
	
	public void onRegister(){
	EditText  username_input=(EditText)findViewById(R.id.username); 
	EditText  password_input=(EditText)findViewById(R.id.password);
	EditText  mobile_input=(EditText)findViewById(R.id.mobile);
	String username=username_input.getText().toString();
	String password=password_input.getText().toString();
	String mobile=mobile_input.getText().toString();
	if (validate()){
      if (register()) {
    	  DialogUtil.showDialog(this,"用户注册成功",false);  
      }
      else
      {
    	 DialogUtil.showDialog(this,"用户名已存在，请重新注册",false);    
      }
	}
	
		
	}

	  private boolean register(){
		    JSONObject jsonObj;
		    String username=((EditText)findViewById(R.id.username)).getText().toString();
		    String password=((EditText)findViewById(R.id.password)).getText().toString();
		    String mobile=((EditText)findViewById(R.id.mobile)).getText().toString();
		    String sex=((EditText)findViewById(R.id.sex)).getText().toString();
		    
		    try
		    {
		    	jsonObj=query(username,password,mobile,sex);
		    	if (jsonObj.getInt("userId")>0)
		    	{
		    		return true;
		    	}
		    }
		    catch(Exception e)
		    {
		    	DialogUtil.showDialog(this,"服务器响应异常，请稍后再试！",false);
		    	e.printStackTrace();
		    }
			return false;
	  }
	  
	  private JSONObject query(String username, String password,String mobile,String sex) throws Exception
		{
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			map.put("password", password);
			map.put("mobile",mobile);
			map.put("sex",sex);
			// 定义发送请求的URL
			String url = HttpUtil.BASE_URL+ "register.jsp";
	//		DialogUtil.showDialog(this,url,false);
			// 发送请求
			return new JSONObject(HttpUtil.postRequest(url, map));
		}
	
	  private boolean validate(){
			EditText  username_input=(EditText)findViewById(R.id.username); 
			EditText  password_input=(EditText)findViewById(R.id.password);
			EditText  mobile_input=(EditText)findViewById(R.id.mobile);
			String username=username_input.getText().toString();
			String password=password_input.getText().toString();
			String mobile=mobile_input.getText().toString();
            if (username.equals("")){
        	      	DialogUtil.showDialog(this,"用户名称是必填项",false);
        	    	return false;
        	    }
            if (password.equals("")){
    	      	DialogUtil.showDialog(this,"用户口令是必填项",false);
    	    	return false;
    	    }
		  return true;
	  }
	
	  private void openOptionsDialog(){
    	  builder= new AlertDialog.Builder(this);
    	  builder.setTitle(R.string.register_msg);
    	  builder.setMessage(R.string.register_title);
    	  
    	  builder.setPositiveButton("确认",
    			  new DialogInterface.OnClickListener(){
    		         public void onClick(
    		         DialogInterface dialoginterface,int i){}
    	  });
    
    	
    	  builder.show();
      
     }

	
	
	
	public void onCancel(View view){
		finish();
	}
}
