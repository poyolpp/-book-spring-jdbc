package th.ku.bookstore;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImp implements BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Book book) {
        String query = "INSERT INTO Book (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice() };
        jdbcTemplate.update(query, data);
    }


    @Override
    public void update(int id, Book book) {
        String query = "UPDATE Book SET id = ?, name = ?, price = ? WHERE id = ?;";
        Object[] args = new Object[]{book.getId(), book.getName(), book.getPrice(), id};
        jdbcTemplate.update(query, args);
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM Book WHERE id = ?;";
        Object[] args = new Object[]{id};
        jdbcTemplate.update(query, args);
    }

    @Override
    public Book findById(int id) {
        String query = "SELECT * FROM Book WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(query, args, new BookRowMapper());
    }

    @Override
    public List<Book> findAll() {
        // เอามาทั้งหมดเลย
        String query = "SELECT * FROM Book";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;


    }

//    class BookRowMapper implements RowMapper<Book> {
//
//            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//                //":id" ตั้งชื่อตามคอลัมน์ในฐานข้อมูล
//                //new แล้วต้องดึงข้อมูลจาก database
//                Book book = new Book(resultSet.getInt(1),
//                        resultSet.getString(2)
//                        ,resultSet.getDouble(3));
//                return book;
//            }
//    }
}

