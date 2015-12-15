package orz.kassy.railsapi.json;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Created by kashimoto on 2015/10/10.
 */
@JsonModel(decamelize=true)
public class FeedJson {
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonKey
    private Integer id;

    @JsonKey
    private String name;

    @JsonKey
    private Integer age;

    @JsonKey
    private String memo;

}
