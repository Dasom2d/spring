package com.spring.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoJdbc implements UserDao{
//    private ConnectionMaker connectionMaker;
    // 의존관계 주입 생성자
//    public UserDao(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }

    // 수정자 메소드 DI
//    public void setConnectionMaker(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }

    private DataSource dataSource;
    private JdbcContext jdbcContext;
    private JdbcTemplate jdbcTemplate;

    public UserDaoJdbc() {
    }

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }

//    public void add(final User user) throws SQLException {
//        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//
//                return ps;
//            }
//        });
//    }

    // 익명 내부 클래스
//    public void add(final User user) throws SQLException {
//        jdbcContextWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//
//                return ps;
//            }
//        });
//    }

    // 로컬 클래스
//    public void add(final User user) throws SQLException {
//        class AddStatement implements StatementStrategy {
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//
//
//                return ps;
//            }
//        }
//        StatementStrategy st = new AddStatement();
//        jdbcContextWithStatementStrategy(st);
//    }

//    public void add(User user) throws SQLException, ClassNotFoundException {
//        jdbcContextWithStatementStrategy(new AddStatement(user));
//    }

//    public void add(User user) throws SQLException, ClassNotFoundException {
////        Connection c = connectionMaker.makeConnection();
//        Connection c = dataSource.getConnection();
//        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//        ps.setString(1, user.getId());
//        ps.setString(2, user.getName());
//        ps.setString(3, user.getPassword());
//
//        ps.executeUpdate();
//
//        ps.close();
//        c.close();
//    }

    private RowMapper<User> userRowMapper =
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user = new User();
                    user.setId(resultSet.getString("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            };

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        User user = new User();
                        user.setId(resultSet.getString("id"));
                        user.setName(resultSet.getString("name"));
                        user.setPassword(resultSet.getString("password"));
                        return user;
                    }
                });
    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
            new Object[] {id},
            this.userRowMapper);
    };

//    public User get(String id) throws SQLException, ClassNotFoundException {
////        Connection c = connectionMaker.makeConnection();
//        Connection c = dataSource.getConnection();
//        PreparedStatement ps = c.prepareStatement("select * from users where id =?");
//        ps.setString(1, id);
//
//        ResultSet rs = ps.executeQuery();
//
//        User user = null;
//        if(rs.next()) {
//            user = new User();
//            user.setId(rs.getString("id"));
//            user.setName(rs.getString("name"));
//            user.setPassword(rs.getString("password"));
//        }
//
//        rs.close();
//        ps.close();
//        c.close();
//
//        if(user == null) throw new EmptyResultDataAccessException(1);
//
//        return user;
//
//    }


    // jdbc
    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }
    // 템플릿/콜백 메소드
//    public void deleteAll() throws SQLException {
////        executeSql("delete from users");
//        this.jdbcContext.executeSql("delete from users");
//    }

    private void executeSql(final String query) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement(query);
                return ps;
            }
        });
    }

//    public void deleteAll() throws SQLException {
//        jdbcContextWithStatementStrategy(new DeleteAllStrategy());
//    }

//    public void deleteAll() throws SQLException {
//        Connection c = null;
//        PreparedStatement ps = null;
//
//        try {
//            c = dataSource.getConnection();
////            ps = makeStatement(c);
//            StatementStrategy statementStrategy = new DeleteAllStrategy();
//            ps = statementStrategy.makePreparedStatement(c);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if(ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                }
//            }
//
//            if(c != null) {
//                try {
//                    c.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//    }

    public int getCount() {
        return this.jdbcTemplate.queryForInt("select count(*) from users");
    }

//    public int getCount() throws SQLException {
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            c = dataSource.getConnection();
//            ps = c.prepareStatement("select count(*) from users");
//
//            rs = ps.executeQuery();
//            rs.next();
//            return rs.getInt(1);
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if(rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e){
//                }
//            }
//            if(ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                }
//            }
//            if(c != null) {
//                try {
//                    c.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//    }

    // 메소드 추출
//    private PreparedStatement makeStatement(Connection c) throws SQLException{
//        PreparedStatement ps;
//        ps = c.prepareStatement("delete from users");
//
//        return ps;
//    }

    // 템플릿 메소드 패턴 적용
//    abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) { try { ps .close(); } catch (SQLException e) {} }
            if (c != null) { try {c.close(); } catch (SQLException e) {} }
        }
    }


}
