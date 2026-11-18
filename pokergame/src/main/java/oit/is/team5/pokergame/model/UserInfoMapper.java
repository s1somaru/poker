package oit.is.team5.pokergame.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
  @Insert("INSERT INTO userinfo (userName) VALUES (#{userName});")
  void insertUser(String username);

  @Select("SELECT COUNT(*) FROM userinfo WHERE userName = #{userName};")
  int countByUsername(String username);
}
