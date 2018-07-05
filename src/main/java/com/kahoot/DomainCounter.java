package com.kahoot;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * This class counts all domains from a list of Emails and prints the top ten to console
 */
public class DomainCounter {

    /**
     * @param emails a "\n" separated list of emails
     * @return List of all Domains and the number of occurences
     */
    public List<Domain> countAndPrint(String[] emails) {
        if(emails == null) {
            return Collections.emptyList();
        }
        Map<String, Long> countedDomains = Arrays.stream(emails)
                .filter(email -> email.contains("@"))
                .map(email -> email.substring(email.indexOf('@') + 1))
                .collect(groupingBy(c -> c, counting()));

        List<Domain> sortedDomains = countedDomains.entrySet().stream()
                .map(email -> new Domain(email.getKey(), email.getValue()))
                .sorted(Comparator.comparingLong(Domain::getCount).reversed())
                .limit(10)
                .collect(Collectors.toList());
        sortedDomains.forEach(domain -> System.out.println(domain.getDomain() + " " + domain.getCount()));
        return sortedDomains;
    }
}
