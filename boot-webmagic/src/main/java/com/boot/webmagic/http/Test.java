package com.boot.webmagic.http;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

/**
 * Created by Z先生 on 2017/3/17.
 */
public class Test {
    public static void main(String[] args) throws ParserException {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>タイトル一覧表示｜小栗旬BLOG｜小栗旬MOBILE</title>\n" +
                "<meta name=\"keywords\" content=\"小栗旬,BLOG,blog,ブログ,日記\">\n" +
                "<meta name=\"description\" content=\"[小栗旬ブログ]タイトル一覧\">\n" +
                "<!--[if lt IE 9]><script src=\"https://html5shiv.googlecode.com/svn/trunk/html5.js\"></script><![endif]-->\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no\">\n" +
                "<meta name=\"format-detection\" content=\"telephone=no\">\n" +
                "<link rel=\"stylesheet\" href=\"/css/blogOguri.css\" type=\"text/css\" media=\"all\">\n" +
                "<link href=\"/img/favicon_iphone.png\" rel=\"apple-touch-icon-precomposed\" />\n" +
                "<link href=\"/img/favicon.png\" rel=\"shortcut icon\" type=\"image/png\" />\n" +
                "<link href=\"/img/favicon.png\" rel=\"icon\" type=\"image/png\" />\n" +
                "<script type=\"text/javascript\">\n" +
                "\n" +
                "  var _gaq = _gaq || [];\n" +
                "  _gaq.push(['_setAccount', 'UA-26415064-6']);\n" +
                "  _gaq.push(['_trackPageview']);\n" +
                "\n" +
                "  (function() {\n" +
                "    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n" +
                "    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n" +
                "    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n" +
                "  })();\n" +
                "\n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div id=\"container\">\n" +
                "\n" +
                "<!-- HEADER -->\n" +
                "<header>\n" +
                "<h1>OGURI SHUN MOBILE for SMART PHONE</h1>\n" +
                "<h2>小栗旬MOBILE</h2>\n" +
                "<nav>\n" +
                "<ul>\n" +
                "<li class=\"back\"><a href=\"#\" onClick=\"history.back(); return false;\">戻る</a></li>\n" +
                "<li class=\"menu\"><a href=\"#\" class=\"cl\">MENU</a></li>\n" +
                "</ul>\n" +
                "</nav>\n" +
                "</header>\n" +
                "<!-- //HEADER -->\n" +
                "\n" +
                "<!-- CONTENT -->\n" +
                "<article>\n" +
                "<section>\n" +
                "<h2>小栗旬BLOG</h2>\n" +
                "<ul class=\"list\">\n" +
                "<li><a href=\"article.php?id=1489298361\">全力<time datetime=\"2017-03-12\">2017/03/12</time></a></li><li><a href=\"article.php?id=1488634693\">この漫画<time datetime=\"2017-03-04\">2017/03/04</time></a></li><li><a href=\"article.php?id=1485407527\">正解<time datetime=\"2017-01-26\">2017/01/26</time></a></li><li><a href=\"article.php?id=1484787281\">さて、<time datetime=\"2017-01-19\">2017/01/19</time></a></li><li><a href=\"article.php?id=1484276001\">ずーっと<time datetime=\"2017-01-13\">2017/01/13</time></a></li><li><a href=\"article.php?id=1482634044\">昨日<time datetime=\"2016-12-25\">2016/12/25</time></a></li><li><a href=\"article.php?id=1479555450\">素敵な<time datetime=\"2016-11-19\">2016/11/19</time></a></li><li><a href=\"article.php?id=1478756311\">友人から<time datetime=\"2016-11-10\">2016/11/10</time></a></li><li><a href=\"article.php?id=1477984963\">とうとう<time datetime=\"2016-11-01\">2016/11/01</time></a></li><li><a href=\"article.php?id=1476773464\">こないだ<time datetime=\"2016-10-18\">2016/10/18</time></a></li></ul>\n" +
                "<ul class=\"paging\"><li><span>前の10件</span></li><li><a href=\"?page=2\">次の10件</a></li></ul></section>\n" +
                "</article>\n" +
                "<!-- //CONTENT -->\n" +
                "\n" +
                "<!-- FOOTER -->\n" +
                "<footer>\n" +
                "\n" +
                "<!-- FOOT NAVIGATION -->\n" +
                "<nav>\n" +
                "<ul>\n" +
                "<li><a href=\"/\">HOME</a></li>\n" +
                "<li><a href=\"#\" class=\"cl\">MENU</a></li>\n" +
                "<li><a href=\"/mypage/\">MY PAGE</a></li>\n" +
                "</ul>\n" +
                "</nav>\n" +
                "<!-- //FOOT NAVIGATION -->\n" +
                "\n" +
                "<!-- COPYRIGHT -->\n" +
                "<small>&copy;Tristone Entertainment, Inc./M-UP</small>\n" +
                "<!-- //COPYRIGHT -->\n" +
                "\n" +
                "</footer>\n" +
                "<!-- //FOOTER -->\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "<!-- MENU -->\n" +
                "<script type=\"text/javascript\" src=\"/js/jquery-1.6.2.min.js\" charset=\"utf-8\"></script>\n" +
                "<script type=\"text/javascript\" src=\"/js/common.js\" charset=\"utf-8\"></script>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "$(function() {\n" +
                "\toguri.contentsListNav.init();\n" +
                "});\n" +
                "</script>\n" +
                "<div id=\"contentsListNav\">\n" +
                "<div class=\"contentsListHeader\">\n" +
                "<p class=\"title\">MENU</p>\n" +
                "<a href=\"#\" class=\"close\">CLOSE</a>\n" +
                "</div>\n" +
                "<div class=\"contentsList\">\n" +
                "<ul>\n" +
                "<li><a href=\"/\">HOME</a></li>\n" +
                "<li><a href=\"/news/\">NEWS</a></li>\n" +
                "<li><a href=\"/media/\">MEDIA</a></li>\n" +
                "<li><a href=\"/profile/\">PROFILE</a></li>\n" +
                "<li><a href=\"/data/\">DATA</a></li>\n" +
                "<li><a href=\"/mailmagazine/\">MAIL MAGAZINE</a></li>\n" +
                "<li><a href=\"/shop/\" target=\"_blank\">SHOP</a></li>\n" +
                "<li><a href=\"/blog/\">BLOG</a></li>\n" +
                "<li><a href=\"/photo/\">PHOTO</a></li>\n" +
                "<li><a href=\"/deco/\">DECO</a></li>\n" +
                "<li><a href=\"/movie/\">MOVIE</a></li>\n" +
                "<li><a href=\"/fanmail/\">FAN MAIL</a></li>\n" +
                "<li><a href=\"/present/\">PRESENT</a></li>\n" +
                "<li><a href=\"/support/about.html\">小栗旬MOBILE for SMARTPHONEとは?</a></li>\n" +
                "<li><a href=\"/members/login.html\">ログイン</a></li>\n" +
                "<li><a href=\"/support/request.php\">リクエスト</a></li>\n" +
                "<li><a href=\"/support/qa.html\">よくある質問</a></li>\n" +
                "<li><a href=\"/support/inquiry.html\">お問合わせ</a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"screen\">\n" +
                "</div>\n" +
                "<!-- //MENU -->\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        Parser parser = new Parser(html);


    }
}
