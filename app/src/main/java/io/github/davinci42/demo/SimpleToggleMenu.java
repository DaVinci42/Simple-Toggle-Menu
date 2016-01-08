package io.github.davinci42.demo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by davinci42 on 2016/1/6.
 */
public class SimpleToggleMenu extends FrameLayout {

	@Bind(R.id.fab_main) FloatingActionButton mMainFab;
	@Bind({ R.id.fab_00, R.id.fab_01, R.id.fab_02 }) FloatingActionButton[] mFabArra;

	private int mDuration = 175;
	private long mFabMaxDistance = 180;
	private int mFabBackgroundColor;
	private Drawable mMainFabDrawable;
	private Drawable[] mFabDrawables = new Drawable[3];

	private boolean mIsExpanding;
	private float mCenterX;
	private float mCenterY;
	private float mXDistance;
	private float mYDistance;
	private float mOptionFabRadius;

	private Interpolator mInterpolator = new DecelerateInterpolator();

	public SimpleToggleMenu(Context context) {
		super(context);
		init();
	}

	public SimpleToggleMenu(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleToggleMenu);

		mFabBackgroundColor = a.getColor(R.styleable.SimpleToggleMenu_fabBackgroundColor,
			ContextCompat.getColor(context, R.color.colorAccent));

		mMainFabDrawable = a.getDrawable(R.styleable.SimpleToggleMenu_actionMainSrc);
		mFabDrawables[0] = a.getDrawable(R.styleable.SimpleToggleMenu_action1Src);
		mFabDrawables[1] = a.getDrawable(R.styleable.SimpleToggleMenu_action2Src);
		mFabDrawables[2] = a.getDrawable(R.styleable.SimpleToggleMenu_action3Src);

		a.recycle();
		init();
	}

	@Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mCenterX = (mMainFab.getLeft() + mMainFab.getRight()) / 2;
		mCenterY = (mMainFab.getTop() + mMainFab.getBottom()) / 2;

		mOptionFabRadius = Math.min(mFabArra[0].getWidth(), mFabArra[0].getHeight()) / 2;

		mXDistance = (float) (mFabMaxDistance * Math.sin(45));
		mYDistance = (float) (mFabMaxDistance * Math.cos(45));
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.layout_simple_toggle_menu, this, true);
		ButterKnife.bind(this);

		mMainFab.setBackgroundTintList(ColorStateList.valueOf(mFabBackgroundColor));
		mMainFab.setImageDrawable(mMainFabDrawable);

		for (int i = 0; i < 3; i++) {
			mFabArra[i].setBackgroundTintList(ColorStateList.valueOf(mFabBackgroundColor));
			mFabArra[i].setImageDrawable(mFabDrawables[i]);
		}

		mIsExpanding = false;

		mMainFab.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View v) {

				if (!mIsExpanding) {
					expandFabs();
				} else {
					shrinkFabs();
				}
			}
		});
	}

	private void expandFabs() {

		mIsExpanding = !mIsExpanding;

		mMainFab.animate()
			.setDuration(mDuration)
			.setInterpolator(mInterpolator)
			.rotation(45)
			.withStartAction(new Runnable() {
				@Override public void run() {

					mFabArra[0].animate()
						.setDuration(mDuration)
						.setInterpolator(mInterpolator)
						.x(mCenterX - mXDistance - mOptionFabRadius)
						.y(mCenterY - mYDistance - mOptionFabRadius)
						.start();

					mFabArra[1].animate()
						.setDuration(mDuration)
						.setInterpolator(mInterpolator)
						.y(mCenterY - mFabMaxDistance - mOptionFabRadius)
						.start();

					mFabArra[2].animate()
						.setDuration(mDuration)
						.setInterpolator(mInterpolator)
						.x(mCenterX + mXDistance - mOptionFabRadius)
						.y(mCenterY - mYDistance - mOptionFabRadius)
						.start();
				}
			})
			.start();
	}

	private void shrinkFabs() {

		mIsExpanding = !mIsExpanding;

		mMainFab.animate()
			.setDuration(mDuration)
			.setInterpolator(mInterpolator)
			.rotation(0)
			.withStartAction(new Runnable() {
				@Override public void run() {

					mFabArra[0].animate()
						.setDuration(mDuration)
						.setInterpolator(mInterpolator)
						.x(mCenterX - mOptionFabRadius)
						.y(mCenterY - mOptionFabRadius)
						.start();

					mFabArra[1].animate()
						.setDuration(mDuration)
						.setInterpolator(mInterpolator)
						.y(mCenterY - mOptionFabRadius)
						.start();

					mFabArra[2].animate()
						.setDuration(mDuration)
						.setInterpolator(mInterpolator)
						.x(mCenterX - mOptionFabRadius)
						.y(mCenterY - mOptionFabRadius)
						.start();
				}
			})
			.start();
	}

	public void setAction1OnClickListener(OnClickListener onClickListener) {
		mFabArra[0].setOnClickListener(onClickListener);
	}

	public void setAction2OnClickListener(OnClickListener onClickListener) {
		mFabArra[1].setOnClickListener(onClickListener);
	}

	public void setAction3OnClickListener(OnClickListener onClickListener) {
		mFabArra[2].setOnClickListener(onClickListener);
	}
}
