package lab03.code;

import aima.core.search.api.Node;
import aima.core.search.api.Problem;
import aima.core.environment.map2d.*;
import aima.core.search.api.StepCostFunction;
import aima.core.search.api.SearchForActionsFunction;
import aima.core.environment.support.ProblemFactory;
import aima.core.search.basic.informed.AStarSearch;

import java.util.List;
import java.util.ArrayList;
import java.util.function.ToDoubleFunction;

public class App {
    // A*Star Search
    public static <A, S> List<A> applyAStarSearch(Problem<A, S> problem, ToDoubleFunction<Node<A, S>> hf) {
        SearchForActionsFunction<A, S> declareSearch = new AStarSearch<>(hf);
        return declareSearch.apply(problem);
    }

    public static void main(String[] args) {
        // Map
        Map2D map = new SimplifiedRoadMapOfPartOfRomania();

        // States info
        String initialLocation = SimplifiedRoadMapOfPartOfRomania.ARAD;
        String goal = SimplifiedRoadMapOfPartOfRomania.BUCHAREST;

        // Setting the problem
        Problem<GoAction, InState> problem = ProblemFactory
                .getSimplifiedRoadMapOfPartOfRomaniaProblem(initialLocation, goal);

        // Heuristic
        Map2DFunctionFactory.StraightLineDistanceHeuristic mapFactory = new Map2DFunctionFactory.StraightLineDistanceHeuristic(map, goal);

        // Relation of Costs
        StepCostFunction<GoAction, InState> costs = Map2DFunctionFactory.getStepCostFunction(map);

        // Testing
        double c1 = costs.stepCost(new InState(SimplifiedRoadMapOfPartOfRomania.ARAD),
                new GoAction(SimplifiedRoadMapOfPartOfRomania.SIBIU),
                new InState(SimplifiedRoadMapOfPartOfRomania.SIBIU));
        System.out.println(c1);

        // Actions
        List<GoAction> actions = applyAStarSearch(problem, mapFactory);
        System.out.println("\nNumber of Actions: " + (long) actions.size());
        System.out.println("List of Actions:");
        for (GoAction action : actions) System.out.println("- Action: " + action.toString());

        // Applying A* Search
        AStarSearch<GoAction, InState> aStar = new AStarSearch<>(mapFactory);
        Node<GoAction, InState> result = aStar.search(problem);

        // Backtracking Nodes
        List<Node<GoAction, InState>> steps = new ArrayList<>();
        steps.add(result);
        Node<GoAction, InState> current = result;
        for (int i = 0; i < actions.size(); i++) {
            steps.add(current);
            current = current.parent();
        }

        // Reversing Nodes
        System.out.println("\nSteps:");
        for (int j = actions.size(); j > 0; j--) System.out.println(steps.get(j));

        // Tests
        /*
        System.out.println("\nResult: " + result.toString());
        System.out.println("\nFinal State: " + result.state());
        System.out.println("\nLast Action: " + result.action());
        System.out.println("\nLast Movement Cost: " + result.pathCost());
        System.out.println("\n1st Parent: " + result.parent());
        System.out.println("\n2nd Parent: " + result.parent().parent());
        System.out.println("\n3rd Parent: " + result.parent().parent().parent());
        System.out.println("\n4th Parent: " + result.parent().parent().parent().parent());
        */
    }
}
