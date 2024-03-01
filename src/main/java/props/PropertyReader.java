package props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String getConnectionUrlForH2() {
        try (InputStream inputStream = PropertyReader.class.getClassLoader()
                .getResourceAsStream("app.properties")) {

            Properties properties = new Properties();

            if (inputStream == null) {
                System.out.println("Unable to find app.properties");
                return null;
            }

            properties.load(inputStream);

            return new StringBuilder("jdbc:")
                    .append(properties.getProperty("h2.db.host"))
                    .append(":./")
                    .append(properties.getProperty("h2.db.database"))
                    .toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
