package org.example.tbanktranslate.dao;

import org.example.tbanktranslate.model.Translation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TranslationDAOImpl implements TranslationDAO {

    private final DataSource dataSource;

    public TranslationDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Translation translation) {
        String query =
                """
                INSERT INTO translations(ip_address, source_text, target_text, source_language_code, target_language_code)
                VALUES (?, ?, ?, ?, ?)
                """;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, translation.getIpAddress());
                preparedStatement.setString(2, translation.getSourceText());
                preparedStatement.setString(3, translation.getTargetText());
                preparedStatement.setString(4, translation.getSourceLanguageCode());
                preparedStatement.setString(5, translation.getTargetLanguageCode());
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
