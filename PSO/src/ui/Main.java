package ui;

import java.util.List;

import business.PSOService;
import business.function.SphereFunction;
import business.topology.FocalTopology;
import business.topology.GlobalTopology;
import business.topology.LocalTopology;

public class Main {

	public static void main(String[] args) {

		PSOService psoService = new PSOService();

		Thread threadSphere = new Thread() {
			public void run() {
				SphereFunction sphereFunction = new SphereFunction();
				
				GlobalTopology globalTopology = new GlobalTopology();
				List<Double> values = psoService.executePSO(sphereFunction, globalTopology);

				LocalTopology localTopology = new LocalTopology();
				values = psoService.executePSO(sphereFunction, localTopology);
				
				FocalTopology focalTopology = new FocalTopology();
				values = psoService.executePSO(sphereFunction, focalTopology);
			}
		};

		threadSphere.start();

		/*
		 * Thread threadRastrigin = new Thread() { public void run() {
		 * RastringinFunction rastringinFunction = new RastringinFunction();
		 * 
		 * GlobalTopology globalTopology = new GlobalTopology(); List<Double> values
		 * =psoService.executePSO(rastringinFunction, globalTopology);
		 * 
		 * LocalTopology localTopology = new LocalTopology(); values =
		 * psoService.executePSO(rastringinFunction, localTopology);
		 * 
		 * FocalTopology focalTopology = new FocalTopology(); values =
		 * psoService.executePSO(rastringinFunction, focalTopology); } };
		 * 
		 * threadRastrigin.start();
		 * 
		 * Thread threadRosenbrock = new Thread() { public void run() {
		 * RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
		 * 
		 * GlobalTopology globalTopology = new GlobalTopology(); List<Double> values =
		 * psoService.executePSO(rosenbrockFunction, globalTopology);
		 * 
		 * LocalTopology localTopology = new LocalTopology(); values =
		 * psoService.executePSO(rosenbrockFunction, localTopology);
		 * 
		 * FocalTopology focalTopology = new FocalTopology(); values =
		 * psoService.executePSO(rosenbrockFunction, focalTopology); } };
		 * 
		 * threadRosenbrock.start();
		 */
		

		 

	}
}
