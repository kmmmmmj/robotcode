package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SelectActivity extends AppCompatActivity  {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private CircleAnimIndicator circleAnimIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        circleAnimIndicator = (CircleAnimIndicator) findViewById(R.id.circleAnimIndicator);
        mPagerAdapter = new PagerAdapter( getSupportFragmentManager() );
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);

        출처: http://iw90.tistory.com/237 [woong's]
        //원사이의 간격
        circleAnimIndicator.setItemMargin(8);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.createDotPanel(CommonObject.nation_name.length, R.drawable.indicator_non , R.drawable.indicator_on);

    }
    /**
     * ViewPager 전환시 호출되는 메서드
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            circleAnimIndicator.selectDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            return PageFragment.create(position);
        }

        @Override
        public int getCount() {
            return CommonObject.nation_name.length;  // 총 5개의 page를 보여줍니다c.
        }

    }

    public static class PageFragment extends Fragment {

        private int mPageNumber;
        public ImageView[] flags;


        public static PageFragment create(int pageNumber) {
            PageFragment fragment = new PageFragment();
            Bundle args = new Bundle();
            args.putInt("page", pageNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPageNumber = getArguments().getInt("page");
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.viewpager_study, container, false);


            flags = new ImageView[6];

            int[] imageID = new int[]{R.id.nation_flag1,R.id.nation_flag2,R.id.nation_flag3,R.id.nation_flag4,R.id.nation_flag5,R.id.nation_flag6};
            for(int i=0; i<6;i++){
                flags[i] = (ImageView) rootView.findViewById(imageID[i]);
                flags[i].setImageResource(CommonObject.nation_flag[mPageNumber]);
                flags[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     Intent intent = new Intent(getContext(),TempActivity.class);
                     //여기 인덱스 값 넣어줘야 함 mPageNumber를 대신해서! mPageNumber사용한 CommonObject부분 코드 다 읽어볼것
                     intent.putExtra("index",0);
                     startActivity(intent);

                    }
                });
            }
            return rootView;
        }
    }

}
