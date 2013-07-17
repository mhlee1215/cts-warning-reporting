package ac.kaist.sms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ac.kaist.sms.model.FileMeta;


@Controller
@RequestMapping("/controller")
public class FileController {

	
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	FileMeta fileMeta = null;
	/***************************************************
	 * URL: /rest/controller/upload  
	 * upload(): receives files
	 * @param request : MultipartHttpServletRequest auto passed
	 * @param response : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 * @throws IOException 
	 ****************************************************/
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
 
		//1. build an iterator
		 Iterator<String> itr =  request.getFileNames();
		 String webPath = "report/attachedFiles";
		 String realPath = request.getSession().getServletContext().getRealPath(webPath);
		 System.out.println(realPath);
		 MultipartFile mpf = null;

		 //2. get each file
		 while(itr.hasNext()){
			 UUID uId = UUID.randomUUID();
			 String curPath = realPath+"/"+uId;
			 String curWebPath = webPath + "/"+uId;
			 new File(curPath).mkdir();
			 //2.1 get next MultipartFile
			 mpf = request.getFile(itr.next()); 
			 System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
			 
			 //2.2 if files > 10 remove the first from the list
			 if(files.size() >= 10)
				 files.pop();
			 
			 //2.3 create new fileMeta
			 fileMeta = new FileMeta();
			 fileMeta.setFileName(mpf.getOriginalFilename());
			 fileMeta.setFilePath(curWebPath+"/"+mpf.getOriginalFilename());
			 fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
			 fileMeta.setFileType(mpf.getContentType());
			 FileOutputStream file = new FileOutputStream(curPath+"/"+mpf.getOriginalFilename());
			 byte[] dataByte = mpf.getBytes();
			 for (int i = 0; i < dataByte.length; i++)
			        file.write(dataByte[i]);
			      file.close();
			 try {
				fileMeta.setBytes(mpf.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //2.4 add to files
			 files.add(fileMeta);
			 
		 }
		 
		 System.out.println(files);
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files.toString();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(HttpServletRequest request, HttpServletResponse response) {	
		String file_id = ServletRequestUtils.getStringParameter(request, "file_id", "");
		return "success!"+file_id;
	}
	
	
	/***************************************************
	 * URL: /rest/controller/get/{value}
	 * get(): get file as an attachment
	 * @param response : passed by the server
	 * @param value : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	 public void get(HttpServletResponse response,@PathVariable String value){
		 FileMeta getFile = files.get(Integer.parseInt(value));
		 try {		
			 	response.setContentType(getFile.getFileType());
			 	response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
		        FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
		 }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
	 }
 
}
