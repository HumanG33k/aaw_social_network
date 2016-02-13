/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.FilesDao;
import dao.FilesEntity;
import dao.UsersEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jerome
 */
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    FilesDao filesDao;

    @Override
    public void add(MultipartFile file, UsersEntity usersEntity) {
        FilesEntity fileEntity;
        try {
            fileEntity = new FilesEntity(file.getName(),file.getContentType(),file.getBytes(), usersEntity);
            this.filesDao.save(fileEntity);
        } catch (IOException ex) {
            Logger.getLogger(FilesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<FilesEntity> searchByOwner(UsersEntity owner) {
            return this.filesDao.findByOwner(owner);
    }
    
    
    

}
