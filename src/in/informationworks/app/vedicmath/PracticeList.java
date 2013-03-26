package in.informationworks.app.vedicmath;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PracticeList extends ListActivity {
	List<String> names=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicelist);
        
        names = Arrays.asList("2 Digit Multiplication", "3 Digit Multiplication", "new name");
        
        MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.practicerow,names);
		setListAdapter(adapter);
    }
    
	static class PracticeTestListViewHolder {
		public int position;
		TextView txtPracticeTestName;
	}

    public class MyCustomAdapter extends ArrayAdapter<String> {

		public MyCustomAdapter(Context context, int textViewResourceId,
				List<String> list) {
			super(context, textViewResourceId, list);
		}
	
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View row = convertView;
			PracticeTestListViewHolder holder;
	        if (row == null)
	        {
	        	LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.practicerow, parent,false);
	            holder = new PracticeTestListViewHolder();
	            holder.txtPracticeTestName =(TextView)row.findViewById(R.id.txtPracticeTestName);
	           
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (PracticeTestListViewHolder) convertView.getTag();
	        }
		
			holder.txtPracticeTestName.setText(names.get(position));
			
			return row;
		}
	}
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, VedicMathActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("keyTestName", names.get(position));
		
		intent.putExtras(bundle);
		
		startActivity(intent);
	}
    
    
}
