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

    // A*Star Search Modified
    public static <A, S> List<A> applyAStarSearchModified(Problem<A, S> problem, ToDoubleFunction<Node<A, S>> hf) {
        SearchForActionsFunction<A, S> declareSearch = new AStarSearchModified<>(hf);
        return declareSearch.apply(problem);
    }

    public static void main(String[] args) {
        // map
        Map2D map = new SimplifiedRoadMapOfPartOfRomania();

        // states info
        String initialLocation = SimplifiedRoadMapOfPartOfRomania.ARAD;
        String goal = SimplifiedRoadMapOfPartOfRomania.BUCHAREST;

        // setting the problem
        Problem<GoAction, InState> problem = ProblemFactory
                .getSimplifiedRoadMapOfPartOfRomaniaProblem(initialLocation, goal);

        // heuristic
        Map2DFunctionFactory.StraightLineDistanceHeuristic mapFactory = new Map2DFunctionFactory.StraightLineDistanceHeuristic(map, goal);

        // relation of costs
        StepCostFunction<GoAction, InState> costs = Map2DFunctionFactory.getStepCostFunction(map);

        // testing cost relations
        double c1 = costs.stepCost(new InState(SimplifiedRoadMapOfPartOfRomania.ARAD),
                new GoAction(SimplifiedRoadMapOfPartOfRomania.SIBIU),
                new InState(SimplifiedRoadMapOfPartOfRomania.SIBIU));
        System.out.println("Test:\nThe cost of Step ARAD --> SIBIU is " + c1);

        System.out.println("\n---------- Old A* Search ----------");
        // execution 1
        // running A* Search by an method
        List<GoAction> actions = applyAStarSearch(problem, mapFactory);
        System.out.println("\nNumber of Actions: " + (long) actions.size() + "\n");
        System.out.println("List of Actions:");
        for (GoAction action : actions) System.out.println("- Action: " + action.toString());
        System.out.print("\n");

        // execution 2
        // running directly A* Search
        AStarSearch<GoAction, InState> aStar = new AStarSearch<>(mapFactory);
        Node<GoAction, InState> result = aStar.search(problem);

        // backtracking Nodes
        List<Node<GoAction, InState>> steps = new ArrayList<>();
        double totalCost = 0;
        steps.add(result);
        Node<GoAction, InState> current = result;
        for (int i = 0; i < actions.size(); i++) {
            steps.add(current);
            totalCost += current.pathCost();
            current = current.parent();
        }

        // reversing Nodes
        System.out.println("Steps:");
        for (int j = actions.size(); j > 0; j--) System.out.println(steps.get(j));

        // showing total cost
        System.out.println("\nTotal cost: " + totalCost);

        System.out.println("\n---------- Modified A* Search ----------");
        // execution 1
        // running A* Search Modified by an method
        List<GoAction> modifiedActions = applyAStarSearchModified(problem, mapFactory);
        System.out.println("\nNumber of Actions: " + (long) modifiedActions.size() + "\n");
        System.out.println("List of Actions:");
        for (GoAction action : modifiedActions) System.out.println("- Action: " + action.toString());

        // execution 2
        // running directly A* Search Modified
        AStarSearchModified<GoAction, InState> aStarModified = new AStarSearchModified<>(mapFactory);
        Node<GoAction, InState> resultModified = aStarModified.search(problem);
        double modifiedTotalCost = 0;

        // expanded nodes
        System.out.println("\nExpanded nodes:");
        for (Node<GoAction, InState> expandedNode : aStarModified.expandedNodes) System.out.println(expandedNode);

        // expanded states
        System.out.println("\nExpanded states:");
        for (Node<GoAction, InState> expandedNode : aStarModified.expandedNodes) {
            modifiedTotalCost += expandedNode.pathCost();
            System.out.println(expandedNode.state());
        }

        // showing total of expanded nodes
        System.out.println("\nTotal of expanded nodes: " + aStarModified.numExpandedNodes);

        // showing total expanded cost
        System.out.println("\nTotal cost: " + modifiedTotalCost);

        /* Tests
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
