package top.naccl.util.img;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: blog-api
 * @Package: top.naccl.util.img
 * @ClassName: CompyImg
 * @Author: 简鑫鑫
 * @Description:
 * @Date: 2022/8/25 14:06
 * @Version: 1.0
 */
@Component
public class CopyImg {
    public static String copyImg(String content, HttpServletRequest request) throws IOException {
        String webapp = request.getServletContext().getRealPath("/");
        String path = "D:\\image";
        List<String> list = new ArrayList<String>();
        List<String> urlList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+"); //正则表达式匹配所以括号的内容
        Matcher matcher = pattern.matcher(content);
        while(matcher.find()){
            list.add(matcher.group());
            content = content.replace(matcher.group(), "/image" + matcher.group());//将路径/upload修改为/image/upload
        }
        for (int i = 0;i < list.size();i++){
            urlList.add(webapp+list.get(i)); //找到图片在服务器中的地址，并存入list集合中
        }
        File file = null;
        File destFile = null;
        for (int i = 0;i < urlList.size();i++){
            file = new File(urlList.get(i));
            destFile = new File(path,list.get(i));
            FileCopyUtils.copy(file,destFile); //将图片从服务器中复制到我们的磁盘中
        }
        return content;
    }
}

