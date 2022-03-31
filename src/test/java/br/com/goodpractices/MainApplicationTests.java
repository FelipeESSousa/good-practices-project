package br.com.goodpractices;

import br.com.goodpractices.resources.PersonResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    private PersonResource personResource;

    @Test
    void contextLoads() {
        assertThat(personResource).isNotNull();
    }

}
