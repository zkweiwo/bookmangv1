package cn.edu.nyist.bookmanv1.biz;

import java.util.Date;

//˵�����鼮ҵ���
public interface BookBiz {
	int saveBook(String name, String descri, double price, String author, int tid, String newFileName, Date pubDate);

}
