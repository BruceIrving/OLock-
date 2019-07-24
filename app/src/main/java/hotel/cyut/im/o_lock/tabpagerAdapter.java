package hotel.cyut.im.o_lock;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class tabpagerAdapter extends FragmentStatePagerAdapter {

    String[] tabarray = new String[]{"會員資料", "房間資訊", "開鎖房門"};
    Integer tabnumber = 3;

    public tabpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                one one1 = new one();
                return one1;
            case 1:
                two two1 = new two();
                return two1;
            case 2:
                three three1 = new three();
                return three1;
        }


        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }


}
