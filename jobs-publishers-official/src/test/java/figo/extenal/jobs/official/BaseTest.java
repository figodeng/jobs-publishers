package figo.extenal.jobs.official;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootApplication(scanBasePackages = {
        "figo.extenal.jobs"
})
@ExtendWith(SpringExtension.class)
public class BaseTest {
}
