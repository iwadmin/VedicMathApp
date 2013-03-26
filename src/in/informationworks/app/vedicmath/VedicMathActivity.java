package in.informationworks.app.vedicmath;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class VedicMathActivity extends Activity implements OnClickListener{

	TextView txtNo1;
	TextView txtNo2;
	TextView txtOperation;
	TextView txtTimes;
	TextView txtTimer;
	EditText txtAns;
	String times="";
	String testName = "";
	static int queId=1;
	private static final Hashtable<String,Long> TIMETAKENHASH = new Hashtable<String,Long>();
	Long sec=0l;
	QueTimeTakenCounter timer = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedicmath);
        Bundle bundle = this.getIntent().getExtras();
        testName = bundle.getString("keyTestName");
        txtNo1 = (TextView)findViewById(R.id.txtNo1);
        txtNo2 = (TextView)findViewById(R.id.txtNo2);
        txtOperation = (TextView)findViewById(R.id.txtOperation);
        txtTimes = (TextView)findViewById(R.id.txtTimes);
        txtTimer = (TextView)findViewById(R.id.txtTimer);
        txtAns = (EditText)findViewById(R.id.txtAns);
        
        ImageView imgNextQue = (ImageView) findViewById(R.id.nextque);
        imgNextQue.setOnClickListener(this);
		
        ImageView imgSubmitTest = (ImageView) findViewById(R.id.submittest);
        imgSubmitTest.setOnClickListener(this);
		
		timer = new QueTimeTakenCounter(300000,1000);
		showQuestion();

    }

    public class QueTimeTakenCounter extends CountDownTimer{ 
        public QueTimeTakenCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
 
        @Override
        public void onFinish() {
        	times = times.concat(String.valueOf((sec)));
        	txtTimes.setText(times);
        }
 
        @Override
        public void onTick(long millisUntilFinished) {
        	txtTimer.setText((300 - (millisUntilFinished/1000))+"");
        	sec=(300 - (millisUntilFinished/1000));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void showQuestion()
    {
    	if(queId!=1)
    	{
	    	TIMETAKENHASH.put(String.valueOf(queId), sec);
	    	times = times.concat(String.valueOf(sec));
	    	times = times.concat(",");
    	}
    	/*txtTimes.setText(times);*/
    	timer.cancel();
        timer.start();

        txtAns.setText("");
    	txtNo1.setText(String.valueOf(getRandomNumber(2)));
    	txtOperation.setText("*");
    	txtNo2.setText(String.valueOf(getRandomNumber(2)));
    	queId++;
    }
    
    public void submitTest()
    {
    	Long totTimeTaken=0L;
    	
    	TIMETAKENHASH.put(String.valueOf(queId), sec);
    	times = times.concat(String.valueOf(sec));
    	times = times.concat(",");
    	int totque = TIMETAKENHASH.size();
    	
    	Intent intent = new Intent(this, TestReport.class);
		Bundle bundle = new Bundle();
		bundle.putInt("keyTotQue", totque);
		
		Iterator it = TIMETAKENHASH.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        totTimeTaken = totTimeTaken + Long.parseLong(pairs.getValue().toString());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		bundle.putLong("keyTotTimeTaken", totTimeTaken);
		bundle.putLong("keyAvgTimeTaken", totTimeTaken/totque);
		intent.putExtras(bundle);
		this.finish();
		startActivity(intent);
    }

    public long getRandomNumber(int length)
    {
		long E2=0;
		while(E2==0)
			E2 = Math.round(Math.random()*Math.pow(10, length)); 
        return E2;
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.nextque:
				showQuestion();
				break;
			case R.id.submittest:
				submitTest();
				break;
			default:
				break;
		}
		
	}
}
