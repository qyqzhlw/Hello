package cn.csdn.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.csdn.util.HttpUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
//����	   	
//	    ListView list = (ListView)findViewById(R.id.list);
//	    String[] b = {"���","����","���"};
//	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.array_item
//				, b);
//	    list.setAdapter(adapter);
	    
	    
	    Button bn1 = (Button)findViewById(R.id.text_two);
	    bn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					JSONArray a = getUser();
					System.err.println(a);
					
							
//					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.array_item
//							, b);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
	}

	  private JSONArray getUser() throws Exception
		{
		
			// ���巢�������URL
			String url = HttpUtil.BASE_URL+ "ContactList.jsp";
	//		DialogUtil.showDialog(this,url,false);
			// ��������
			return new JSONArray(HttpUtil.postRequest(url, null));
		}
		
		
	


	
	
	


}
