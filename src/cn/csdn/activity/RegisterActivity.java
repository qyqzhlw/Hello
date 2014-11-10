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
    	  DialogUtil.showDialog(this,"�û�ע��ɹ�",false);  
      }
      else
      {
    	 DialogUtil.showDialog(this,"�û����Ѵ��ڣ�������ע��",false);    
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
		    	DialogUtil.showDialog(this,"��������Ӧ�쳣�����Ժ����ԣ�",false);
		    	e.printStackTrace();
		    }
			return false;
	  }
	  
	  private JSONObject query(String username, String password,String mobile,String sex) throws Exception
		{
			// ʹ��Map��װ�������
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			map.put("password", password);
			map.put("mobile",mobile);
			map.put("sex",sex);
			// ���巢�������URL
			String url = HttpUtil.BASE_URL+ "register.jsp";
	//		DialogUtil.showDialog(this,url,false);
			// ��������
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
        	      	DialogUtil.showDialog(this,"�û������Ǳ�����",false);
        	    	return false;
        	    }
            if (password.equals("")){
    	      	DialogUtil.showDialog(this,"�û������Ǳ�����",false);
    	    	return false;
    	    }
		  return true;
	  }
	
	  private void openOptionsDialog(){
    	  builder= new AlertDialog.Builder(this);
    	  builder.setTitle(R.string.register_msg);
    	  builder.setMessage(R.string.register_title);
    	  
    	  builder.setPositiveButton("ȷ��",
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
