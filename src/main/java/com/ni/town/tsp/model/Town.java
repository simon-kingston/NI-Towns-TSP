package com.ni.town.tsp.model;

import lombok.Data;

@Data
public class Town {
	public static final double R = 6372.8; // In kilometers
	public static final double MI = 1.609344498; // miles ratio
	private String townName;
	private double x;
    private double y;

    public Town(String townName, double latitude, double longitude) {
    	this.townName = townName;
        this.x = longitude;
        this.y = latitude;
    }

    public double distanceToTown(Town town) {
        return haversine(getY(), getX(), town.getY(), town.getX());
    }
	
	// generally used geo measurement function
	private double haversine(double lat1, double lon1, double lat2, double lon2) {
		
		double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c / MI;
	}
}
