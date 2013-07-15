package ac.kaist.cts.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ac.kaist.cts.domain.FileUploadForm;

@Controller
public class FileUploadController extends HttpServlet{
	
	
	@RequestMapping(value = "/testFileWrite.do", method = RequestMethod.GET)
    public String testFileWrite(HttpServletRequest request) {
		System.out.println("Hi! testFileWrite.do");
		String realPath = request.getSession().getServletContext().getRealPath("/report/");
		File a = new File(realPath+"/testFolder");
		System.out.println("success ? :"+a.mkdir());
		
        return "file_upload_form";
    }
	
	@RequestMapping(value = "/show.do", method = RequestMethod.GET)
    public String displayForm() {
		System.out.println("Hi! show.do");
        return "file_upload_form";
    }
     
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public String save(
    		
            @ModelAttribute("uploadForm") FileUploadForm uploadForm,
                    Model map, HttpServletRequest request) throws IOException, BiffException {
    	System.out.println("Hi! save.do");
        List<MultipartFile> files = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
        System.out.println("size:"+files.size());
        String realPath = request.getSession().getServletContext().getRealPath("/report/attachedFiles");
		System.out.println(realPath);
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
 
                String fileName = multipartFile.getOriginalFilename();
                System.out.println("File name : "+fileName);
                fileNames.add(fileName);
                
                InputStream is = multipartFile.getInputStream();
                
                Workbook workbook = Workbook.getWorkbook(is);
        		System.out.println("sheet number : " + workbook.getSheets().length);
        		
//        		for(int i = 0 ; i < workbook.getSheets().length ; i++){
//        			System.out.println("sheet name["+i+"] : "+workbook.getSheets()[i].getName());
//        			Sheet sheet = workbook.getSheets()[i];
//        			System.out.println("content 0,0 "+sheet.getCell(0, 0).getContents());
//        		
//        		}
        		
//                Reader      reader      = new InputStreamReader(is);
//
//                int data = reader.read();
//                while(data != -1){
//                    //char theChar = (char) data;
//                    data = reader.read();
//                   
//                    System.out.println(data);
//                }
//
//                reader.close(); 
            }
        }
         
        map.addAttribute("files", fileNames);
        return "file_upload_success";
    }
    
    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        
//        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getfile"));
//            if (file.exists()) {
//                int bytes = 0;
//                ServletOutputStream op = response.getOutputStream();
//
//                response.setContentType(getMimeType(file));
//                response.setContentLength((int) file.length());
//                response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
//
//                byte[] bbuf = new byte[1024];
//                DataInputStream in = new DataInputStream(new FileInputStream(file));
//
//                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
//                    op.write(bbuf, 0, bytes);
//                }
//
//                in.close();
//                op.flush();
//                op.close();
//            }
//        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+ request.getParameter("delfile"));
//            if (file.exists()) {
//                file.delete(); // TODO:check and report success
//            } 
//        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getthumb"));
//                if (file.exists()) {
//                    System.out.println(file.getAbsolutePath());
//                    String mimetype = getMimeType(file);
//                    if (mimetype.endsWith("png") || mimetype.endsWith("jpeg")|| mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
//                        BufferedImage im = ImageIO.read(file);
////                        if (im != null) {
////                            BufferedImage thumb = Scalr.resize(im, 75); 
////                            ByteArrayOutputStream os = new ByteArrayOutputStream();
////                            if (mimetype.endsWith("png")) {
////                                ImageIO.write(thumb, "PNG" , os);
////                                response.setContentType("image/png");
////                            } else if (mimetype.endsWith("jpeg")) {
////                                ImageIO.write(thumb, "jpg" , os);
////                                response.setContentType("image/jpeg");
////                            } else if (mimetype.endsWith("jpg")) {
////                                ImageIO.write(thumb, "jpg" , os);
////                                response.setContentType("image/jpeg");
////                            } else {
////                                ImageIO.write(thumb, "GIF" , os);
////                                response.setContentType("image/gif");
////                            }
////                            ServletOutputStream srvos = response.getOutputStream();
////                            response.setContentLength(os.size());
////                            response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
////                            os.writeTo(srvos);
////                            srvos.flush();
////                            srvos.close();
////                        }
//                    }
//            } // TODO: check and report success
//        } else {
//            PrintWriter writer = response.getWriter();
//            writer.write("call POST with multipart form data");
//        }
//    }
//    
    /**
        * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
        * 
        */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/save1.do", method = RequestMethod.POST)
    protected void upload_file(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println("Save1");
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        System.out.println("make json");
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            System.out.println("item size : "+items.size());
            for (FileItem item : items) {
                if (!item.isFormField()) {
                        File file = new File(request.getServletContext().getRealPath("/")+"imgs/", item.getName());
                        item.write(file);
                        JSONObject jsono = new JSONObject();
                        jsono.put("name", item.getName());
                        jsono.put("size", item.getSize());
                        jsono.put("url", "UploadServlet?getfile=" + item.getName());
                        jsono.put("thumbnail_url", "UploadServlet?getthumb=" + item.getName());
                        jsono.put("delete_url", "UploadServlet?delfile=" + item.getName());
                        jsono.put("delete_type", "GET");
                        //json.put(jsono);
                        json.add(jsono);
                        System.out.println(json.toString());
                }
            }
        } catch (FileUploadException e) {
                throw new RuntimeException(e);
        } catch (Exception e) {
                throw new RuntimeException(e);
        } finally {
            writer.write(json.toString());
            writer.close();
        }

    }

    private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpg")){
                mimetype = "image/jpg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpeg")){
                mimetype = "image/jpeg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("gif")){
                mimetype = "image/gif";
            }else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype  = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }



    private String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }
	
}
