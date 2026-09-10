package com.mattcorth.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream stream = ApiConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (stream != null) {
                props.load(stream);
            } else {
                throw new IOException("Unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUri() {
        return props.getProperty("postcodes.base_uri");
    }

    public static String getPostcodes() { return props.getProperty("postcodes.endpoint.postcodes"); }

    public static String getTerminatedPostcodes() { return props.getProperty("postcodes.endpoint.terminated_postcodes"); }

    public static String getScottishPostcodes() { return props.getProperty("postcodes.endpoint.scottish_postcode"); }

    public static String getOutwardCodes() { return props.getProperty("postcodes.endpoint.outward_codes"); }

    public static String getPlaces() { return props.getProperty("postcodes.endpoint.places"); }

}
