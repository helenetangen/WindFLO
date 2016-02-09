public class main {

	
//  public static void main(String argv[]) {
//      try {
//          WindScenario ws = new WindScenario("../Scenarios/obs_00.xml");
//          KusiakLayoutEvaluator wfle = new KusiakLayoutEvaluator();
//          wfle.initialize(ws);
//          GA algorithm = new GA(wfle);
//          algorithm.run(); // optional, name of method 'run' provided on submission
//          // algorithm can also just use constructor
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//  }
	
	
//	  public static void main(String argv[]) {
//	      try {
//	          WindScenario windScenario = new WindScenario("../Scenarios/obs_00.xml");
//	          KusiakLayoutEvaluator evaluator = new KusiakLayoutEvaluator();
//	          evaluator.initialize(windScenario);
//	          GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, 10, 4, 5, 0.05, 0.4);
//	          ga.run(); // optional, name of method 'run' provided on submission
//	          // algorithm can also just use constructor
//	      } catch (Exception exception) {
//	          exception.printStackTrace();
//	      }
//	  }
	  
	  
	  public static void main(String argv[]) {
	      try {
	          WindScenario windScenario = new WindScenario("../Scenarios/obs_00.xml");
	          KusiakLayoutEvaluator evaluator = new KusiakLayoutEvaluator();
	          evaluator.initialize(windScenario);
	          IslandModel ga = new IslandModel(evaluator, 40, 3, 10, 0.05, 0.4, 4, 2, 1);
	          ga.runIslandModel(); // optional, name of method 'run' provided on submission
	          // algorithm can also just use constructor
	      } catch (Exception exception) {
	          exception.printStackTrace();
	      }
	  }
}
