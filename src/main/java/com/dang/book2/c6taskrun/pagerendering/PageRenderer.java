package com.dang.book2.c6taskrun.pagerendering;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Dangdang on 2018/8/23.
 * 实现单线程的页面渲染器，页面渲染器作用是将HTML页面绘制到图像缓存中<br>
 * 假设HTML页面只包含文本标签，预定义大小的图片和url<br>
 * 本案例实现最简单的方式，即串行的处理HTML文档，解析HTML文档流，遇到文本标签写入图像缓存，遇到图片就通过连接获取之再写到图像缓存
 */
public class PageRenderer {
    public static void main(String[] args) {
        //String List来存放html标签
        List<String> htmlList = new ArrayList<>();
        htmlList.add("text");
        htmlList.add("img");
        htmlList.add("text");
        htmlList.add("img");

        PageRenderer pageRenderer = new PageRenderer();

        System.out.println("main start render " + SecondUtil.getCurrentSecond());

        //pageRenderer.serialRenderHtml(htmlList);
        pageRenderer.advanceSerialRenderHtml(htmlList);

        System.out.println("main start render " + SecondUtil.getCurrentSecond());

    }

    /**
     * 串行的处理html文档流，从前往后依次处理文本标签和img标签
     *
     * @param htmlList 用String list来模拟文本标签和img标签的序列流，其中"text"表示文本流，"img"表示img
     */
    public void serialRenderHtml(List<String> htmlList) {

        for (String item : htmlList) {
            if (item.equalsIgnoreCase("text")) {
                renderText(item);
            }
            if (item.equalsIgnoreCase("img")) {
                renderImg("http://" + item + ".com");
            }
        }
    }

    /**
     * 高级版串行的处理html文档流，首先统一处理文本标签，然后再统一处理img标签
     *
     * @param htmlList 用String list来模拟文本标签和img标签的序列流，其中"text"表示文本流，"img"表示img
     */
    public void advanceSerialRenderHtml(List<String> htmlList) {

        //从HTML文档中获取所有文本标签
        List<String> textList = htmlList.stream().filter(item -> item.equalsIgnoreCase("text")).collect(Collectors.toList());

        List<String> imgList = htmlList.stream().filter(item -> item.equalsIgnoreCase("img")).collect(Collectors.toList());

        //首先，将所有文本标签输出，使用户能看到页面上所有的文本
        for (String item : textList) {
            renderText(item);
        }

        //然后，将所有img标签输出
        for (String img : imgList) {
            renderImg("http://" + img + ".com");
        }
    }

    /**
     * 模拟渲染HTML文本标签的动作
     */
    public void renderText(String text) {
        System.out.println("render html text " + SecondUtil.getCurrentSecond());
    }

    /**
     * 模拟渲染HTML img标签，首先根据url获取图片，然后将其绘制到图片缓存中，其中获取图片耗时较长
     *
     * @param imgUrl
     */
    public void renderImg(String imgUrl) {
        try {
            //模拟获取耗时较长的现象
            SECONDS.sleep(5);
            System.out.println("render html img: " + imgUrl + " " + SecondUtil.getCurrentSecond());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
