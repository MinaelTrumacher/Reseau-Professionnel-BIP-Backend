package afpa.mra.importation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class DataImportRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final Environment environment;

    @Autowired
    public DataImportRunner(JdbcTemplate jdbcTemplate, Environment environment) {
        this.jdbcTemplate = jdbcTemplate;
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws IOException {
        // Verification si spring.jpa.hibernate.ddl-auto est en mode create sinon ne rien importer
        String ddlAutoValue = environment.getProperty("spring.jpa.hibernate.ddl-auto");
        if (!"create".equals(ddlAutoValue)) {
            System.out.println("Mode update");
            return;
        }

        // Charger le fichier Table_geocalisations depuis le dossier resource
        ClassPathResource resource = new ClassPathResource("Table_geolocalisation.csv");
        byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String csvContent = new String(data, StandardCharsets.UTF_8);

        // Preparation des variables SQL
        String tableName = "geolocalisations";
        String[] columns = { "id","code_postal", "latitude", "longitude", "region", "ville" };

        // Couper le CSV en ligne et l'inserer ligne par ligne avec une requete JDBC
        System.out.println("Commencement de l'importation des geolocalisations...");
        String[] rows = csvContent.split("\n");
        for (int i = 1; i < rows.length; i++) {
            String row = rows[i];
            String[] values = row.split(",");
            if (values.length == columns.length) {
                // Convertir l'id en long 
                Long id = Long.parseLong(values[0]);
                jdbcTemplate.update("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?)", id,values[3], values[1], values[2], values[4], values[5]);
            }
        }

        System.out.println("Importation réussite!");
    }
}
