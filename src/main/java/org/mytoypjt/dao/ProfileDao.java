package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class ProfileDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Profile> profileRowMapper;

    public ProfileDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("profile");
        profileRowMapper = (rs, rowNum) -> {
            Profile profile = new Profile(
                    rs.getInt("account_no"),
                    rs.getString("nicname"),
                    rs.getDate("register_date"),
                    rs.getString("city"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getInt("user_level")
            );
            return profile;
        };
    }

    public Profile getProfile(int accountNo) {
        String sql = "select * from profile where account_no = :accountNo";

        MapSqlParameterSource param = new MapSqlParameterSource("accountNo", accountNo);

        List<Profile> profileList = jdbcTemplate.query(sql, param, profileRowMapper);
        return profileList.size() != 0 ? profileList.get(0) : null;
    }

    public boolean createProfile(Profile profile){
        String sql = "insert into profile(" +
                "account_no, register_date, nicname, city, age, gender, user_level) " +
                "values (:accountNo, :registerDate, :nicname, :city, :age, :gender, :userLevel)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(profile);
        int returnCode = jdbcTemplate.update(sql, param);
        return returnCode == 1 ? true : false;
    }

    public boolean updateProfile(Profile profile){
        String sql = "update profile set nicname=:nicname, city=:city, age=:age, gender=:gender, user_level=:userLevel " +
                "where account_no=:accountNo";

        SqlParameterSource param = new BeanPropertySqlParameterSource(profile);
        int returnCode = jdbcTemplate.update(sql, param);
        return returnCode == 1 ? true : false;
    }
}
