package com.team20.Wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 *  Ref： Based on our CMPT 213 Assignment to develop and adjust the details to fix out project
 */

public class LocationWrapper {
    public int x;
    public int y;


    public static LocationWrapper getOneCellLocation(int xPos, int yPos) {
        LocationWrapper location = new LocationWrapper();
        location.x = xPos;
        location.y = yPos;

        return location;
    }

    public static List<LocationWrapper> getCellsLocations(int[] xPos, int[] yPos) {
        List<LocationWrapper> locations = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            locations.add(getOneCellLocation(xPos[i], yPos[i]));
        }
        return locations;
    }
}
