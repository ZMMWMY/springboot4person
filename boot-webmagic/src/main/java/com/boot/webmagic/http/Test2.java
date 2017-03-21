package com.boot.webmagic.http;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Author : MrZ
 *
 * @Description Created in 12:32 on 2017/3/21.
 * Modified By :
 */
public class Test2 {
    public static void main(String[] args) throws ParserException {


        String url = "http://www.tristone.co.jp/blog/oguri/img/photo/sp_1489298357.jpg";
        HttpUtil.getHttpUtilInstance().downImg(url);

    }
}
