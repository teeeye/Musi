package ouc.musi.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSON;

import ouc.musi.domain.Result;

public class ResultWriter {
	public static void writeResult(ServletResponse res, Result result) {
		try {
			res.setCharacterEncoding("utf-8");
			PrintWriter writer = res.getWriter();
			writer.write(JSON.toJSONString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
