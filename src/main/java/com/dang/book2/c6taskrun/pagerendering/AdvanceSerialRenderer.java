package com.dang.book2.c6taskrun.pagerendering;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Dangdang on 2018/8/23.
 * 高级版串行的处理HTML文档，解析HTML文档流，先统一处理文本标签，让所有文本显示，再统一处理img
 */
public class AdvanceSerialRenderer {
    public static void main(String[] args) {
        //String List来存放html标签
        List<String> htmlList = new ArrayList<>();
        htmlList.add("text");
        htmlList.add("img");
        htmlList.add("text");
        htmlList.add("img");

        AdvanceSerialRenderer serialRenderer = new AdvanceSerialRenderer();

        System.out.println("main start render " + SecondUtil.getCurrentSecond());
        serialRenderer.advanceSerialRenderHtml(htmlList);
        System.out.println("main start render " + SecondUtil.getCurrentSecond());

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
