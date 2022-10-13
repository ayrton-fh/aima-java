package lab06.code;

import java.util.List;
import java.util.ArrayList;

import aima.core.search.api.CSP;
import aima.core.search.basic.csp.AC3;
import aima.core.search.api.Constraint;
import aima.core.search.basic.support.BasicCSP;
import aima.core.environment.support.CSPFactory;
import aima.core.search.basic.support.BasicConstraint;

public class Main {
    private static void labExample() {
        Object[] digits = new Object[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        String[] variables = new String[] {"X", "Y", "Z"};
        Object[][] domain = new Object[][] {digits, digits, digits};
        Constraint restriction = BasicConstraint.newTabularConstraint(variables, new Object[][] {{0, 0}, {1, 1}, {2, 4}, {3, 9}});

        CSP csp = new BasicCSP(variables, domain, restriction);

        System.out.println("Variáveis : " + csp.getVariables());
        System.out.println("Dominios  : " + csp.getDomains());
        System.out.println("DominioX  : " + csp.getDomains().get(csp.indexOf("X")).getValues());
        System.out.println("DominioX-2: " + csp.getDomainValues("X"));
        System.out.println("DominioY  : " + csp.getDomains().get(csp.indexOf("Y")).getValues());
        System.out.println("DominioZ  : " + csp.getDomains().get(csp.indexOf("Z")).getValues());
        System.out.println("RestricaoBinaria: " + csp.getConstraints().get(0).isBinary());
        System.out.println("csp       : " + csp);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("DominioX-2: " + csp.getDomainValues("X") );

        System.out.println("=====================================================================\n");
        //------------------------------------------------------------------------------------------

        CSP csp2 = CSPFactory.mapColoringTerritoriesOfAustraliaCSP();

        System.out.println( "Variáveis  : " + csp2.getVariables() );
        System.out.println( "Dominios   : " + csp2.getDomains() );
        System.out.println( "DominioWA  : " + csp2.getDomains().get( csp2.indexOf("WA") ).getValues() );
        System.out.println( "DominioWA-2: " + csp2.getDomains().get( csp2.indexOf("WA") ).getValues() );
        System.out.println( "DominioNT  : " + csp2.getDomains().get( csp2.indexOf("NT") ).getValues() );
        System.out.println( "DominioQ   : " + csp2.getDomains().get( csp2.indexOf("Q") ).getValues() );
        System.out.println( "RestricaoBinaria: " + csp2.getConstraints().get(8).isBinary() );
        System.out.println( "csp        : " + csp2 );
        System.out.println("---------------------------------------------------------------------");

        System.out.println("DominioWA  : " + csp2.getDomains().get(csp2.indexOf("WA")).getValues());
        System.out.println("Reduzindo dominio de WA para red");
        csp2.getDomain("WA").reduceDomainTo( "red");
        System.out.println("DominioWA  : " + csp2.getDomains().get(csp2.indexOf("WA")).getValues());

        System.out.println("---------------------------------------------------------------------");

        System.out.println("DominioSA  : " + csp2.getDomains().get(csp2.indexOf("SA")).getValues());
        System.out.println( "Removendo green em SA");
        csp2.getDomain("SA").delete("green");
        System.out.println("DominioSA  : " + csp2.getDomains().get(csp2.indexOf("SA")).getValues());

        System.out.println("Removendo blue em SA");
        csp2.getDomain("SA").delete("blue");
        //csp.getDomains().get(X.i).delete(x);
        System.out.println("DominioSA  : " + csp2.getDomains().get(csp2.indexOf("SA")).getValues());

        System.out.println("=Consistencia de Arco====================================================================\n");

        System.out.println();
        String[] variables2 = new String[] {"X", "Y"};
        Object[][] domains2 = new Object[][] {digits, digits};
        Constraint restriction2 = new BasicConstraint(variables2,
                values -> ((Integer) values[1]) == ((Integer) values[0]) * ((Integer) values[0]));
                // values -> ((Integer) values[0]) + ((Integer) values[1]) == 4);

        CSP csp3 = new BasicCSP(variables2, domains2, restriction2);

        System.out.println("Variáveis : " + csp3.getVariables());
        System.out.println("Dominios  : " + csp3.getDomains());
        System.out.println("DominioX  : " + csp3.getDomains().get(csp3.indexOf("X")).getValues());
        System.out.println("DominioX-2: " + csp3.getDomainValues("X"));
        System.out.println("DominioY  : " + csp3.getDomains().get(csp3.indexOf("Y")).getValues());
        System.out.println("RestricaoBinaria: " + csp3.getConstraints().get(0).isBinary()) ;

        System.out.println("csp       : " + csp3);
        System.out.println("---------------------------------------------------------------------");

        System.out.println("DominioX-2: " + csp3.getDomainValues("X"));
        System.out.println("---------------------------------------------------------------------\n");

        System.out.println("----------------ac3.test()----------------");
        AC3 ac3 = new AC3();
        ac3.test(csp3);
        System.out.println("Variáveis : " + csp3.getVariables());
        System.out.println("Dominios  : " + csp3.getDomains());
        System.out.println("DominioX  : " + csp3.getDomains().get( csp3.indexOf("X")).getValues());
        System.out.println("DominioX-2: " + csp3.getDomainValues("X"));
        System.out.println("DominioY  : " + csp3.getDomains().get( csp3.indexOf("Y")).getValues());
        System.out.println("RestricaoBinaria: " + csp3.getConstraints().get(0).isBinary());
        System.out.println("csp       : " + csp3);

        System.out.println("=====================================================================");

        //------------------------------------------------------------------------------------------
    }

    private static void jobShopSchedule() {
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

        CSP csp = new BasicCSP(tasks, domain, restrictions.toArray(new Constraint[0]));
        System.out.println("---------- CSP BEFORE AC3 Algorithm ----------");
        System.out.println("CSP:\n" + csp);

        AC3 ac3 = new AC3();
        ac3.test(csp);
        System.out.println("\n---------- CSP AFTER AC3 Algorithm ----------");
        System.out.println("CSP completed:\n" + csp);
        System.out.println("\nCSP splitted:");
        for (String task : tasks) System.out.println(task + " - " + csp.getDomainValues(task));
    }

    public static void main(String[] args) {
        // labExample()
        jobShopSchedule();
    }
}
