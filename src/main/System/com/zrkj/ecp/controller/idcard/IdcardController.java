package com.zrkj.ecp.controller.idcard;

import com.zrkj.ecp.domain.file.IdCardFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("idcardController")
@MultipartConfig
public class IdcardController {

	@RequestMapping("upload")
	public void upload(MultipartFile images, HttpServletRequest request)
			throws IOException {
		String fileName = images.getOriginalFilename();
        String name = images.getName();
        String type = images.getContentType();
        String newName = UUID.randomUUID().toString();
        String path = request.getServletContext().getRealPath(
				"WEB-INF/idcard/");
        Date timeCur = new Date();
		SimpleDateFormat fmtYY = new SimpleDateFormat("yyyy");
		SimpleDateFormat fmtMM = new SimpleDateFormat("MM");
		SimpleDateFormat fmtDD = new SimpleDateFormat("dd");
		String strYY = fmtYY.format(timeCur);
		String strMM = fmtMM.format(timeCur);
		String strDD = fmtDD.format(timeCur);
		String dataPath = "/" + strYY + "/" + strMM + "/" + strDD + "/";
		String realPath = path + dataPath ;
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("name:"+name);
	    System.out.println("type:"+type);
        System.out.println("realpath:"+realPath);
        System.out.println("newName:"+newName);
        System.out.println("oldName:"+fileName);
        IdCardFile idCardFile = new IdCardFile();
        idCardFile.setFileName(fileName);
        idCardFile.setNewName(newName);
        idCardFile.setOldName(fileName);
        idCardFile.setType(type);
        idCardFile.setPath(dataPath);
		File imageFile = new File(realPath, newName);
		try {
			images.transferTo(imageFile);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
