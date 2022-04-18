package br.com.goodpractices.resources;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/leaks")
@Log4j2
public class MemoryLeakResource {

    @GetMapping(path = "/heap")
    public void memoryLeakExample() {
        List<Integer> incrementNumbersLeak = new ArrayList<>();
        var number = 0;
        while (true) {
            incrementNumbersLeak.add(number);
            number++;
        }
    }

    @GetMapping(path = "/heapsleep")
    public void heapSleepExample() {
        try {
            List<Integer> incrementNumbersLeak = new ArrayList<>();
            var number = 0;
            var sleepSeconds = 50l;
            while (true) {
                incrementNumbersLeak.add(number);
                number++;

                Thread.sleep(sleepSeconds);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
