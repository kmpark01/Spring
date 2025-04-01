package org.joonzis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum; // 현재 페이지 번호
    private int amount;  // 한 페이지당 보여줄 게시글 수

    public Criteria() {
        this(1, 10); // 기본값: 1페이지, 10개 게시글
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
