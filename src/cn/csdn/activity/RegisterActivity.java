package cn.csdn.activity;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.csdn.util.DialogUtil;
import cn.csdn.util.HttpUtil;

public class RegisterActivity extends Activity {
	Builder builder;
	EditText username_input;
	EditText  password_input;
	EditText  mobile_input;
	RadioGroup rg;
	String tip;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		username_input=(EditText)findViewById(R.id.usernameRegister); 
		password_input=(EditText)findViewById(R.id.passwordRegister);
		mobile_input=(EditText)findViewById(R.id.mobile);
		rg = (RadioGroup)findViewById(R.id.sex);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup grouo, int checkedId) {
				// TODO Auto-generated method stub
				 tip = checkedId == R.id.nan ? "您的性别是男人" : "您到性别是女人";
				
			}
		});
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
		    String username=username_input.getText().toString();
		    String password=password_input.getText().toString();
		    String mobile=mobile_input.getText().toString();
		    String sex= tip;
		    
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
