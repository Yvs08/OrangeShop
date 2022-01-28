package com.orange.shop.services.impl;

import com.orange.shop.utils.FileShopReader;
import com.orange.shop.exception.OrangeShopNotFoundException;
import com.orange.shop.models.Line;
import com.orange.shop.services.OrangeShopFinder;

import java.util.List;

public class OrangeShopFinderImpl implements OrangeShopFinder {

    public FileShopReader fileReader = new FileShopReader();


    @Override
    public String findOrangeShopWithMobileAvailable(double longitude, double latitude, String nameMobile) throws OrangeShopNotFoundException {

        List<Line> setAllLine = fileReader.setAllLine();
        String shopDescription = setAllLine.stream().filter(line -> Integer.parseInt(line.getField4()) > 0 && Integer.parseInt(line.getField5()) > 0 && Integer.parseInt(line.getField6()) > 0)
                .map(line -> getStockByMobileName(line, longitude, latitude, nameMobile))
                .reduce((x, y) -> x.getDistance().compareTo(y.getDistance()) <= 0 ? x : y).get().getField3();

        if (shopDescription == null) {
            throw new OrangeShopNotFoundException("Orange Shop Not Exist");
        }
        return shopDescription;
    }

    /* management of shops with available stocks depending on the mobile
     * */
    private Line getStockByMobileName(Line line, double longitude, double latitude, String nameMobile) {

        if (nameMobile.equals("sunusng")) {
            line.setField5(null);
            line.setField6(null);
            line.setDistance(DistanceBetweenTwoPoints(longitude, latitude, Double.parseDouble(line.getField1()), Double.parseDouble(line.getField2())));
        } else if (nameMobile.equals("ipom")) {
            line.setField4(null);
            line.setField6(null);
            line.setDistance(DistanceBetweenTwoPoints(longitude, latitude, Double.parseDouble(line.getField1()), Double.parseDouble(line.getField2())));
        } else if (nameMobile.equals("weiwei")) {
            line.setField4(null);
            line.setField5(null);
            line.setDistance(DistanceBetweenTwoPoints(longitude, latitude, Double.parseDouble(line.getField1()), Double.parseDouble(line.getField2())));
        } else {
            line.setField3(null);
            line.setDistance(1.00);
        }

        return line;
    }

    /*Algorithm which calculates a distance between two point on informed by a longitude and a latitude
     *  */
    private Double DistanceBetweenTwoPoints(double firstLongitude, double firstLatitude, double secondLongitude,
                                            double secondLatitude) {

        double z = Math.cos(Math.toRadians(firstLatitude)) * Math.cos(Math.toRadians(secondLatitude)) *
                Math.pow(Math.sin((secondLongitude - firstLongitude) / 2), 2);
        double y = Math.pow(Math.sin((secondLatitude - firstLatitude) / 2), 2);

        double x = Math.sqrt(y + z);

        int r = 6_371_000;

        return 2 * r * Math.asin(Math.toRadians(x));
    }

}

