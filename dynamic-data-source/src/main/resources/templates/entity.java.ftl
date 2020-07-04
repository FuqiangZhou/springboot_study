package ${packageName}.entity;

import com.baomidou.mybatisplus.annotation.TableName;
<#if includePk>
import com.baomidou.mybatisplus.annotation.TableId;
</#if>
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
<#if importDate>
import java.util.Date;
</#if>
<#if importBigDecimal>
import java.math.BigDecimal;
</#if>

/**
 * ${comment}
 */
@Data
@TableName("${tableName}")
public class ${className} {

    <#list javaColumnInfos as javaColumnInfo>
        /**
         * ${javaColumnInfo.comment}
         */
        <#if javaColumnInfo.isPk>
        @TableId("${javaColumnInfo.columnName}")
        </#if>
        <#if !javaColumnInfo.isPk>
        @TableField("${javaColumnInfo.columnName}")
        </#if>
        @ApiModelProperty(name="${javaColumnInfo.javaProperties}", value="${javaColumnInfo.comment}")
        private ${javaColumnInfo.javaType} ${javaColumnInfo.javaProperties};

    </#list>


}