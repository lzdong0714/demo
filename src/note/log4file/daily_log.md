### 20190521

#### insert获取主键id

```java
//实体类
public class Analyzer {

    private int id;

    @ApiModelProperty("")
//    @NotBlank
    private int analyzerId;

    private String analyzerType;

    private String analyzerExpression;
}

Dao层
public interface AnalyzerDao {
int insertAnalyzer( Analyzer analyzer);
//注意这里如果添加了@Param,则无法返回主键
int insertAnalyzer(@Param("analyzer")Analyzer analyzer);

```

```xml-dtd
    <insert id="insertAnalyzer" parameterType="Analyzer" useGeneratedKeys="true" keyProperty="id" keyColumn="id" >
    <!--<selectKey resultType="java.lang.Integer" order="AFTER" keyProperty="id">-->
        <!--SELECT LAST_INSERT_ID() AS id-->
    <!--</selectKey>-->
        INSERT INTO
          qrtz_alart_analyzer_test(
            analyzer_id,
            analyzer_type,
            analyzer_expression
          )
        VALUES (
          #{analyzerId},
          #{analyzerType},
          #{analyzerExpression}
        )
    </insert>
```







