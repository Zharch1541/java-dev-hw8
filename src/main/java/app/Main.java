package app;

import org.flywaydb.core.Flyway;
import props.PropertyReader;

public class Main {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource(PropertyReader.getConnectionUrlForH2(), null, null)
                .load();

        flyway.migrate();

    }
}
