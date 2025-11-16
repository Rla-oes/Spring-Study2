package wink.spring_study.controller;

public class MemberForm {
    private String name;
    public String getName() { //getName으로 꺼내줌
        return name;
    }
    public void setName(String name) { //setName을 통해서 값을 넣어줌
        this.name = name;
    }
}
