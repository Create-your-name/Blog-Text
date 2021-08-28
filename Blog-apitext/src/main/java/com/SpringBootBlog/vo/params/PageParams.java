package com.SpringBootBlog.vo.params;

import lombok.Data;

@Data
public class PageParams {
    private int page =1;

    private  int pageSize=10;

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    private  Long categoryId;

    private  Long tagId;

    private  String year;

    private  String month;

    public  String getMonth(){
        if(this.month != null && this.month.length()==1){
            return "0" + this.month;
        }
        return  this.month;
    }
}
