package lab07.code;

import aima.core.search.api.CSP;
import aima.core.search.basic.csp.AC3;
import aima.core.search.api.Constraint;
import aima.core.search.basic.support.BasicCSP;
import aima.core.search.basic.csp.BacktrackingSearch;
import aima.core.search.basic.support.BasicConstraint;
import aima.core.search.api.SearchForAssignmentFunction;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static void jobShopScheduleWithBacktracking() {
        String[] tasks = new String[] {"AxleF", "AxleB", "WheelRF", "WheelLF", "WheelRB", "WheelLB", "NutsRF",
                "NutsLF", "NutsRB", "NutsLB", "CapRF", "CapLF", "CapRB", "CapLB", "Inspect"};

        Object[] values = new Object[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};

        Object[][] domain = new Object[][] {values, values, values, values, values,
                values, values, values, values, values,
                values, values, values, values, values};

        // Axis and Wheels
        Constraint restriction_1 = new BasicConstraint(new String[]{"AxleF", "WheelRF"},
                v -> ((Integer) v[0] + 10 <= (Integer) v[1]));

        Constraint restriction_2 = new BasicConstraint(new String[]{"AxleF", "WheelLF"},
                v -> ((Integer) v[0] + 10 <= (Integer) v[1]));

        Constraint restriction_3 = new BasicConstraint(new String[]{"AxleB", "WheelRB"},
                v -> ((Integer) v[0] + 10 <= (Integer) v[1]));

        Constraint restriction_4 = new BasicConstraint(new String[]{"AxleB", "WheelLB"},
                v -> ((Integer) v[0] + 10 <= (Integer) v[1]));

        // Wheels and Nuts
        Constraint restriction_5 = new BasicConstraint(new String[]{"WheelRF", "NutsRF"},
                v -> ((Integer) v[0] + 1 <= (Integer) v[1]));

        Constraint restriction_6 = new BasicConstraint(new String[]{"WheelLF", "NutsLF"},
                v -> ((Integer) v[0] + 1 <= (Integer) v[1]));

        Constraint restriction_7 = new BasicConstraint(new String[]{"WheelRB", "NutsRB"},
                v -> ((Integer) v[0] + 1 <= (Integer) v[1]));

        Constraint restriction_8 = new BasicConstraint(new String[]{"WheelLB", "NutsLB"},
                v -> ((Integer) v[0] + 1 <= (Integer) v[1]));

        // Nuts and Hubcaps
        Constraint restriction_9 = new BasicConstraint(new String[]{"NutsRF", "CapRF"},
                v -> ((Integer) v[0] + 2 <= (Integer) v[1]));

        Constraint restriction_10 = new BasicConstraint(new String[]{"NutsLF", "CapLF"},
                v -> ((Integer) v[0] + 2 <= (Integer) v[1]));

        Constraint restriction_11 = new BasicConstraint(new String[]{"NutsRB", "CapRB"},
                v -> ((Integer) v[0] + 2 <= (Integer) v[1]));

        Constraint restriction_12 = new BasicConstraint(new String[]{"NutsLB", "CapLB"},
                v -> ((Integer) v[0] + 2 <= (Integer) v[1]));

        // Sharing tools
        Constraint restriction_13 = new BasicConstraint(new String[]{"AxleF", "AxleB"},
                v -> ((Integer) v[0] + 10 <= (Integer) v[1]) || ((Integer) v[1] + 10 <= (Integer) v[0]));

        List<Constraint> restrictions = new ArrayList<>();
        restrictions.add(restriction_1);
        restrictions.add(restriction_2);
        restrictions.add(restriction_3);
        restrictions.add(restriction_4);
        restrictions.add(restriction_5);
        restrictions.add(restriction_6);
        restrictions.add(restriction_7);
        restrictions.add(restriction_8);
        restrictions.add(restriction_9);
        restrictions.add(restriction_10);
        restrictions.add(restriction_11);
        restrictions.add(restriction_12);
        restrictions.add(restriction_13);

        // Inspection
        for (int i = 0; i < 14; i++) {
            Constraint newRestriction = new BasicConstraint(new String[] {tasks[i], "Inspect"},
                    v -> ((Integer) v[0] + 3 <= (Integer) v[1]));

            restrictions.add(newRestriction);
        }

        // CSP
        CSP csp = new BasicCSP(tasks, domain, restrictions.toArray(new Constraint[0]));
        System.out.println("---------- CSP BEFORE AC3 Algorithm ----------");
        System.out.println("CSP:\n" + csp);

        // AC3
        AC3 ac3 = new AC3();
        ac3.test(csp);
        System.out.println("\n---------- CSP AFTER AC3 Algorithm ----------");
        System.out.println("CSP completed:\n" + csp);
        System.out.println("\nCSP splitted:");
        for (String task : tasks) System.out.println(task + " - " + csp.getDomainValues(task));

        // Backtracking
        AtomicReference<BacktrackingSearch> backtrack = new AtomicReference<>(new BacktrackingSearch());
        String searchHeuristic = "ForwardCheckingInferenceBackTrackingSearch";
        backtrack.get().setInferenceFunction(BacktrackingSearch.getInferenceForwardCheckingFunction());
        SearchForAssignmentFunction search = backtrack.get();
        search.apply(csp);
        System.out.println("\n---------- CSP AFTER Backtracking with " + searchHeuristic + " heuristic ----------");
        System.out.println("CSP completed:\n" + csp);
        System.out.println("\nCSP splitted:");
        for (String task : tasks) System.out.println(task + " - " + csp.getDomainValues(task));
    }

    public static void main(String[] args) {
        jobShopScheduleWithBacktracking();
    }
}
