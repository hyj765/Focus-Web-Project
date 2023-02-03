package com.bb.focus.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUtil {

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
    String basePath = System.getProperty("user.dir") + "\\images\\"+keyword;
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

  public byte[] Read(Long imageSerial){
    String filePath = System.getProperty("user.dir");
    File file = new File(filePath);


    return null;
  }
  public boolean Upload(MultipartFile file,String baseFileName){
    String filePath = System.getProperty("user.dir") + "\\images";
    if(!checkFolder("")){
      return false;
    }
    if(!checkFolder("ficture")){
      return false;
    }
    if(!checkFolder("selfintroduce")){
      return false;
    }
    int serialNumber =getImageCount("selfintroduce");

    String saveName = baseFileName + serialNumber + file.getOriginalFilename();
    filePath = filePath + "\\selfintroduce";
    try {

      File createdFile = new File(filePath+"\\"+saveName);
      file.transferTo(createdFile);
    }catch(IOException e){
        return false;
    }
    return true;
  }

  public boolean Delete(Long fileNumber,String baseName){


    return true;
  }

}
