package lab02.code;

import java.util.List;
// import java.util.Arrays;

import aima.core.search.api.Problem;
import aima.core.environment.map2d.InState;
import aima.core.environment.map2d.GoAction;
import aima.core.environment.support.ProblemFactory;
import aima.core.search.basic.uninformed.UniformCostSearch;
import aima.core.search.basic.uninformed.BreadthFirstSearch;
import aima.core.search.basic.uninformed.DepthLimitedTreeSearch;
import aima.core.search.basic.uninformed.IterativeDeepeningSearch;
import aima.core.environment.map2d.SimplifiedRoadMapOfPartOfRomania;

public class App {
    // Breadth-First-Search
    public static void useBFS(Problem<GoAction, InState> problem) {
        BreadthFirstSearch<GoAction, InState> bfs = new BreadthFirstSearch<>();
        List<GoAction> actions = bfs.apply(problem);
        for (Object act : actions) System.out.println("Action: " + act.toString());
    }

    // Uniform-Cost-Search or Dijkstra Algorithm
    public static void useUCS(Problem<GoAction, InState> problem) {
        UniformCostSearch<GoAction, InState> ucs = new UniformCostSearch<>();
        List<GoAction> actions = ucs.apply(problem);
        for (Object act : actions) System.out.println("Action: " + act.toString());
    }

    // Depth-Limited-Tree-Search
    public static void useDLTS(Problem<GoAction, InState> problem, int depthLimit) {
        DepthLimitedTreeSearch<GoAction, InState> dlts = new DepthLimitedTreeSearch<>(depthLimit);
        /*
        List<GoAction> actions = Arrays
                .asList(new GoAction(SimplifiedRoadMapOfPartOfRomania.SIBIU),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.FAGARAS),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.BUCHAREST));
        */
        List<GoAction> actions = dlts.apply(problem);
        for (Object act : actions) System.out.println("Action: " + act.toString());
    }

    // Iterative-Deepening-Search
    public static void useIDS(Problem<GoAction, InState> problem) {
        IterativeDeepeningSearch<GoAction, InState> ids = new IterativeDeepeningSearch<>();
        /*
        List<GoAction> actions = Arrays
                .asList(new GoAction(SimplifiedRoadMapOfPartOfRomania.SIBIU),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.FAGARAS),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.BUCHAREST));
        */
        List<GoAction> actions = ids.apply(problem);
        for (Object act : actions) System.out.println("Action: " + act.toString());
    }

    public static void main(String[] args) {
        Problem<GoAction, InState> problem = ProblemFactory
                .getSimplifiedRoadMapOfPartOfRomaniaProblem(SimplifiedRoadMapOfPartOfRomania.ARAD, SimplifiedRoadMapOfPartOfRomania.BUCHAREST);

        System.out.println("--> Simplified RoadMap of Part of Romania Problem");
        System.out.println("Starting Breadth-First-Search...");
        useBFS(problem);
        System.out.println("Finished!");
        System.out.println("------------------------------");

        System.out.println("Starting Uniform-Cost-Search or Dijkstra Algorithm...");
        useUCS(problem);
        System.out.println("Finished!");
        System.out.println("------------------------------");

        System.out.println("Starting Depth-Limited-Tree-Search...");
        useDLTS(problem, 8);
        System.out.println("Finished!");
        System.out.println("------------------------------");

        System.out.println("Starting Iterative-Deepening-Search...");
        useIDS(problem);
        System.out.println("Finished!");
    }
}
