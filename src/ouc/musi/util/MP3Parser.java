package ouc.musi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

public class MP3Parser {

	public static int getLength(File file) {

		try {
			
			InputStream input = new FileInputStream(file);
			ContentHandler handler = new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new Mp3Parser();
			ParseContext parseCtx = new ParseContext();
			parser.parse(input, handler, metadata, parseCtx);
			input.close();

			int msc_lnth = (int)Double.parseDouble(metadata.get("xmpDM:duration")) / 1000;
			return msc_lnth;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
