package in.informationworks.app.vedicmath;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TestReport extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 // TODO Auto-generated method stub
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.testreport);
	    Bundle bundle = this.getIntent().getExtras();
		//TestName = bundle.getString("keyTestName");
		try
		{
		TextView detailsTotTimeTaken = (TextView)findViewById(R.id.lbltot_timetaken);
		detailsTotTimeTaken.setText(String.valueOf(bundle.getLong("keyTotTimeTaken")));
		TextView detailsAvgTimeTaken = (TextView)findViewById(R.id.lblavg_timetaken);
		detailsAvgTimeTaken.setText(String.valueOf(bundle.getLong("keyAvgTimeTaken")));
		TextView detailsTotQue = (TextView)findViewById(R.id.lbltot_que);
		detailsTotQue.setText(bundle.getInt("keyTotQue"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();

		//ShowTestReport();
	}
	public void ShowTestReport()
	{
		
		/*TextView detailsTotCorr = (TextView)findViewById(R.id.lbltot_correct);
		TextView detailsTotAttempt = (TextView)findViewById(R.id.lbltot_attempt);
		TextView detailsTotTimeTaken = (TextView)findViewById(R.id.lbltot_timetaken);
		RelativeLayout RowTotTimeTaken = (RelativeLayout)findViewById(R.id.rowtot_timetaken);
		TextView detailsRawScore = (TextView)findViewById(R.id.lblraw_score);
		TextView detailsFakeQue = (TextView)findViewById(R.id.lblfake_que);
		TextView detailstxtFakeQue = (TextView)findViewById(R.id.txtfake_que);

	    
		TextView detailsTotInCorr = (TextView)findViewById(R.id.lbltot_incorrect);
		TextView detailsPer = (TextView)findViewById(R.id.lblpercentage);
		TextView detailsrawscore = (TextView)findViewById(R.id.lblrawscore);
		Button btnReviewAttempt = (Button)findViewById(R.id.btnReviewAttempt);
		btnReviewAttempt.setOnClickListener(this);*/
		

	}

	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
