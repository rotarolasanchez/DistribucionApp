package com.example.shopper.distribucionapp.Dao;

import com.example.shopper.distribucionapp.Dao.Distance;
import com.example.shopper.distribucionapp.Dao.Duration;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Mai Thanh Hiep on 4/3/2016.
 */
public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
