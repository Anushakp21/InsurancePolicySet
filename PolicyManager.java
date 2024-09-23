package insurancemanagementusingset;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PolicyManager {
    private Set<Policy> hashSetPolicies;
    private Set<Policy> linkedHashSetPolicies;
    private Set<Policy> treeSetPolicies;

    public PolicyManager() {
        hashSetPolicies = new HashSet<>();
        linkedHashSetPolicies = new LinkedHashSet<>();
        treeSetPolicies = new TreeSet<>();
    }

    public void addPolicy(Policy policy) {
        hashSetPolicies.add(policy);
        linkedHashSetPolicies.add(policy);
        treeSetPolicies.add(policy);
    }

    public Set<Policy> getAllUniquePolicies() {
        return new HashSet<>(hashSetPolicies);
    }

    public List<Policy> getPoliciesExpiringSoon() {
        List<Policy> expiringSoon = new ArrayList<>();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date thirtyDaysFromNow = cal.getTime();

        for (Policy policy : treeSetPolicies) {
            if (!policy.getExpiryDate().before(now) && policy.getExpiryDate().before(thirtyDaysFromNow)) {
                expiringSoon.add(policy);
            }
        }
        return expiringSoon;
    }

    public List<Policy> getPoliciesByCoverage(String coverageType) {
        List<Policy> result = new ArrayList<>();
        for (Policy policy : hashSetPolicies) {
            if (policy.getCoverageType().equalsIgnoreCase(coverageType)) {
                result.add(policy);
            }
        }
        return result;
    }

    public List<Policy> findDuplicatePolicies() {
        Map<String, Integer> policyCount = new HashMap<>();
        for (Policy policy : hashSetPolicies) {
            policyCount.put(policy.getPolicyNumber(), policyCount.getOrDefault(policy.getPolicyNumber(), 0) + 1);
        }

        List<Policy> duplicates = new ArrayList<>();
        for (Policy policy : hashSetPolicies) {
            if (policyCount.get(policy.getPolicyNumber()) > 1) {
                duplicates.add(policy);
            }
        }
        return duplicates;
    }

    public void comparePerformance(int iterations) {
        long startTime, endTime;

        // HashSet performance
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            addPolicy(new Policy("P" + i, "Holder" + i, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(i)), "Type" + (i % 3), 100.0));
        }
        endTime = System.nanoTime();
        System.out.println("HashSet time: " + (endTime - startTime) + " ns");

        // LinkedHashSet performance
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linkedHashSetPolicies.add(new Policy("P" + i, "Holder" + i, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(i)), "Type" + (i % 3), 100.0));
        }
        endTime = System.nanoTime();
        System.out.println("LinkedHashSet time: " + (endTime - startTime) + " ns");

        // TreeSet performance
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            treeSetPolicies.add(new Policy("P" + i, "Holder" + i, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(i)), "Type" + (i % 3), 100.0));
        }
        endTime = System.nanoTime();
        System.out.println("TreeSet time: " + (endTime - startTime) + " ns");
    }
}

