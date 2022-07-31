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
    public static void useBFS() {
        BreadthFirstSearch<GoAction, InState> bfs = new BreadthFirstSearch<>();
        Problem<GoAction, InState> problem = ProblemFactory
                .getSimplifiedRoadMapOfPartOfRomaniaProblem(SimplifiedRoadMapOfPartOfRomania.ARAD, SimplifiedRoadMapOfPartOfRomania.BUCHAREST);
        List<GoAction> result = bfs.apply(problem);
        for (Object act : result) System.out.println("State: " + act.toString());
    }

    // Uniform-Cost-Search or Dijkstra Algorithm
    public static void useUCS() {
        UniformCostSearch<GoAction, InState> ucs = new UniformCostSearch<>();
        Problem<GoAction, InState> problem = ProblemFactory
                .getSimplifiedRoadMapOfPartOfRomaniaProblem(SimplifiedRoadMapOfPartOfRomania.ARAD, SimplifiedRoadMapOfPartOfRomania.BUCHAREST);
        List<GoAction> result = ucs.apply(problem);
        for (Object act : result) System.out.println("State: " + act.toString());
    }

    // Depth-Limited-Tree-Search
    public static void useDLTS(int depthLimit) {
        DepthLimitedTreeSearch<GoAction, InState> dlts = new DepthLimitedTreeSearch<>(depthLimit);
        Problem<GoAction, InState> problem = ProblemFactory
                .getSimplifiedRoadMapOfPartOfRomaniaProblem(SimplifiedRoadMapOfPartOfRomania.ARAD, SimplifiedRoadMapOfPartOfRomania.BUCHAREST);
        /*
        List<GoAction> actions = Arrays
                .asList(new GoAction(SimplifiedRoadMapOfPartOfRomania.SIBIU),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.FAGARAS),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.BUCHAREST));
        */
        List<GoAction> result = dlts.apply(problem);
        for (Object act : result) System.out.println("State: " + act.toString());
    }

    // Iterative-Deepening-Search
    public static void useIDS() {
        IterativeDeepeningSearch<GoAction, InState> ids = new IterativeDeepeningSearch<>();
        Problem<GoAction, InState> problem = ProblemFactory
                .getSimplifiedRoadMapOfPartOfRomaniaProblem(SimplifiedRoadMapOfPartOfRomania.ARAD, SimplifiedRoadMapOfPartOfRomania.BUCHAREST);
        /*
        List<GoAction> actions = Arrays
                .asList(new GoAction(SimplifiedRoadMapOfPartOfRomania.SIBIU),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.FAGARAS),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.BUCHAREST));
        */
        List<GoAction> result = ids.apply(problem);
        for (Object act : result) System.out.println("State: " + act.toString());
    }

    public static void main(String[] args) {
        System.out.println("--> Simplified RoadMap of Part of Romania Problem");
        System.out.println("Starting Breadth-First-Search...");
        useBFS();
        System.out.println("Finished!");
        System.out.println("------------------------------");

        System.out.println("Starting Uniform-Cost-Search or Dijkstra Algorithm...");
        useUCS();
        System.out.println("Finished!");
        System.out.println("------------------------------");

        System.out.println("Starting Depth-Limited-Tree-Search...");
        useDLTS(8);
        System.out.println("Finished!");
        System.out.println("------------------------------");

        System.out.println("Starting Iterative-Deepening-Search...");
        useIDS();
        System.out.println("Finished!");
    }
}
