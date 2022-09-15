package lab06.code;

import java.time.LocalDateTime;

import aima.core.search.api.CSP;
import aima.core.search.basic.csp.AC3;
import aima.core.search.api.Constraint;
import aima.core.search.basic.support.BasicCSP;
import aima.core.environment.support.CSPFactory;
import aima.core.search.basic.support.BasicConstraint;

public class Main {
    public static void labExample() {
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

    public static void jobShopSchedule() {
        int crrtMin = LocalDateTime.now().getMinute();
        String[] tasks = new String[] {"AxleF", "AxleB", "WheelRF", "WheelLF", "WheelRB", "WheelLB", "NutsRF",
                "NutsLF", "NutsRB", "NutsLB", "CapRF", "CapLF", "CapRB", "CapLB", "Inspect"};
        int[] domain = new int[] {};
        for (int i = 0; i < 15; i++) domain[i] = crrtMin;

        // Constraint restriction = new BasicConstraint(tasks,
          //      values -> (values + (String) values[0]  <= values[1]);

        // CSP csp = new BasicCSP(tasks, domain, restriction);
    }

    public static void main(String[] args) {
        // labExample()
        // jobShopSchedule();

    }
}
