package pl.readstack.domain.discovery;

import pl.readstack.config.DataSourceProvider;
import pl.readstack.domain.common.BaseDao;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryDao extends BaseDao {

    public List<Discovery> findAll() {
        final String query = """
        SELECT
            id, title, url, description, date_added, category_id
        FROM
            discovery d
        """;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Discovery> allDiscoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                allDiscoveries.add(discovery);
            }
            return allDiscoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discovery> findByCategory(int categoryId) {
        final String query = """
                SELECT
                    id, title, url, description, date_added, category_id
                FROM
                    discovery
                WHERE
                    category_id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            List<Discovery> discoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
            return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Discovery mapRow(ResultSet set) throws SQLException {
        int discoveryId = set.getInt("id");
        String title = set.getString("title");
        String url = set.getString("url");
        String description = set.getString("description");
        LocalDateTime dateAdded = set.getTimestamp("date_added").toLocalDateTime();
        int categoryId = set.getInt("category_id");
        return new Discovery(discoveryId, title, url, description, dateAdded, categoryId);
    }
}
