# Simple-Toggle-Menu

The original idea is from [Simple Toggle Menu | Material Up](http://www.materialup.com/posts/simple-toggle-menu)

by [Maria del Carmen](http://www.materialup.com/MariDlCrmn)

![](AndroidSimpleToggleMenu.gif)


how to use in `.xml`:

	<io.github.davinci42.demo.SimpleToggleMenu
		android:id="@+id/stm"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		
		app:fabBackgroundColor="@color/fab_tint"
		app:actionMainSrc="@drawable/ic_add_white_24dp"
		app:action1Src="@drawable/ic_call_white_24dp"
		app:action2Src="@drawable/ic_mail_outline_white_24dp"
		app:action3Src="@drawable/ic_mode_edit_white_24dp"
		/>
		


how to `setOnClickListener()` in `Activity`:

	
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
	
		}
	}

