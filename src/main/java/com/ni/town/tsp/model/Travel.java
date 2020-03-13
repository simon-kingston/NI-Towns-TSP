package com.ni.town.tsp.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ni.town.tsp.main.RunAlgorithm;

import lombok.Data;

@Data
public class Travel {
	private static final Logger log = LoggerFactory.getLogger(Travel.class);
	private ArrayList<Town> travel = new ArrayList<>();
    private ArrayList<Town> previousTravel = new ArrayList<>();

    public Travel() {
   	
    	try (BufferedReader br = new BufferedReader(new FileReader("NI-settlements-data.csv"))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        String[] values = line.split(",");
    	        travel.add(new Town(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2])));
    	    }
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void generateInitialTravel(){
        if (travel.isEmpty()) {
            new Travel();
        }
    }

    public void swapTowns() {
        int a = generateRandomIndex();
        int b = generateRandomIndex();
        previousTravel = travel;
        Town x = travel.get(a);
        Town y = travel.get(b);
        travel.set(a, y);
        travel.set(b, x);
    }

    public void revertSwap() {
        travel = previousTravel;
    }

    private int generateRandomIndex() {
        return (int) (Math.random() * travel.size());
    }

    public Town getTown(int index) {
        return travel.get(index);
    }

    public double getDistance() {
    	return getDistance(false);
    }
    
    public double getDistance(boolean printResults) {
        double distance = 0;

        for (int index = 0; index < travel.size(); index++) {
        	Town starting = getTown(index);
        	Town destination = getDestination(index);
            distance += starting.distanceToTown(destination);
        }
        
        if (printResults)
        	log.info("new total distance: {} miles", String.format("%.2f", distance));
        
        return distance;
    }
    
    public void getRoute() {
    	for (int index = 0; index < travel.size(); index++) {
    		Town starting = getTown(index);
        	Town destination = getDestination(index);
    		log.info("{} --> {}", starting.getTownName(), destination.getTownName());
    	}
    }

	private Town getDestination(int index) {
		if (index + 1 < travel.size()) {
            return getTown(index + 1);
        } else {
        	return getTown(0);
        }
	}
}
