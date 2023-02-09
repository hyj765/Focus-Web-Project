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

  public boolean ExtensionCheck(MultipartFile file){
    String extension = FilenameUtils
                      .getExtension(file.getOriginalFilename())
                      .toLowerCase(Locale.ENGLISH);

    if(extension.equals("jpg")  || extension.equals("png") || extension.equals("jpeg")){
      return true;
    }

    return false;
  }

  public boolean checkFolder(String folderName){
    String filePath = System.getProperty("user.dir");
    if(!folderName.equals("")){
      folderName = "\\"+folderName;
    }
    File file = new File(filePath+ "\\images" + folderName);

    if(file.exists()){
      return true;
    }else{
      file.mkdir();
      if(file.exists()){
        return true;
      }
    }
    return false;
  }

  public int getImageCount(String keyword){
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
  public boolean Upload(MultipartFile file,String baseFileName){
//    String filePath = "tmp/image";
    int serialNumber =getImageCount(filePath);

    String saveName = baseFileName + serialNumber + file.getOriginalFilename();
//    filePath = filePath + "\\selfintroduce";
    try {

      File createdFile = new File(filePath+"\\"+saveName);
      file.transferTo(createdFile);
    }catch(IOException e){
        return false;
    }
    return true;
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
