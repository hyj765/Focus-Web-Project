package com.bb.focus.common.util;

import java.io.*;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class ImageUtil {

  private final String filePath = "/etc/image";
  private final String companyLogoPath = filePath + "/logo";
  private final String introducePath = filePath + "/introduce";
  private final String facePath = filePath + "/face";
  public boolean ExtensionCheck(MultipartFile file){
    String extension = FilenameUtils
                      .getExtension(file.getOriginalFilename())
                      .toLowerCase(Locale.ENGLISH);

    if(extension.equals("jpg")  || extension.equals("png") || extension.equals("jpeg")){
      return true;
    }

    return false;
  }

  public int getImageCount(String keyword){
    if(keyword.equals("face")){
      keyword = facePath;
    }else if(keyword.equals("company")){
      keyword = companyLogoPath;
    }else{
      keyword = introducePath;
    }
    File directory = new File(keyword);
    File[] files = directory.listFiles();
    if(files == null){
      return -1;
    }
    int fileCount =0;

    for(int i=0; i<files.length; ++i){
      File file = files[i];

      if(file.isFile()) {
        fileCount++;
      }
    }

    return fileCount;
  }

  public byte[] Read_image(String fileName, String folderPath) throws FileNotFoundException, IOException{
    File file = new File(folderPath);
    byte[] imageByteArray;
    String path = folderPath + "\\" + fileName;
    if(!isFileExist(path)){
      return null;
    }

    InputStream imageInput = new FileInputStream(path);
    imageByteArray=IOUtils.toByteArray(imageInput);
    imageInput.close();

    return imageByteArray;
  }
  public String Upload(MultipartFile file,String baseFileName){
    int serialNumber =getImageCount(filePath);

    String saveName = baseFileName + serialNumber + file.getOriginalFilename();
    try {
      File createdFile = new File(filePath+"\\"+saveName);
      file.transferTo(createdFile);
    }catch(IOException e){
        return null;
    }
    return saveName;
  }

  public boolean Delete(String fileName,String folderPath){
    String path = folderPath + '/' + fileName;
    if(!isFileExist(path)){
      return false;
    }
    File file = new File(path);
    file.delete();

    return true;
  }

  public boolean isFileExist(String filePath){
    File file = new File(filePath);

    if(!file.exists()){
      return false;
    }
    if(!file.isFile()){
      return false;
    }

    return true;
  }

}
