package com.kahoot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DomainCounterTest {

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

    @BeforeEach
    void redirectSystemOutStream() {
        originalSystemOut = System.out;
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }

    @AfterEach
    void restoreSystemOutStream() {
        System.setOut(originalSystemOut);
    }

    @DisplayName("Testing with more then 10 emails")
    @Test
    public void testCountEmails_more_then_ten() {
        String[] emailString = readEmailString("src/test/resources/emails_more_then_10.txt");

        DomainCounter domainCounter = new DomainCounter();
        List<Domain> sortedDomains = domainCounter.countAndPrint(emailString);

        assertEquals(10, sortedDomains.size());
        assertAll("sortedDomains",
                () -> assertEquals("aaa.de", sortedDomains.get(0).getDomain()),
                () -> assertEquals(9, sortedDomains.get(0).getCount()),
                () -> assertEquals("bbb.de", sortedDomains.get(1).getDomain()),
                () -> assertEquals(5, sortedDomains.get(1).getCount())
        );

        assertEquals("aaa.de 9\n" +
                "bbb.de 5\n" +
                "ccc.de 3\n" +
                "ddd.de 2\n" +
                "mmm.de 1\n" +
                "iii.de 1\n" +
                "hhh.de 1\n" +
                "ggg.de 1\n" +
                "lll.de 1\n" +
                "jjj.de 1\n", systemOutContent.toString());

    }

    @DisplayName("Testing with less then 10 emails")
    @Test
    public void testCountEmails_less_then_ten() {
        String[] emailString = readEmailString("src/test/resources/emails_less_then_10.txt");

        DomainCounter domainCounter = new DomainCounter();
        List<Domain> sortedDomains = domainCounter.countAndPrint(emailString);

        assertAll("sortedDomains",
                () -> assertEquals("aaa.de", sortedDomains.get(0).getDomain()),
                () -> assertEquals(9, sortedDomains.get(0).getCount()),
                () -> assertEquals("bbb.de", sortedDomains.get(1).getDomain()),
                () -> assertEquals(5, sortedDomains.get(1).getCount())
        );

        assertEquals(3, sortedDomains.size());
        assertEquals("aaa.de 9\n" +
                "bbb.de 5\n" +
                "ccc.de 3\n", systemOutContent.toString());

    }

    @DisplayName("Testing input null")
    @Test
    public void testCountEmails_null() {
        DomainCounter domainCounter = new DomainCounter();
        List<Domain> sortedDomains = domainCounter.countAndPrint(null);

        assertEquals(Collections.EMPTY_LIST, sortedDomains);
        assertEquals("", systemOutContent.toString());
    }

    @DisplayName("Testing a malformed string")
    @Test
    public void testCountEmails_malformedString() {
        String emailString[] = readEmailString("src/test/resources/malformed_file.txt");

        DomainCounter domainCounter = new DomainCounter();
        List<Domain> sortedDomains = domainCounter.countAndPrint(emailString);

        assertEquals(Collections.EMPTY_LIST, sortedDomains);
        assertEquals("", systemOutContent.toString());
    }



    private String[] readEmailString(String file) {
        try {
            return Files.lines(Paths.get(file)).collect(Collectors.joining("\n")).split("\n");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}