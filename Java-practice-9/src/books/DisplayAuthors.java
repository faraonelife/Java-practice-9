package books;


import java.sql.*;

public class DisplayAuthors
    {
        public static void main(String args[])
        {
            final String DATABASE_URL = "jdbc:derby:./db/books";
            final String SELECT_QUERY =
                    "SELECT authorID, firstName, lastName FROM authors";


            try (
                    Connection connection = DriverManager.getConnection(DATABASE_URL);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(SELECT_QUERY))
            {

//         DatabaseMetaData metaDbData = connection.getMetaData();
//         ResultSet rs = metaDbData.getTables(null, null, "authors", null);
//         if (!rs.next()) {
//            System.out.println("Table does not exists");
//            String createDbSql = Files.readString(Path.of("books.sql"));
//            statement.execute(createDbSql);
//         }


                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                System.out.printf("Authors Table of Books Database:%n%n");


                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-8s\t", metaData.getColumnName(i));
                System.out.println();


                while (resultSet.next())
                {
                    for (int i = 1; i <= numberOfColumns; i++)
                        System.out.printf("%-8s\t", resultSet.getObject(i));
                    System.out.println();
                }
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }

        }
    }


