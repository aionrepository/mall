package com.als.mall.handler.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.als.mall.enums.GenderEnum;



/**
 * 自定义类型处理器；
 * 将java类型与jdbc类型进行转换；
 * 在mybatis中需要配置转换器包扫描：mybatis.type-handler-package:  com.als.mall.handler.tpehandlers(类型转换器类所在包)；
 * 在mybatis-plus中配置：mybatis-plus.type-handlers-package: com.als.mall.handler.typehandler，
 * 当需要转换的类型是枚举，只需配置：mybatis-plus.type-enums-package: com.als.mall.enums，
 * 此时枚举类需要额外设置
 * @author 艾霖森
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(GenderEnum.class)
public class GenderTypeHandler extends BaseTypeHandler<GenderEnum>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, GenderEnum parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, javaToJdbc(parameter));
	}

	@Override
	public GenderEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return jdbcToJava(rs.getInt(columnName));
	}

	@Override
	public GenderEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return jdbcToJava(rs.getInt(columnIndex));
	}

	@Override
	public GenderEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return jdbcToJava(cs.getInt(columnIndex));
	}

	/**
	 * 将javaType转换为jdbcType
	 * @param gender GenderEnum
	 * @return int
	 */
	private int javaToJdbc(GenderEnum gender) {
		return gender.equals(GenderEnum.MALE) ? 1: 0;
	}
	
	/**
	 * 将jdbcType转换为javaType
	 * @param gender int
	 * @return GenderEnum
	 */
	private GenderEnum jdbcToJava(int gender) {
		return gender == 1 ? GenderEnum.MALE: GenderEnum.FEMALE;
	}
}
