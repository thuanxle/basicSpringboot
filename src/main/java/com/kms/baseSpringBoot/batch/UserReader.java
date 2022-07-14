package com.kms.baseSpringBoot.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kms.baseSpringBoot.models.User;

@Component
public class UserReader extends JdbcCursorItemReader<User> implements ItemReader<User>{
	
	public UserReader(@Autowired DataSource dataSource) {
		setDataSource(dataSource);
		setSql("SELECT id, username, email FROM users");
		setFetchSize(100);
		setRowMapper(new UserRowMapper());
	}
	
	public class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user  = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			return user;
		}
	}
}