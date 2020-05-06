package com.ni.town.tsp.algorithms;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ni.town.tsp.model.Travel;

public class SimulatedAnnealing {
	private static final Logger log = LoggerFactory.getLogger(SimulatedAnnealing.class);
	
	private static Travel travel = new Travel();

    public static double simulateAnnealing(double startingTemperature, int numberOfIterations, double coolingRate) throws FileNotFoundException, IOException {
    	log.info("Starting SA with temperature: [{}], # of iterations: [{}] and colling rate: [{}]", startingTemperature , numberOfIterations , coolingRate);
        double t = startingTemperature;
        boolean debugOutput = true;
        boolean firstTime = false;
        travel.generateInitialTravel();
        double bestDistance = travel.getDistance(debugOutput);
        log.info("Initial distance of travel: [{}]", bestDistance);
        Travel bestSolution = travel;
        Travel currentSolution = bestSolution;

        for (int i = 0; i < numberOfIterations; i++) {
            if (t > 0.1) {
                currentSolution.swapTowns();
                double currentDistance = currentSolution.getDistance();
                if (currentDistance < bestDistance) {
                	log.info("improvent in iteration #{}", i);
                    bestDistance = currentSolution.getDistance(debugOutput);
                } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                    currentSolution.revertSwap();
                }
                t *= coolingRate;
            } else {
            	if (!firstTime) {
            		log.error("Temperature at zero from iteration #{}", i);
            		firstTime = true;
            	}
                continue;
            }
            if (i % 1000 == 0) {
                log.info("Iteration #{}",i);
            }
        }
        currentSolution.getRoute();
        return bestDistance;
    }
}
