package org.mytoypjt.dao;

import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;
import org.mytoypjt.models.entity.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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
                    rs.getString("nickname"),
                    rs.getDate("register_date"),
                    rs.getString("city"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getInt("user_level")
            );
            return profile;
        };
    }

    public List<Profile> findByAccountNo(int accountNo) {
        String sql = "select * from profile where account_no = :accountNo";
        MapSqlParameterSource param = new MapSqlParameterSource("accountNo", accountNo);
        return jdbcTemplate.query(sql, param, profileRowMapper);
    }

    public Profile createProfile(Profile profile){
        String sql = "insert into profile(" +
                "account_no, register_date, nickname, city, age, gender, user_level) " +
                "values (:accountNo, :registerDate, :nickname, :city, :age, :gender, :userLevel)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(profile);

        try {
            jdbcTemplate.update(sql, param);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROFILE_CREATION_FAILURE, e.getMessage());
        }
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        String sql = "update profile set nickname=:nickname, city=:city, age=:age, gender=:gender, user_level=:userLevel " +
                "where account_no=:accountNo";

        SqlParameterSource param = new BeanPropertySqlParameterSource(profile);

        try {
            jdbcTemplate.update(sql, param);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROFILE_UPDATE_FAILURE, e.getMessage());
        }
        return profile;
    }
}
