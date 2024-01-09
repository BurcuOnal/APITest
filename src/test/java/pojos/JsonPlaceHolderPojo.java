package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //bilmediğim bir şey gelirse bunu görmezden gel
public class JsonPlaceHolderPojo {
    /*
    Pojo: "Plain old java object"- mükemmel bir java objesi
    4 basamağı vardır:
    --->
    1- Private değişkenler oluşturulur
    2- Constructorlar oluşturulur (parametresiz ve bütün parametleri içeren)
    3- Getter ve Setter lar oluşuturul
    4- toString methodu oluşturulur.
     */

/*
{
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            } -- oluşuturulaca şablon bu
 */
    //1-Private değişkenler oluşturulur
    private Integer userId;
    private String title;
    private Boolean completed;

    //2- Constructorlar oluşturulur


    public JsonPlaceHolderPojo() {

    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
