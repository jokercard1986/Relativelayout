package com.example.relativelayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MessageActivity extends Activity {

	private static final String FILE_NAME = "history.txt";
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
	
		//textView = (TextView) findViewById(R.id.textView1);
		
		listView = (ListView) findViewById(R.id.listView1);
		String text = getIntent().getStringExtra("text");
		boolean isChecked = getIntent().getBooleanExtra("checkbox", false);

		writeToFile(text);
		setListViewData();
	}

	private void setListViewData() {
		String allText = readFile();
		String[] messages = allText.split("\n");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, messages);
		listView.setAdapter(adapter);
	}

	private void writeToFile(String text) {
		try {
			text += "\n";
			FileOutputStream fos = openFileOutput(FILE_NAME,
					Context.MODE_APPEND);
			fos.write(text.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String readFile() {

		try {
			FileInputStream fis = openFileInput(FILE_NAME);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String line = null;
			String result = "";
			while ((line = br.readLine()) != null) {
				result += line + "\n";
			}
			return result;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}