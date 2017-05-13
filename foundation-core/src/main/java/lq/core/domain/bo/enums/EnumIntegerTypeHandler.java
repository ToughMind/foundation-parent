package lq.core.domain.bo.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 为了增加代码的可读性，代码中使用枚举，数据库中存储数值。枚举本身的ordinal机制虽然可以满足枚举和数值的相互转换，
 * 但可维护性较差。故参照mybatis的{@code EnumOrdinalTypeHandler}定义了该mybatis的类型处理器，对枚举和数字
 * 的相互转换做了统一处理。
 * 
 * 注意要使用该类型处理器，枚举必须要实现{@link EnumIntegerInterface}接口
 * @author 刘泉
 * @date 2016年9月29日 下午8:34:49
 */
public class EnumIntegerTypeHandler<E extends EnumIntegerInterface<E>> extends BaseTypeHandler<E>{

    private Class<E> type;
    private final E[] enums;

    public EnumIntegerTypeHandler(Class<E> type) {
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
        JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName)
        throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        }
        
        for(E e : enums){
            if(e.getValue() == i)
                return e;
        }
        throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", null);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex)
        throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        }
        
        for(E e : enums){
            if(e.getValue() == i)
                return e;
        }
        throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", null);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
        throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        }
        for(E e : enums){
            if(e.getValue() == i)
                return e;
        }
        throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", null);
    }

}
