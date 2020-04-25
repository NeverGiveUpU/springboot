package learn.springmybatis;

import lombok.Data;

@Data
public class BookDo {
    private long id;
    private String name;
    private int number;

    public BookDo(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
