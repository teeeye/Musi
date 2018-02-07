package com.musi.enumeration;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class MusicCategory {
private static List<MusicCategory> categoryList;
	
	static {
		categoryList = new ArrayList<MusicCategory>();
		categoryList.add(new MusicCategory(1, "古典"));
		categoryList.add(new MusicCategory(1 << 1, "流行音乐"));
		categoryList.add(new MusicCategory(1 << 2, "轻音乐"));
		categoryList.add(new MusicCategory(1 << 3, "韩流"));
		categoryList.add(new MusicCategory(1 << 4, "摇滚"));
		categoryList.add(new MusicCategory(1 << 5, "电子"));
		categoryList.add(new MusicCategory(1 << 6, "爵士"));
		categoryList.add(new MusicCategory(1 << 7, "民谣"));
		categoryList.add(new MusicCategory(1 << 8, "说唱"));
		categoryList.add(new MusicCategory(1 << 9, "金属"));
		categoryList.add(new MusicCategory(1 << 10, "朋克"));
		categoryList.add(new MusicCategory(1 << 11, "拉丁"));
		categoryList.add(new MusicCategory(1 << 12, "蓝调"));
		categoryList.add(new MusicCategory(1 << 13, "古风"));
	}
	
	private int flag;
	private String name;
		
	private MusicCategory(int flag, String name) {
		this.flag = flag;
		this.name = name;
	}
	
	public int getFlag() {
		return flag;
	}
	
	public String getName() {
		return name;
	}
	
	public static List<MusicCategory> getAllCategories() {
		return categoryList;
	}
	
	public static void main(String[] args) {
		System.out.println(JSON.toJSON(getAllCategories()));
	}
}

