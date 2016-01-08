package io.github.davinci42.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@Bind(R.id.stm) SimpleToggleMenu mStm;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		mStm.setAction1OnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				Toast.makeText(MainActivity.this, "You clicked Fab 01", Toast.LENGTH_SHORT).show();
			}
		});

		mStm.setAction2OnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				Toast.makeText(MainActivity.this, "You clicked Fab 02", Toast.LENGTH_SHORT).show();
			}
		});

		mStm.setAction3OnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				Toast.makeText(MainActivity.this, "You clicked Fab 03", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
