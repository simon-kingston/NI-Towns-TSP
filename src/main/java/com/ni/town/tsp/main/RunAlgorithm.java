package com.ni.town.tsp.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ni.town.tsp.algorithms.SimulatedAnnealing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
public class RunAlgorithm implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(RunAlgorithm.class);
	

	public static void main(String[] args)  throws InstantiationException, IllegalAccessException, FileNotFoundException, IOException
	{
		Scanner in = new Scanner(System.in);
		log.info("Running NI Towns TSP");
		log.info("Run algorithm:");
		log.info("1 - Simulated Annealing");
		// TODO log.info("2 - Simple Genetic Algorithm");
		// TODO log.info("3 - Ant Colony");
		// TODO log.info("4 - Greedy Search");
		
		// TODO Make ga decision be decided in commandline arguments
		int decision = 1;// in.nextInt();
		
		switch (decision) {
			case 1:
				log.info("Optimized distance for travel: [{}]", SimulatedAnnealing.simulateAnnealing(10.0, 10000, 0.9995));
				break;
			default:
				log.info("Unknown option");
				break;
		}
		
		in.close();
	}


	@Override
	public void run(String... args) throws Exception {
		
	}

}

