package lab04.code;

import java.util.List;
import java.util.ArrayList;
import aima.core.search.api.ConditionalPlan;
import aima.core.environment.vacuum.VEWorldState;
import aima.core.environment.vacuum.VELocalState;
import aima.core.search.api.NondeterministicProblem;
import aima.core.environment.vacuum.VacuumEnvironment;
import aima.core.search.basic.contingency.AndOrGraphSearch;
import aima.core.environment.support.NondeterministicProblemFactory;


public class Main {
    private static final AndOrGraphSearch<String, VEWorldState> orSearch = new AndOrGraphSearch<>();

    private static ConditionalPlan<String, VEWorldState> applySearch(String location, VELocalState state1, VELocalState state2) {
        NondeterministicProblem<String, VEWorldState> problemFactory = NondeterministicProblemFactory
                .getSimpleErraticVacuumWorldProblem(location, state1, state2);
        return orSearch.apply(problemFactory);
    }

    public static void main(String[] args) {
        // ---------- single test ----------
        String startLocation = VacuumEnvironment.LOCATION_A;

        // normal situation
        VELocalState localState1 = new VELocalState(VacuumEnvironment.LOCATION_A, VacuumEnvironment.Status.Clean);
        VELocalState localState2 = new VELocalState(VacuumEnvironment.LOCATION_B, VacuumEnvironment.Status.Dirty);

        // problem situation 1
        // VELocalState localState1 = new VELocalState(VacuumEnvironment.LOCATION_A, VacuumEnvironment.Status.Dirty);
        // VELocalState localState2 = new VELocalState(VacuumEnvironment.LOCATION_B, VacuumEnvironment.Status.Dirty);

        // problem situation 2
        // VELocalState localState1 = new VELocalState(VacuumEnvironment.LOCATION_A, VacuumEnvironment.Status.Clean);
        // VELocalState localState2 = new VELocalState(VacuumEnvironment.LOCATION_B, VacuumEnvironment.Status.Clean);

        ConditionalPlan<String, VEWorldState> newPlan = applySearch(startLocation, localState1, localState2);

        System.out.println("Plan:\n" + newPlan);

        // ---------- group of tests ----------
        //List<VEWorldState> inputStates = new ArrayList<>();
        // inputStates.add(new VEWorldState(new VELocalState(VacuumEnvironment.LOCATION_A, VacuumEnvironment.Status.Dirty),
        //                                new VELocalState(VacuumEnvironment.LOCATION_B, VacuumEnvironment.Status.Dirty)));

        //List<ConditionalPlan.Interator<String, VEWorldState>> actions = new ArrayList<>();
        //for (VEWorldState s : inputStates) {
        //  actions.add(newPlan.iterator());
        //}

        // System.out.println("Iterator " + newPlan.iterator());
    }
}
