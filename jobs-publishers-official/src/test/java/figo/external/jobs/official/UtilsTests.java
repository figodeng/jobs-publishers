package figo.external.jobs.official;

import figo.external.jobs.official.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Date;

public class UtilsTests extends BaseTest {

    @Test
    public void convert_test() {
        Date date = DateUtils.convert("2023年03月08日");
        Assertions.assertNotNull(date);
    }
}
