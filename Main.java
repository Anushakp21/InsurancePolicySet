package insurancemanagementusingset;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        PolicyManager manager = new PolicyManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        manager.addPolicy(new Policy("P001", "John Doe", sdf.parse("2024-10-15"), "Health", 1000.0));
        manager.addPolicy(new Policy("P002", "Jane Smith", sdf.parse("2024-09-30"), "Auto", 1500.0));
        manager.addPolicy(new Policy("P003", "Alice Johnson", sdf.parse("2024-10-05"), "Home", 2000.0));
        manager.addPolicy(new Policy("P004", "Bob Brown", sdf.parse("2024-11-01"), "Auto", 1200.0));
        manager.addPolicy(new Policy("P005", "Alice Johnson", sdf.parse("2024-10-05"), "Health", 900.0)); // Duplicate example

        System.out.println("All Unique Policies:");
        manager.getAllUniquePolicies().forEach(System.out::println);

        System.out.println("\nPolicies Expiring Soon:");
        manager.getPoliciesExpiringSoon().forEach(System.out::println);

        System.out.println("\nPolicies with coverage type 'Health':");
        manager.getPoliciesByCoverage("Health").forEach(System.out::println);

        System.out.println("\nDuplicate Policies:");
        manager.findDuplicatePolicies().forEach(System.out::println);

        System.out.println("\nPerformance Comparison (1000 iterations):");
        manager.comparePerformance(1000);
    }
}

