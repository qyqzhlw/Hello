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
				 tip = checkedId == R.id.nan ? "�����Ա�������" : "�����Ա���Ů��";
				
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
