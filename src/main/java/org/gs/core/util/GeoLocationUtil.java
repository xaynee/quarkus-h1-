package com.dimata.service.general.harisma.core.util;

import com.dimata.service.general.harisma.exception.FormatException;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Utility format geoLocation.
 * 
 * @author Dimata Developer.
 *
 */
@Data
public class GeoLocationUtil {
	
	public static final double LATITUDE_MIN = -90;
	public static final double LATITUDE_MAX = 90;
	
	public static final double LONGITUDE_MIN = -180;
	public static final double LONGITUDE_MAX = 180;
	
	private double latitude;
	private double longitude;
	
	public GeoLocationUtil(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}
	
	public GeoLocationUtil(String geoLocation) {
		setGeoLocation(geoLocation);
	}
	
	/**
	 * Check minimum dan maximum dari latitude yang valid. 
	 * Nilai valid berjarak maximum 90 dan minimum -90.
	 * 
	 * @param latitude nilai yang ingin dicheck.
	 * @return true jika valid.
	 * 
	 * @author Hariyogi
	 * 
	 */
	public boolean isValidLatitude(double latitude) {
		return latitude >= LATITUDE_MIN && latitude <= LATITUDE_MAX;
	}
	
	/**
	 * check minimum dan maximum dari longitude yang valid.
	 * Nilai valid berjarak antara maximum 180 dan minimum -180.
	 * 
	 * @param longitude nilai yang ingin dicheck.
	 * @return true jika valid.
	 * 
	 * @author Hariyogi
	 * 
	 */
	public boolean isValidLongitude(double longitude) {
		return longitude >= LONGITUDE_MIN && longitude <= LONGITUDE_MAX;
	}
	
	public void setLatitude(double latitude) {
		if(isValidLatitude(latitude)) {
			this.latitude = latitude;
		}else {
			throw new IllegalArgumentException("latitude of " + latitude + " is not valid"
					+ " (value is less than " + LATITUDE_MIN + " or greater than " + LATITUDE_MAX + ")");
		}
	}
	
	public void setLongitude(double longitude) {
		if(isValidLongitude(longitude)) {
			this.longitude = longitude;
		}else {
			throw new IllegalArgumentException("longitude of " + longitude + " is not valid"
					+ " (value is less than " + LONGITUDE_MIN + " or greater than " + LONGITUDE_MAX + ")");
		}
	}
	
	/**
	 * Format yang diterima adalah "latitude,longitude". Contoh : 41.0992,45.17463
	 * 
	 * @param geoLocation wajib dengan format diatas.
	 * 
	 * @author Hariyogi
	 */
	public void setGeoLocation(String geoLocation) {
		String[] split = geoLocation.split(",");
		if(split.length != 2) {
			throw new FormatException("Format geoLocation " + geoLocation + " is not valid");
		}
		
		String latitudeRaw = split[0].trim();
		String longitudeRaw = split[1].trim();
		
		if(NumberUtils.isParsable(latitudeRaw)) {
			setLatitude(Double.parseDouble(latitudeRaw));
		}else {
			throw new FormatException("Latitude is not number");
		}
		
		if(NumberUtils.isParsable(longitudeRaw)) {
			setLongitude(Double.parseDouble(longitudeRaw));
		}else {
			throw new FormatException("Longitude is not number");
		}
	}
	
	/**
	 * Mengambil geo location dengan format string. formatnya adalah "latitude,longitude".
	 * Contoh : 41.0992,45.17463
	 * 
	 * @return geo location dengan format diatas.
	 * 
	 * @author Hariyogi
	 */
	public String getGeoInWord() {
		return latitude + "," + longitude;
	}
}
