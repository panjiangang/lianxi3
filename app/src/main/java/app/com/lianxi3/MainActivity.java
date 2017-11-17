package app.com.lianxi3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class MainActivity extends Activity {
    public AMapLocationClient client;
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

        }
    };

    public AMapLocationClientOption option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLocation();
    }

    private void startLocation() {
        client = new AMapLocationClient(getApplicationContext());
        client.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {

                if (aMapLocation.getErrorCode() == 0){
                    if (aMapLocation != null) {
                        double lat = aMapLocation.getLatitude();
                        double lng = aMapLocation.getLongitude();
                        System.out.print("lat=:" + lat + "------------lng=:" + lng);
                    }
                }else {
                    System.out.print("aMapLocation=:" + aMapLocation);
                    client.stopLocation();
                }


            }
        });

        option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        option.setOnceLocation(true);
        option.setOnceLocationLatest(true);
        option.setNeedAddress(true);
        option.setHttpTimeOut(30000);
        option.setLocationCacheEnable(false);
        client.setLocationOption(option);


    }
}
