
package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    private static JsonNode readJson() {

        try {
            InputStream input = TestDataProvider.class
                    .getClassLoader()
                    .getResourceAsStream("testdata.json");

            if (input == null) {
                throw new RuntimeException("testdata.json not found!");
            }

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readTree(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data", e);
        }
    }

    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {

        JsonNode data = readJson().get("validLogin");

        return new Object[][]{
                {
                        data.get("username").asText(),
                        data.get("password").asText()
                }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {

        JsonNode data = readJson().get("invalidLogins");

        List<Object[]> testData = new ArrayList<>();

        for (JsonNode item : data) {

            testData.add(new Object[]{
                    item.get("username").asText(),
                    item.get("password").asText()
            });
        }

        return testData.toArray(new Object[0][]);
    }
}

