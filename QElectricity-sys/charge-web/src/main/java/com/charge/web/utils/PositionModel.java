package com.charge.web.utils;

/**
 * Created by vincent on 18/09/2018.
 */
public class PositionModel {

    //最小纬度,即矩形左/右下纬度
    private Double MinLat;

    //最大纬度,即矩形左/右上纬度
    private Double MaxLat;

    //最小经度,即左上/下经度
    private Double MinLng;

    //最大经度右上/下经度
    private  Double MaxLng;

    PositionModel(double MinLat, double MaxLat, double MinLng, double MaxLng) {
        this.MinLat = MinLat;
        this.MaxLat = MaxLat;
        this.MinLng = MinLng;
        this.MaxLng = MaxLng;

    }

    /**
     * 获取距离用户Xkm范围的矩形坐标,
     * @return 按照左上 右上 左下 右下的顺序返回
     */
//    public Map<Double,Double> get4coordinates() {
//        Map coordinateMap = new HashMap<>();
//        //左上角坐标
//        //左上经度
//        Double left_top_lng = MinLng;
//        //左上纬度
//        Double left_top_lat = MaxLat;
//
//        //右上角坐标
//        //右上经度
//        Double right_top_lng = MaxLng;
//        //右上纬度
//        Double right_top_lat = MaxLat;
//
//        //左下角坐标
//        //左下经度
//        Double left_bottom_lng = MinLng;
//        //左下纬度
//        Double left_bottom_lat = MinLat;
//
//        //右下角坐标
//        //右下经度
//        Double ringht_bottom_lng = MaxLng;
//        //右下纬度
//        Double right_bottom_lat = MinLat;
//
//        //左上角坐标
//        coordinateMap.put(left_top_lng,left_top_lat);
//
//        //右上角坐标
//        coordinateMap.put(right_top_lng,right_top_lat);
//
//        //左下角坐标
//        coordinateMap.put(left_bottom_lng,left_bottom_lat);
//
//        //右下角坐标
//        coordinateMap.put(ringht_bottom_lng,right_bottom_lat);
//
//        return coordinateMap;
//    }


    public Double getMinLat() {
        return MinLat;
    }

    public void setMinLat(Double minLat) {
        MinLat = minLat;
    }

    public Double getMaxLat() {
        return MaxLat;
    }

    public void setMaxLat(Double maxLat) {
        MaxLat = maxLat;
    }

    public Double getMinLng() {
        return MinLng;
    }

    public void setMinLng(Double minLng) {
        MinLng = minLng;
    }

    public Double getMaxLng() {
        return MaxLng;
    }

    public void setMaxLng(Double maxLng) {
        MaxLng = maxLng;
    }

    @Override
    public String toString() {
        return "PositionModel{" +
                "MinLat=" + MinLat +
                ", MaxLat=" + MaxLat +
                ", MinLng=" + MinLng +
                ", MaxLng=" + MaxLng +
                '}';
    }
}
