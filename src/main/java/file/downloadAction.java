package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadAction")
public class downloadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("file");
		
		//서버 외부에 다운로드 위치 설정 - 시큐어 코딩
		String directory = "C:/ldw_data/upload";
		File file = new File(directory + "/" + fileName);
		
		String mimeType = getServletContext().getMimeType(file.toString());
		if(mimeType == null) {
			response.setContentType("application/octet-stream"); //2진 파일을 사용할 경우
		}
		
		String downloadName = null;
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) { //인터넷 익스플로러 사용자가 아니라면
			downloadName = new String(fileName.getBytes("UTF-8"), "8859_1"); //8859_1로 전달하면 파일이 깨지지 않고 전달됨			
		}else {
			downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ downloadName + "\";");

		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b[] = new byte[1024];
		int data = 0;
		
		while((data = (fileInputStream.read(b, 0, b.length))) != -1) {
			servletOutputStream.write(b, 0, data);
		}
		
		new FileDAO().hit(fileName); // 다운로드를 1씩 증가

		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}
}